package jdbcrpc.server;

import java.io.File;
import java.nio.file.Files;
import java.sql.SQLException;
import jdbcrpc.core.JDBCServiceHandler;
import jdbcrpc.thrift.RjdbcService;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {

    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class.getName());

    public static void main(String[] args) {

        final JDBCServiceHandler handler = new JDBCServiceHandler();
        try {
            File jarDir = new File(Server.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile();
            LOGGER.info("Running directory " + jarDir);
            File confFile = new File(jarDir, "server.properties");
            if (!Files.exists(confFile.toPath())) {
                LOGGER.error("Configuration file not found");
                return;
            }
            PropertiesConfiguration config = new PropertiesConfiguration(confFile);
            int port = config.getInt("port");
            int maxWorkerThread = config.containsKey("maxWorkerThread") ? config.getInt("maxWorkerThread") : Integer.MAX_VALUE;
            String[] drivers = config.getStringArray("drivers");
            for (String d : drivers) {
                java.lang.Class.forName(d);
            }

            RjdbcService.Processor<JDBCServiceHandler> processor = new RjdbcService.Processor<>(handler);

            TServerTransport serverTransport = new TServerSocket(port);
            TThreadPoolServer.Args serverArgs = new TThreadPoolServer.Args(serverTransport).processor(processor);
            serverArgs.minWorkerThreads(10);
            serverArgs.protocolFactory(new TCompactProtocol.Factory());
            serverArgs.maxWorkerThreads(maxWorkerThread);           
            TServer server = new TThreadPoolServer(serverArgs);

            LOGGER.info("Starting the JDBC thrift server (listening on port " + port + ")...");
            LOGGER.info("Thread pool min = " + serverArgs.minWorkerThreads + " max = " + serverArgs.maxWorkerThreads);
            server.serve();
        } catch (ConfigurationException | ClassNotFoundException | TTransportException ex) {
            LOGGER.error("JDBC thrift Server", ex);
        } finally {
            try {
                handler.dispose();
            } catch (SQLException ex) {
                LOGGER.error("JDBC thrift Server", ex);
            }
        }
    }
}
