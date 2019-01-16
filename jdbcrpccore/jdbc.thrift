namespace * jdbcrpc.thrift

struct RConnection
{
  1: i32 id
}

struct RStatement
{
  1: i32 id, 
  2: string sql
  3: i32 id_connection
}

struct RProperty
{
  1: string key,
  2: string value
}

union RawVal
{
  1: i64 bigint_val,
  2: i32 integer_val,
  3: i16 smallint_val,
  4: byte tinyint_val,
  5: double double_val,
  6: bool bool_val,
  7: string string_val,
  8: byte msfromepoch_val,
  9: binary binary_val,
  10: ArrayVal array_val
}

struct RValue
{
  1: bool isnull,
  2: RawVal val
}

struct RValueSQL
{
  1: bool isnull,
  2: RawVal val
  3: i32 sqlType
}
  
struct RRow
{
  1: list<RValue> values
}

struct ArrayVal
{
  1: i32 sqlType
  2: list<RawVal> elements
}

struct RResultSetMetaDataPart 
{
  1:  string catalogName,
  2:  string columnClassName,
  3:  i32 columnDisplaySize,
  4:  string columnLabel,
  5:  string columnName,
  6:  i32 columnType,
  7:  string columnTypeName,
  8:  i32 precision,
  9:  i32 scale,
  10:  string schemaName,
  11:  string tableName,
  12:  bool autoIncrement,
  13:  bool caseSensitive,
  14:  bool currency,
  15:  bool definitelyWritable,
  16:  i32 nullable,
  17:  bool readOnly,
  18:  bool searchable,
  19:  bool signed,
  20:  bool writable
}

struct RResultSetMetaData
{
  1: list<RResultSetMetaDataPart> parts
}

struct RResultSet
{
  1: i32 id,
  2: list<RRow> rows,
  3: RResultSetMetaData metadata
}

struct RStaticMetaData
{
  1: i32 databaseMajorVersion,
  2: i32 databaseMinorVersion,
  3: string databaseProductName,
  4: string databaseProductVersion,
  5: i32 defaultTransactionIsolation,
  6: string identifierQuoteString,
  7: bool supportsCatalogsInTableDefinitions,
  8: bool supportsSavepoints,
  9: bool supportsSchemasInDataManipulation,
  10: bool supportsSchemasInTableDefinitions
}

struct RSQLWarning
{
  1: string reason
  2: string state
  3: i32 vendorCode
}

struct statement_getWarnings_return
{
  1: list<RSQLWarning> warnings
}

exception RSQLException
{
  1: string reason
  2: string sqlState
  3: i32 vendorCode
}

