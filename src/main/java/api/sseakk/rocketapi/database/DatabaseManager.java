package api.sseakk.rocketapi.database;

import api.sseakk.rocketapi.RocketAPI;
import api.sseakk.rocketapi.util.MessageUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.HashMap;

/**
 * Handles the connection and disconnection of databases.
 * @author sSeaKk
 * @version 1.0
 * @since RocketAPI-1.3
 */
public class DatabaseManager {
    private final MessageUtil messages;
    private final HashMap<String, Database> dbmap;

    /**
     * Init DatabaseManager.
     * @since RocketAPI-1.3
     * @param plugin RocketAPI plugin
     */
    public DatabaseManager(RocketAPI plugin){
        this.messages = plugin.getMessages();
        this.dbmap = new HashMap<>();
    }

    /**
     * Connects to a database.
     * @since RocketAPI-1.3
     * @param server server address
     * @param dbName database name
     * @param user username
     * @param password password
     * @return database if connected, null instead
     */
    public Database connectDatabase(String server, String dbName, String user, String password){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://"+server+"/"+dbName, user, password);
            Database database = new Database(dbName, connection);

            this.dbmap.put(dbName, database);
            this.messages.databaseLog(database, "Successfully connected to " + database.getName() + " database.");
            return database;
        } catch (SQLException e) {
            this.messages.databaseConnectionError(dbName);
        }

        return null;
    }

    /**
     * Connects to a database, with root login credentials.
     * @since RocketAPI-1.3
     * @param server server address
     * @param dbName database name
     * @return database if connected, null instead
     */
    public Database connectDatabase(String server, String dbName){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://"+server+"/"+dbName, "root", "");
            Database database = new Database(dbName, connection);

            this.dbmap.put(dbName, database);
            this.messages.databaseLog(database, "Successfully connected to " + database.getName() + " database.");
            return database;
        } catch (SQLException e) {
            this.messages.databaseConnectionError(dbName);
        }

        return null;
    }

    /**
     * Disconnects from a database.
     * @since RocketAPI-1.3
     * @param database database to disconnect
     * @return true if disconnected, false instead
     */
    public boolean disconnectDatabase(Database database){
        try {
            database.getConnection().close();
            this.dbmap.remove(database.getName());
            this.messages.databaseLog(database, "Successfully disconnected from " + database.getName() + " database.");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}