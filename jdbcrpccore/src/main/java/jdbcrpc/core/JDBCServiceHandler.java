package jdbcrpc.core;

import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import javafx.util.converter.BigDecimalStringConverter;
import jdbcrpc.thrift.ArrayVal;
import jdbcrpc.thrift.RConnection;
import jdbcrpc.thrift.RResultSet;
import jdbcrpc.thrift.RResultSetMetaData;
import jdbcrpc.thrift.RResultSetMetaDataPart;
import jdbcrpc.thrift.RRow;
import jdbcrpc.thrift.RSQLException;
import jdbcrpc.thrift.RSQLWarning;
import jdbcrpc.thrift.RStatement;
import jdbcrpc.thrift.RStaticMetaData;
import jdbcrpc.thrift.RValue;
import jdbcrpc.thrift.RValueSQL;
import jdbcrpc.thrift.RawVal;
import jdbcrpc.thrift.RawVal._Fields;
import jdbcrpc.thrift.RjdbcService.Iface;
import jdbcrpc.thrift.statement_getWarnings_return;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCServiceHandler implements Iface {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCServiceHandler.class.getName());

    private final ConcurrentHashMap<Integer, Connection> connectionById;
    private volatile int idConnectionIncrement;
    private final ConcurrentHashMap<Integer, Statement> statementById;
    private volatile int idStatementIncrement;

    public JDBCServiceHandler() {

        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        TimeZone.setDefault(timeZone);

        this.connectionById = new ConcurrentHashMap<>();
        this.idConnectionIncrement = 0;
        this.statementById = new ConcurrentHashMap<>();
        this.idStatementIncrement = 0;
    }

    private Connection getConnection(final RConnection connection) throws TException {

        // Check predicates
        if (connection == null) {
            throw new TException("Connection cannot be null");
        }

        // Get back connection
        Connection jdbcConnection = this.connectionById.get(connection.id);
        if (jdbcConnection == null) {
            throw new TException("Connection id does not match an existing connexion");
        }

        return jdbcConnection;
    }

    private Statement getStatement(final RStatement statement) throws TException {

        // Check predicates
        if (statement == null) {
            throw new TException("statement cannot be null");
        }

        // Get back connection
        Statement jdbcStatement = this.statementById.get(statement.id);
        if (jdbcStatement == null) {
            throw new TException("Statement id does not match an existing statement");
        }

        return jdbcStatement;
    }

    private PreparedStatement getPreparedStatement(final RStatement statement) throws TException {

        Statement jdbcStatement = getStatement(statement);
        if (jdbcStatement instanceof PreparedStatement) {
            return (PreparedStatement) jdbcStatement;
        } else {
            throw new TException("Statement must be prepared statement");
        }
    }

    private void removeConnection(final RConnection connection,
            boolean removeStatements) throws SQLException {

        if (this.connectionById.containsKey(connection.id)) {
            Connection remove = this.connectionById.remove(connection.id);
            if (removeStatements) {
                for (Entry<Integer, Statement> entry : this.statementById.entrySet()) {
                    if (entry.getValue().getConnection().equals(remove)) {
                        // avoid simultaneous removing
                        if (this.statementById.containsKey(entry.getKey())) {
                            this.statementById.remove(entry.getKey());
                        }
                    }
                }
            }
        }
    }

    private void removeStatement(final RStatement statement) {

        if (this.statementById.containsKey(statement.id)) {
            this.statementById.remove(statement.id);
        }
    }

    private RResultSet parseResultSet(ResultSet res) throws SQLException {

        // Get metadatas for describing columns
        ResultSetMetaData jdbcResMetadata = res.getMetaData();
        int columnCount = jdbcResMetadata.getColumnCount();
        RResultSetMetaData resultSetMetadata = new RResultSetMetaData();
        /*for (int i = 1; i <= columnCount; ++i) {
            resultSetMetadata.addToParts(new RResultSetMetaDataPart(jdbcResMetadata.getCatalogName(i),
                    jdbcResMetadata.getColumnClassName(i), jdbcResMetadata.getColumnDisplaySize(i),
                    jdbcResMetadata.getColumnLabel(i), jdbcResMetadata.getColumnName(i), jdbcResMetadata.getColumnType(i),
                    jdbcResMetadata.getColumnTypeName(i), jdbcResMetadata.getPrecision(i), jdbcResMetadata.getScale(i),
                    jdbcResMetadata.getSchemaName(i), jdbcResMetadata.getTableName(i), jdbcResMetadata.isAutoIncrement(i),
                    jdbcResMetadata.isCaseSensitive(i), jdbcResMetadata.isCurrency(i), jdbcResMetadata.isDefinitelyWritable(i),
                    jdbcResMetadata.isNullable(i), jdbcResMetadata.isReadOnly(i), jdbcResMetadata.isSearchable(i),
                    jdbcResMetadata.isSigned(i), jdbcResMetadata.isWritable(i)));
        }*/
        for (int i = 1; i <= columnCount; ++i) {
            RResultSetMetaDataPart resultSetMetaDataPart = new RResultSetMetaDataPart();
            resultSetMetaDataPart.columnTypeName = jdbcResMetadata.getColumnTypeName(i);
            resultSetMetaDataPart.columnType = jdbcResMetadata.getColumnType(i);
            resultSetMetaDataPart.columnName = jdbcResMetadata.getColumnName(i);
            resultSetMetadata.addToParts(resultSetMetaDataPart);
        }

        // Get rows
        ArrayList<RRow> rowList = new ArrayList<>();
        while (res.next()) {
            ArrayList<RValue> valueList = new ArrayList<>();
            for (int i = 1; i <= columnCount; ++i) {
                RValue val = new RValue();

                Object obj = res.getObject(i);
                if (obj != null) {
                    RawVal rawVal = new RawVal();
                    val.val = rawVal;

                    int columntype = resultSetMetadata.parts.get(i - 1).getColumnType();
                    switch (columntype) {
                        case java.sql.Types.INTEGER:
                            rawVal.setInteger_val(res.getInt(i));
                            break;
                        case java.sql.Types.BIGINT:
                            rawVal.setBigint_val(res.getLong(i));
                            break;
                        case java.sql.Types.SMALLINT:
                            rawVal.setSmallint_val(res.getShort(i));
                            break;
                        case java.sql.Types.TINYINT:
                            rawVal.setTinyint_val(res.getByte(i));
                            break;
                        case java.sql.Types.FLOAT:
                        case java.sql.Types.DOUBLE:
                            rawVal.setDouble_val(res.getDouble(i));
                            break;
                        case java.sql.Types.DECIMAL:
                            rawVal.setString_val(res.getBigDecimal(i).toEngineeringString());
                            break;
                        case java.sql.Types.BOOLEAN:
                            rawVal.setBool_val(res.getBoolean(i));
                            break;
                        case java.sql.Types.TIME:
                            rawVal.setBigint_val(res.getTime(i).getTime());
                            break;
                        case java.sql.Types.DATE:
                            rawVal.setBigint_val(res.getDate(i).getTime());
                            break;
                        case java.sql.Types.TIMESTAMP:
                            rawVal.setBigint_val(res.getTimestamp(i).getTime());
                            break;
                        case java.sql.Types.VARCHAR:
                        case java.sql.Types.CHAR:
                            rawVal.setString_val(res.getString(i));
                            break;
                        case java.sql.Types.VARBINARY:
                        case java.sql.Types.BINARY:
                            rawVal.setBinary_val(res.getBytes(i));
                            break;
                        case java.sql.Types.ARRAY:
                            rawVal.setArray_val(readArray(res.getArray(i)));
                            break;
                        case Types.JAVA_OBJECT:
                            rawVal.setString_val(res.getObject(i).toString());
                            break;
                        default:
                            throw new SQLException("Type not supported by jdbcRPC: " + resultSetMetadata.parts.get(i - 1).getColumnTypeName());
                    }

                    val.isnull = false;
                } else {
                    val.isnull = true;
                }

                valueList.add(val);
            }
            RRow row = new RRow(valueList);
            rowList.add(row);
        }

        // Result set should be closed immediatly, so do not maintain id
        RResultSet resultSet = new RResultSet(0, rowList, resultSetMetadata);
        return resultSet;
    }

    private ArrayVal readArray(Array array) throws SQLException {

        ArrayVal arrayVal = new ArrayVal();
        arrayVal.sqlType = array.getBaseType();
        arrayVal.elements = new ArrayList<>();

        switch (arrayVal.sqlType) {
            case java.sql.Types.INTEGER:
                for (int e : (int[])array.getArray())
                {
                    arrayVal.elements.add(new RawVal(_Fields.INTEGER_VAL, e));
                }
                break;
            case java.sql.Types.BIGINT:
                for (long e : (long[])array.getArray())
                {
                    arrayVal.elements.add(new RawVal(_Fields.BIGINT_VAL, e));
                }
                break;
            case java.sql.Types.SMALLINT:
                for (short e : (short[])array.getArray())
                {
                    arrayVal.elements.add(new RawVal(_Fields.SMALLINT_VAL, e));
                }
                break;
            case java.sql.Types.TINYINT:
                for (byte e : (byte[])array.getArray())
                {
                    arrayVal.elements.add(new RawVal(_Fields.TINYINT_VAL, e));
                }
                break;
            case java.sql.Types.FLOAT:
            case java.sql.Types.DOUBLE:
                for (double e : (double[])array.getArray())
                {
                    arrayVal.elements.add(new RawVal(_Fields.DOUBLE_VAL, e));
                }
                break;
            case java.sql.Types.DECIMAL:
                for (BigDecimal e : (BigDecimal[])array.getArray())
                {
                    arrayVal.elements.add(new RawVal(_Fields.STRING_VAL, e.toEngineeringString()));
                }
                break;
            case java.sql.Types.BOOLEAN:
                for (boolean e : (boolean[])array.getArray())
                {
                    arrayVal.elements.add(new RawVal(_Fields.BOOL_VAL, e));
                }
                break;
            case java.sql.Types.TIME:
            case java.sql.Types.DATE:
            case java.sql.Types.TIMESTAMP:
                for (Date e : (Date[])array.getArray())
                {
                    arrayVal.elements.add(new RawVal(_Fields.MSFROMEPOCH_VAL, e.getTime()));
                }
                break;
            case java.sql.Types.VARCHAR:
            case java.sql.Types.CHAR:
                for (String e : (String[])array.getArray())
                {
                    arrayVal.elements.add(new RawVal(_Fields.STRING_VAL, e));
                }
                break;
            case java.sql.Types.BINARY:
                for (Byte[] e : (Byte[][])array.getArray())
                {
                    arrayVal.elements.add(new RawVal(_Fields.BINARY_VAL, e));
                }
                break;
            default:
                throw new SQLException("Type not supported by jdbcRPC: " + arrayVal.getSqlType());
        }

        return arrayVal;
    }

    public void dispose() throws SQLException {

        // Release statement that have not been closed
        for (Statement statement : this.statementById.values()) {
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        this.statementById.clear();

        // Release connection that has not been closed
        for (Connection connection : this.connectionById.values()) {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
        this.connectionById.clear();
    }

    @Override
    public RConnection createConnection(String url,
            Map<String, String> properties) throws RSQLException, TException {
        try {
            // Create properties
            Properties props = new Properties();
            if (properties != null) {
                for (Entry<String, String> keyEtr : properties.entrySet()) {
                    props.put(keyEtr.getKey(), keyEtr.getValue());
                }
            }

            // Create connection
            LOGGER.info("Connecting to " + url);
            DriverManager.setLoginTimeout(2);
            Connection jdbcConnection = DriverManager.getConnection(url, props);

            // Wrap and map connection
            RConnection connection = new RConnection();
            synchronized (this) {
                connection.id = this.idConnectionIncrement++;
            }
            this.connectionById.put(connection.id, jdbcConnection);
            return connection;
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public RStatement createStatement(RConnection connection)
            throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            // Create statement
            Statement jdbcStatement = jdbcConnection.createStatement();

            // Wrap and map statement
            RStatement statement = new RStatement();
            statement.id_connection = connection.id;
            synchronized (this) {
                statement.id = this.idStatementIncrement++;
            }
            this.statementById.put(statement.id, jdbcStatement);

            return statement;
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public RStatement prepareStatement(RConnection connection, String sql)
            throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            // Create statement
            Statement jdbcStatement = jdbcConnection.prepareStatement(sql);

            // Wrap and map statement
            RStatement statement = new RStatement();
            statement.id_connection = connection.id;
            synchronized (this) {
                statement.id = this.idStatementIncrement++;
            }
            this.statementById.put(statement.id, jdbcStatement);

            return statement;
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public RStatement prepareCall(RConnection connection, String sql)
            throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            // Prepare call to stored procedure
            Statement jdbcStatement = jdbcConnection.prepareCall(sql);

            // Wrap and map statement
            RStatement statement = new RStatement();
            statement.id_connection = connection.id;
            synchronized (this) {
                statement.id = this.idStatementIncrement++;
            }
            this.statementById.put(statement.id, jdbcStatement);

            return statement;
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public void connection_commit(RConnection connection) throws RSQLException,
            TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            // Commit
            jdbcConnection.commit();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public void connection_rollback(RConnection connection)
            throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            // Rollback
            jdbcConnection.rollback();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public RStaticMetaData connection_getstaticmetadata(RConnection connection)
            throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            // Get metadata
            DatabaseMetaData jdbcMetadata = jdbcConnection.getMetaData();

            // Create metadata
            RStaticMetaData metadata = new RStaticMetaData(jdbcMetadata.getDatabaseMajorVersion(),
                    jdbcMetadata.getDatabaseMinorVersion(), jdbcMetadata.getDatabaseProductName(),
                    jdbcMetadata.getDatabaseProductVersion(), jdbcMetadata.getDefaultTransactionIsolation(),
                    jdbcMetadata.getIdentifierQuoteString(), jdbcMetadata.supportsCatalogsInTableDefinitions(),
                    jdbcMetadata.supportsSavepoints(), jdbcMetadata.supportsSchemasInDataManipulation(),
                    jdbcMetadata.supportsSchemasInTableDefinitions());

            return metadata;
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public boolean connection_isvalid(RConnection connection, int timeout)
            throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            return jdbcConnection.isValid(timeout);
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public void connection_setAutoCommit(RConnection connection,
            boolean autoCommit) throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            jdbcConnection.setAutoCommit(autoCommit);
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public boolean connection_getAutoCommit(RConnection connection)
            throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            return jdbcConnection.getAutoCommit();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public void connection_setTransactionIsolation(RConnection connection,
            int level) throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            jdbcConnection.setTransactionIsolation(level);
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public int connection_getTransactionIsolation(RConnection connection)
            throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            return jdbcConnection.getTransactionIsolation();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public void connection_setReadOnly(RConnection connection, boolean readOnly)
            throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            jdbcConnection.setReadOnly(readOnly);
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public boolean connection_getReadOnly(RConnection connection)
            throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            return jdbcConnection.isReadOnly();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public void connection_setCatalog(RConnection connection, String catalog)
            throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            jdbcConnection.setCatalog(catalog);
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public String connection_getCatalog(RConnection connection)
            throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            return jdbcConnection.getCatalog();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public void connection_setSchema(RConnection connection, String schema)
            throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            jdbcConnection.setSchema(schema);
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public String connection_getSchema(RConnection connection)
            throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            return jdbcConnection.getSchema();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public String connection_getCatalogSeparator(RConnection connection)
            throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            DatabaseMetaData jdbcMetadata = jdbcConnection.getMetaData();
            return jdbcMetadata.getCatalogSeparator();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public String connection_getCatalogTerm(RConnection connection)
            throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            DatabaseMetaData jdbcMetadata = jdbcConnection.getMetaData();
            return jdbcMetadata.getCatalogTerm();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public String connection_getSchemaTerm(RConnection connection)
            throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            DatabaseMetaData jdbcMetadata = jdbcConnection.getMetaData();
            return jdbcMetadata.getSchemaTerm();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public RResultSet connection_getCatalogs(RConnection connection)
            throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            // Get catalogs
            DatabaseMetaData jdbcMetadata = jdbcConnection.getMetaData();
            RResultSet resultSet;
            try (ResultSet res = jdbcMetadata.getCatalogs()) {
                resultSet = parseResultSet(res);
            }
            return resultSet;
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public RResultSet connection_getSchemas(RConnection connection,
            String catalog, String schemaPattern) throws RSQLException,
            TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            // Get schemas
            DatabaseMetaData jdbcMetadata = jdbcConnection.getMetaData();
            RResultSet resultSet;
            try (ResultSet res = jdbcMetadata.getSchemas(catalog, schemaPattern)) {
                resultSet = parseResultSet(res);
            }
            return resultSet;
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public RResultSet connection_getTables(RConnection connection,
            String catalog, String schemaPattern, String tableNamePattern,
            List<String> types) throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            // Get tables
            DatabaseMetaData jdbcMetadata = jdbcConnection.getMetaData();
            RResultSet resultSet;
            try (ResultSet res = jdbcMetadata.getTables(catalog, schemaPattern, tableNamePattern, types.toArray(new String[types.size()]))) {
                resultSet = parseResultSet(res);
            }
            return resultSet;
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public RResultSet connection_getColumns(RConnection connection,
            String catalog, String schemaPattern, String tableNamePattern,
            String columnNamePattern) throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            // Get columns
            DatabaseMetaData jdbcMetadata = jdbcConnection.getMetaData();
            RResultSet resultSet;
            try (ResultSet res = jdbcMetadata.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern)) {
                resultSet = parseResultSet(res);
            }
            return resultSet;
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public String connection_getSQLKeywords(RConnection connection)
            throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            // Get SQL keywords
            DatabaseMetaData jdbcMetadata = jdbcConnection.getMetaData();
            return jdbcMetadata.getSQLKeywords();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public RResultSet connection_getTableTypes(RConnection connection)
            throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            // Get tables types
            DatabaseMetaData jdbcMetadata = jdbcConnection.getMetaData();
            RResultSet resultSet;
            try (ResultSet res = jdbcMetadata.getTableTypes()) {
                resultSet = parseResultSet(res);
            }
            return resultSet;
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public RResultSet connection_getTypeInfo(RConnection connection)
            throws RSQLException, TException {

        Connection jdbcConnection = getConnection(connection);

        try {
            // Get type info
            DatabaseMetaData jdbcMetadata = jdbcConnection.getMetaData();
            RResultSet resultSet;
            try (ResultSet res = jdbcMetadata.getTypeInfo()) {
                resultSet = parseResultSet(res);
            }
            return resultSet;
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public void closeConnection(RConnection connection) throws RSQLException,
            TException {

        // Check connection already exists
        if (!this.connectionById.containsKey(connection.id)) {
            return;
        }

        Connection jdbcConnection = getConnection(connection);

        try {
            // Close connection
            jdbcConnection.close();

            // Remove connection and associated statements
            removeConnection(connection, true);

            LOGGER.info("Closing connection " + jdbcConnection.getMetaData().getURL());
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public void statement_close(RStatement statement) throws RSQLException,
            TException {

        // Check statement already exists
        if (!this.statementById.containsKey(statement.id)) {
            return;
        }

        Statement jdbcStatement = getStatement(statement);

        try {
            // Close statement
            jdbcStatement.close();

            // Remove statement
            removeStatement(statement);
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public boolean statement_execute(RStatement statement, String sql)
            throws RSQLException, TException {

        Statement jdbcStatement = getStatement(statement);

        try {
            // Execute statement
            return jdbcStatement.execute(sql);
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public RResultSet statement_executeQuery(RStatement statement, String sql)
            throws RSQLException, TException {

        Statement jdbcStatement = getStatement(statement);

        try {
            // Execute query
            ResultSet res = jdbcStatement.executeQuery(sql);
            RResultSet resultSet = parseResultSet(res);
            return resultSet;
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public int statement_executeUpdate(RStatement statement, String sql)
            throws RSQLException, TException {

        Statement jdbcStatement = getStatement(statement);

        try {
            // Execute update
            return jdbcStatement.executeUpdate(sql);
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public RResultSet statement_getResultSet(RStatement statement)
            throws RSQLException, TException {

        Statement jdbcStatement = getStatement(statement);

        try {
            RResultSet resultSet;
            try ( // Get result
                    ResultSet res = jdbcStatement.getResultSet()) {
                resultSet = parseResultSet(res);
            }
            return resultSet;
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public int statement_getUpdateCount(RStatement statement)
            throws RSQLException, TException {

        Statement jdbcStatement = getStatement(statement);

        try {
            // Get update count
            return jdbcStatement.getUpdateCount();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public int statement_getResultSetType(RStatement statement)
            throws RSQLException, TException {
        Statement jdbcStatement = getStatement(statement);

        try {
            // Get resultset type
            return jdbcStatement.getResultSetType();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public void statement_cancel(RStatement statement) throws RSQLException,
            TException {
        Statement jdbcStatement = getStatement(statement);

        try {
            // Cancel statement
            jdbcStatement.cancel();
        } catch (SQLException e) {
            throw new TException(e);
        }
    }

    @Override
    public statement_getWarnings_return statement_getWarnings(
            RStatement statement) throws RSQLException, TException {

        Statement jdbcStatement = getStatement(statement);

        try {
            // Get warnings
            SQLWarning sqlWarnings = jdbcStatement.getWarnings();
            statement_getWarnings_return warnings = new statement_getWarnings_return();

            if (sqlWarnings != null) {
                SQLWarning sqlwarn;
                while ((sqlwarn = sqlWarnings.getNextWarning()) != null) {
                    warnings.addToWarnings(new RSQLWarning(sqlwarn.getMessage(), sqlwarn.getSQLState(), sqlwarn.getErrorCode()));
                }
            }

            return warnings;
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public void statement_clearWarnings(RStatement statement)
            throws RSQLException, TException {

        Statement jdbcStatement = getStatement(statement);

        try {
            // Clear warnings
            jdbcStatement.clearWarnings();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public int statement_getMaxRows(RStatement statement) throws RSQLException,
            TException {

        Statement jdbcStatement = getStatement(statement);

        try {
            // Get max rows
            return jdbcStatement.getMaxRows();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public void statement_setMaxRows(RStatement statement, int max)
            throws RSQLException, TException {

        Statement jdbcStatement = getStatement(statement);

        try {
            // Clear warnings
            jdbcStatement.setMaxRows(max);
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public int statement_getQueryTimeout(RStatement statement)
            throws RSQLException, TException {

        Statement jdbcStatement = getStatement(statement);

        try {
            // Get query timeout
            return jdbcStatement.getQueryTimeout();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public void statement_setQueryTimeout(RStatement statement, int seconds)
            throws RSQLException, TException {

        Statement jdbcStatement = getStatement(statement);

        try {
            // Clear warnings
            jdbcStatement.setQueryTimeout(seconds);
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public void statement_addBatch(RStatement statement, String sql)
            throws RSQLException, TException {

        Statement jdbcStatement = getStatement(statement);

        try {
            // Add to batch
            jdbcStatement.addBatch(sql);
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public void statement_addBatches(RStatement statement, List<String> sqlList) throws RSQLException, TException {

        Statement jdbcStatement = getStatement(statement);

        try {
            // Add to batch
            for (String sql : sqlList) {
                jdbcStatement.addBatch(sql);
            }
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public void statement_clearBatch(RStatement statement)
            throws RSQLException, TException {

        Statement jdbcStatement = getStatement(statement);

        try {
            // Add to batch
            jdbcStatement.clearBatch();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<Integer> statement_executeBatch(RStatement statement)
            throws RSQLException, TException {

        Statement jdbcStatement = getStatement(statement);

        try {
            // Add to batch
            int[] res = jdbcStatement.executeBatch();

            ArrayList<Integer> ret = new ArrayList<>();
            for (int i = 0; i < res.length; ++i) {
                ret.add(i);
            }
            return ret;
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    private void setParameterPreparedStatement(PreparedStatement jdbcStatement, int position,
            RValueSQL valueSQL) throws SQLException {

        if (valueSQL.isIsnull()) {
            jdbcStatement.setNull(position, valueSQL.getSqlType());
            return;
        }

        switch (valueSQL.getSqlType()) {
            case java.sql.Types.INTEGER:
                jdbcStatement.setInt(position, valueSQL.getVal().getInteger_val());
                break;
            case java.sql.Types.BIGINT:
                jdbcStatement.setLong(position, valueSQL.getVal().getBigint_val());
                break;
            case java.sql.Types.SMALLINT:
                jdbcStatement.setShort(position, valueSQL.getVal().getSmallint_val());
                break;
            case java.sql.Types.TINYINT:
                jdbcStatement.setByte(position, valueSQL.getVal().getTinyint_val());
                break;
            case java.sql.Types.FLOAT:
            case java.sql.Types.DOUBLE:
                jdbcStatement.setDouble(position, valueSQL.getVal().getDouble_val());
                break;
            case java.sql.Types.DECIMAL:
                jdbcStatement.setBigDecimal(position,
                        new BigDecimalStringConverter().fromString(valueSQL.getVal().getString_val()));
                break;
            case java.sql.Types.BOOLEAN:
                jdbcStatement.setBoolean(position, valueSQL.getVal().getBool_val());
                break;
            case java.sql.Types.TIME:
            case java.sql.Types.DATE:
            case java.sql.Types.TIMESTAMP:
                jdbcStatement.setDate(position, new Date(valueSQL.getVal().getBigint_val()));
                break;
            case java.sql.Types.VARCHAR:
            case java.sql.Types.CHAR:
                jdbcStatement.setString(position, valueSQL.getVal().getString_val());
                break;
            case java.sql.Types.VARBINARY:
            case java.sql.Types.BINARY:
                jdbcStatement.setBytes(position, valueSQL.getVal().getBinary_val());
                break;
            case java.sql.Types.ARRAY:
                jdbcStatement.setArray(position, createArray(jdbcStatement.getConnection(), valueSQL.getVal().getArray_val()));
                break;
            default:
                throw new SQLException("Type not supported by jdbcRPC: " + valueSQL.getSqlType());
        }
    }

    private java.sql.Array createArray(Connection connection, ArrayVal arrayVal)
            throws SQLException {

        int length = arrayVal.elements.size();

        Object[] rawArray = new Object[length];
        for (int i = 0; i < length; ++i) {
            rawArray[i] = arrayVal.elements.get(i).getFieldValue();
        }

        switch (arrayVal.getSqlType()) {
            case java.sql.Types.INTEGER:
                return connection.createArrayOf("INTEGER", rawArray);
            case java.sql.Types.BIGINT:
                return connection.createArrayOf("BIGINT", rawArray);
            case java.sql.Types.SMALLINT:
                return connection.createArrayOf("SMALLINT", rawArray);
            case java.sql.Types.TINYINT:
                return connection.createArrayOf("TINYINT", rawArray);
            case java.sql.Types.FLOAT:
            case java.sql.Types.DOUBLE:
                return connection.createArrayOf("DOUBLE", rawArray);
            case java.sql.Types.DECIMAL:
                return connection.createArrayOf("DECIMAL", rawArray);
            case java.sql.Types.BOOLEAN:
                return connection.createArrayOf("BOOLEAN", rawArray);
            case java.sql.Types.TIME:
            case java.sql.Types.DATE:
            case java.sql.Types.TIMESTAMP:
                return connection.createArrayOf("TIMESTAMP", rawArray);
            case java.sql.Types.VARCHAR:
            case java.sql.Types.CHAR:
                return connection.createArrayOf("VARCHAR", rawArray);
            case java.sql.Types.BINARY:
                return connection.createArrayOf("BINARY", rawArray);
            default:
                throw new SQLException("Type not supported by jdbcRPC: " + arrayVal.getSqlType());
        }
    }

    private void setParametersPreparedStatement(PreparedStatement jdbcStatement,
            List<RValueSQL> parameters) throws SQLException {

        // Set parameters to statement
        int size = parameters.size();
        for (int i = 1; i <= size; ++i) {
            setParameterPreparedStatement(jdbcStatement, i, parameters.get(i - 1));
        }
    }

    @Override
    public void preparedstatement_setParameters(RStatement preparedStatement,
            List<RValueSQL> parameters) throws RSQLException, TException {

        PreparedStatement jdbcStatement = getPreparedStatement(preparedStatement);
        try {
            setParametersPreparedStatement(jdbcStatement, parameters);
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public void preparedstatement_setParameter(RStatement preparedStatement,
            RValueSQL parameter, int position) throws RSQLException, TException {

        PreparedStatement jdbcStatement = getPreparedStatement(preparedStatement);

        try {
            // Set parameter to statement
            setParameterPreparedStatement(jdbcStatement, position, parameter);
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public boolean preparedstatement_execute(RStatement preparedstatement)
            throws RSQLException, TException {

        PreparedStatement jdbcStatement = getPreparedStatement(preparedstatement);

        try {
            // Execute statement
            return jdbcStatement.execute();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public RResultSet preparedstatement_executeQuery(
            RStatement preparedstatement) throws RSQLException, TException {

        PreparedStatement jdbcStatement = getPreparedStatement(preparedstatement);

        try {
            // Execute query
            ResultSet res = jdbcStatement.executeQuery();
            return parseResultSet(res);
        } catch (SQLException sqlEx) {

            sqlEx.printStackTrace();
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public int preparedstatement_executeUpdate(RStatement preparedstatement)
            throws RSQLException, TException {

        PreparedStatement jdbcStatement = getPreparedStatement(preparedstatement);

        try {
            // Execute update
            return jdbcStatement.executeUpdate();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public void preparedstatement_clearParameters(RStatement preparedStatement)
            throws RSQLException, TException {

        PreparedStatement jdbcStatement = getPreparedStatement(preparedStatement);

        try {
            // Clear parameters
            jdbcStatement.clearParameters();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public void preparedstatement_addBatchWithParameters(RStatement preparedStatement,
            List<RValueSQL> parameters) throws RSQLException, TException {

        PreparedStatement jdbcStatement = getPreparedStatement(preparedStatement);

        try {
            // Set parameters to statement
            int size = parameters.size();
            for (int i = 1; i <= size; ++i) {
                setParameterPreparedStatement(jdbcStatement, i, parameters.get(i - 1));
            }

            // Add batch
            jdbcStatement.addBatch();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public void preparedstatement_addBatch(RStatement preparedStatement)
            throws RSQLException, TException {

        PreparedStatement jdbcStatement = getPreparedStatement(preparedStatement);

        try {
            // Add batch
            jdbcStatement.addBatch();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public boolean preparedstatement_setParametersThenExecute(RStatement preparedstatement,
            List<RValueSQL> parameters) throws RSQLException, TException {

        PreparedStatement jdbcStatement = getPreparedStatement(preparedstatement);

        try {
            // Set parameters
            setParametersPreparedStatement(jdbcStatement, parameters);
            // Execute statement
            return jdbcStatement.execute();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public RResultSet preparedstatement_setParametersThenExecuteQuery(RStatement preparedstatement,
            List<RValueSQL> parameters) throws RSQLException, TException {

        PreparedStatement jdbcStatement = getPreparedStatement(preparedstatement);

        try {
            // Set parameters
            setParametersPreparedStatement(jdbcStatement, parameters);
            // Execute statement
            ResultSet res = jdbcStatement.executeQuery();
            return parseResultSet(res);
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public int preparedstatement_setParametersThenExecuteUpdate(RStatement preparedstatement,
            List<RValueSQL> parameters) throws RSQLException, TException {

        PreparedStatement jdbcStatement = getPreparedStatement(preparedstatement);

        try {
            // Set parameters
            setParametersPreparedStatement(jdbcStatement, parameters);
            // Execute statement
            return jdbcStatement.executeUpdate();
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<Integer> preparedstatement_executeBatch(RStatement statement,
            List<List<RValueSQL>> parameters) throws RSQLException, TException {

        //PreparedStatement jdbcStatement = getPreparedStatement(statement);
        PreparedStatement jdbcStatement = (PreparedStatement) this.statementById.get(statement.id);

        try {
            // Add batches
            for (List<RValueSQL> params : parameters) {
                // Set parameters to statement
                int size = params.size();
                for (int i = 1; i <= size; ++i) {
                    setParameterPreparedStatement(jdbcStatement, i, params.get(i - 1));
                }

                // Add batch
                jdbcStatement.addBatch();
            }

            // Execute batch
            int[] res = jdbcStatement.executeBatch();

            ArrayList<Integer> ret = new ArrayList<>();
            for (int i = 0; i < res.length; ++i) {
                ret.add(i);
            }
            return ret;
        } catch (SQLException sqlEx) {
            throw new RSQLException(sqlEx.getMessage(), sqlEx.getSQLState(), sqlEx.getErrorCode());
        } catch (Exception e) {
            throw new TException(e);
        }
    }
}
