package api.sseakk.rocketapi.database;

import api.sseakk.rocketapi.RocketAPI;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private String name;
    private Connection connection;
    private Map<String, Table> tables;

    protected Database(String name, Connection connection){
        this.name = name;
        this.connection = connection;
        this.tables = new HashMap<>();
        loadTables();
    }

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

    public void loadTables(){
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

    public Table getTable(String table){
        return this.tables.get(table.toLowerCase());
    }

    public void removeTable(Table table){
        this.tables.remove(table.getName());
    }

    public String getName(){ return this.name; }

    public Connection getConnection(){ return this.connection; }

    private void log(String message){
        RocketAPI.getInstance().getMessages().databaseLog(this, message);
    }

}