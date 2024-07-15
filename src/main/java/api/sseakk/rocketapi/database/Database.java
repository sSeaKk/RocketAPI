package api.sseakk.rocketapi.database;

import api.sseakk.rocketapi.RocketAPI;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles the creation of a database.
 * @author sSeaKk
 * @version 1.0
 * @since RocketAPI-1.3
 */
public class Database {
    private final String name;
    private final Connection connection;
    private final Map<String, Table> tables;

    /**
     * Creates a new database.
     * @since RocketAPI-1.3
     * @param name name of the database
     * @param connection connection to the database
     */
    protected Database(String name, Connection connection){
        this.name = name;
        this.connection = connection;
        this.tables = new HashMap<>();
        loadTables();
    }

    /**
     * Adds a new table in the database.
     * @since RocketAPI-1.3
     * @param table table to add
     * @return table if added, null instead
     */
    public Table addTable(Table table){
        StringBuilder arguments = new StringBuilder();

        if(this.tables.containsKey(table.getName()) && this.tables.get(table.getName()) == null) {
            this.tables.replace(table.getName(), table);
            return table;
        }

        if(this.tables.get(table.getName()) != null){
            RocketAPI.getInstance().getMessages().tableLog(this.tables.get(table.getName()),table.getName() + " already exist!");
            return this.tables.get(table.getName());
        }

        for(int i=0;i<table.getColumns().length;i++){
            String[] args = table.getColumns();
            arguments.append(args[i]);
            if(i!=(args.length-1)) arguments.append(", ");
        }

        try {
            PreparedStatement ps = this.connection.prepareStatement("CREATE TABLE " + table.getName() + "(" + arguments + ")");
            ps.execute();
            ps.close();
            this.tables.put(table.getName(), table);
            RocketAPI.getInstance().getMessages().tableLog(table, "Table created.");
            return table;
        } catch (SQLException e) {
            RocketAPI.getInstance().getMessages().databaseWarning(this, "Cannot create Table '"+table.getName()+"'.");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Load all tables from the database.
     * @since RocketAPI-1.4
     */
    private void loadTables(){
        try {
            DatabaseMetaData md = this.connection.getMetaData();
            ResultSet rs = md.getTables(this.name, null, "%", null);
            while (rs.next()) {
                this.tables.put(rs.getString(3), null);
                this.log(" Loaded '"+rs.getString(3)+"' table.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get a table from the database.
     * @since RocketAPI-1.3
     * @param table name of the table
     * @return table if found, null instead
     */
    public Table getTable(String table){
        return this.tables.get(table.toLowerCase());
    }

    /**
     * Remove a table from the database.
     * @since RocketAPI-1.3
     * @param table table to remove
     */
    public void removeTable(Table table){
        this.tables.remove(table.getName());
    }

    /**
     * Get the name of the database.
     * @since RocketAPI-1.3
     * @return name of the database
     */
    public String getName(){
        return this.name;
    }

    /**
     * Get the connection to the database.
     * @return connection to the database
     */
    public Connection getConnection(){
        return this.connection;
    }

    /**
     * Log a message to the console.
     * @param message message to log from database
     */
    private void log(String message){
        RocketAPI.getInstance().getMessages().databaseLog(this, message);
    }

}