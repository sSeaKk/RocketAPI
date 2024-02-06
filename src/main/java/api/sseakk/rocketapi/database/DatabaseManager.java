package api.sseakk.rocketapi.database;

import api.sseakk.rocketapi.RocketAPI;
import api.sseakk.rocketapi.util.MessageUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.HashMap;

public class DatabaseManager {
    private RocketAPI plugin;
    private MessageUtil messages;
    private HashMap<String, Database> dbmap;

    public DatabaseManager(RocketAPI plugin){
        this.plugin = plugin;
        this.messages = this.plugin.getMessages();
        this.dbmap = new HashMap<>();
    }

    public Database connectDatabase(String server, String dbName, String user, String password){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://"+server+"/"+dbName, user, password);
            Database database = new Database(dbName, connection);

            this.dbmap.put(dbName, database);
            this.messages.databaseLog(database, "Succesfully connected to " + database.getName() + " database.");
            return database;
        } catch (SQLException e) {
            this.messages.databaseConnectionError(dbName);
        }

        return null;
    }

    public Database connectDatabase(String server, String dbName){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://"+server+"/"+dbName, "root", "");
            Database database = new Database(dbName, connection);

            this.dbmap.put(dbName, database);
            this.messages.databaseLog(database, "Succesfully connected to " + database.getName() + " database.");
            return database;
        } catch (SQLException e) {
            this.messages.databaseConnectionError(dbName);
        }

        return null;
    }

    public boolean disconnectDatabase(Database database){
        try {
            database.getConnection().close();
            this.dbmap.remove(database.getName());
            this.messages.databaseLog(database, "Succesfully disconnected from " + database.getName() + " database.");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}