service RjdbcService
{
   RConnection createConnection(1:string url, 2:map<string,string> properties) throws (1:RSQLException sqlException)
   
   RStatement createStatement(1:RConnection connection) throws (1:RSQLException sqlException)
   RStatement prepareStatement(1:RConnection connection, 2:string sql) throws (1:RSQLException sqlException)
   RStatement prepareCall(1:RConnection connection, 2:string sql) throws (1:RSQLException sqlException)
   
   RStaticMetaData connection_getstaticmetadata(1:RConnection connection) throws (1:RSQLException sqlException)
   bool connection_isvalid(1:RConnection connection, 2:i32 timeout) throws (1:RSQLException sqlException)
   
   void connection_commit(1:RConnection connection) throws (1:RSQLException sqlException)
   void connection_rollback(1:RConnection connection) throws (1:RSQLException sqlException)
   void connection_setAutoCommit(1:RConnection connection, 2:bool autoCommit) throws (1:RSQLException sqlException)
   bool connection_getAutoCommit(1:RConnection connection) throws (1:RSQLException sqlException)
   void connection_setTransactionIsolation(1:RConnection connection, 2:i32 level) throws (1:RSQLException sqlException)
   i32 connection_getTransactionIsolation(1:RConnection connection) throws (1:RSQLException sqlException)
   void connection_setReadOnly(1:RConnection connection, 2:bool readOnly) throws (1:RSQLException sqlException)
   bool connection_getReadOnly(1:RConnection connection) throws (1:RSQLException sqlException)
   
   void connection_setCatalog(1:RConnection connection, 2:string catalog) throws (1:RSQLException sqlException)
   string connection_getCatalog(1:RConnection connection) throws (1:RSQLException sqlException)
   void connection_setSchema(1:RConnection connection, 2:string schema) throws (1:RSQLException sqlException)
   string connection_getSchema(1:RConnection connection) throws (1:RSQLException sqlException)
   
   string connection_getCatalogSeparator(1:RConnection connection) throws (1:RSQLException sqlException)
   string connection_getCatalogTerm(1:RConnection connection) throws (1:RSQLException sqlException)
   string connection_getSchemaTerm(1:RConnection connection) throws (1:RSQLException sqlException)
   
   RResultSet connection_getCatalogs(1:RConnection connection) throws (1:RSQLException sqlException)
   RResultSet connection_getSchemas(1:RConnection connection, 2:string catalog, 3:string schemaPattern) throws (1:RSQLException sqlException)
   RResultSet connection_getTables(1:RConnection connection, 2:string catalog, 3:string schemaPattern, 4:string tableNamePattern, 5:list<string> types) throws (1:RSQLException sqlException)
   RResultSet connection_getColumns(1:RConnection connection, 2:string catalog, 3:string schemaPattern, 4:string tableNamePattern, 5:string columnNamePattern) throws (1:RSQLException sqlException)
   string connection_getSQLKeywords(1:RConnection connection) throws (1:RSQLException sqlException)
   RResultSet connection_getTableTypes(1:RConnection connection)  throws (1:RSQLException sqlException)
   
   RResultSet connection_getTypeInfo(1:RConnection connection) throws (1:RSQLException sqlException)

   void closeConnection(1:RConnection connection) throws (1:RSQLException sqlException)
   
   void statement_close(1:RStatement statement) throws (1:RSQLException sqlException)
   bool statement_execute(1:RStatement statement, 2:string sql) throws (1:RSQLException sqlException)
   RResultSet statement_executeQuery(1:RStatement statement, 2:string sql) throws (1:RSQLException sqlException)
   i32 statement_executeUpdate(1:RStatement statement, 2:string sql) throws (1:RSQLException sqlException)
   RResultSet statement_getResultSet(1:RStatement statement) throws (1:RSQLException sqlException)
   i32 statement_getUpdateCount(1:RStatement statement)  throws (1:RSQLException sqlException)
   i32 statement_getResultSetType(1:RStatement statement)  throws (1:RSQLException sqlException)
   void statement_cancel(1:RStatement statement) throws (1:RSQLException sqlException)
   void statement_addBatch(1:RStatement statement, 2:string sql) throws (1:RSQLException sqlException)
   void statement_addBatches(1:RStatement statement, 2:list<string> sqlList) throws (1:RSQLException sqlException)
   void statement_clearBatch(1:RStatement statement) throws (1:RSQLException sqlException)
   list<i32> statement_executeBatch(1:RStatement statement) throws (1:RSQLException sqlException)
   
   bool preparedstatement_execute(1:RStatement preparedstatement) throws (1:RSQLException sqlException)
   RResultSet preparedstatement_executeQuery(1:RStatement preparedstatement) throws (1:RSQLException sqlException)
   i32 preparedstatement_executeUpdate(1:RStatement preparedstatement) throws (1:RSQLException sqlException)
   bool preparedstatement_setParametersThenExecute(1:RStatement preparedstatement, 2:list<RValueSQL> parameters) throws (1:RSQLException sqlException)
   RResultSet preparedstatement_setParametersThenExecuteQuery(1:RStatement preparedstatement, 2:list<RValueSQL> parameters) throws (1:RSQLException sqlException)
   i32 preparedstatement_setParametersThenExecuteUpdate(1:RStatement preparedstatement, 2:list<RValueSQL> parameters) throws (1:RSQLException sqlException)
   void preparedstatement_setParameters(1:RStatement preparedStatement, 2:list<RValueSQL> parameters)  throws (1:RSQLException sqlException)
   void preparedstatement_setParameter(1:RStatement preparedStatement, 2:RValueSQL parameter, 3:i32 position)  throws (1:RSQLException sqlException)
   void preparedstatement_clearParameters(1:RStatement preparedStatement)  throws (1:RSQLException sqlException)
   void preparedstatement_addBatchWithParameters(1:RStatement preparedStatement, 2:list<RValueSQL> parameters) throws (1:RSQLException sqlException)
   void preparedstatement_addBatch(1:RStatement preparedStatement) throws (1:RSQLException sqlException)
   list<i32> preparedstatement_executeBatch(1:RStatement statement, 2:list<list<RValueSQL>> parameters) throws (1:RSQLException sqlException)
	 
   statement_getWarnings_return statement_getWarnings(1:RStatement statement) throws (1:RSQLException sqlException)
   void statement_clearWarnings(1:RStatement statement) throws (1:RSQLException sqlException)
   
   i32 statement_getMaxRows(1:RStatement statement) throws (1:RSQLException sqlException)
   void statement_setMaxRows(1:RStatement statement, 2:i32 max) throws (1:RSQLException sqlException)
   i32 statement_getQueryTimeout(1:RStatement statement) throws (1:RSQLException sqlException)
   void statement_setQueryTimeout(1:RStatement statement, 2:i32 seconds) throws (1:RSQLException sqlException)
}
