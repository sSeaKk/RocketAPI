package api.sseakk.rocketapi.database;

import api.sseakk.rocketapi.RocketAPI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
    private String name;
    private Connection connection;
    private ArrayList<Table> tables;

    protected Database(String name, Connection connection){
        this.name = name;
        this.connection = connection;
        this.tables = new ArrayList<>();
    }

    public Table getTable(String table){
        for(Table t : this.tables){
            if(t.getName().equalsIgnoreCase(table)){
                return t;
            }
        }

        return null;
    }

    public Table addTable(Table table){
        StringBuilder arguments = new StringBuilder();

        for(Table t : this.tables){
            if(t.getName().equalsIgnoreCase(table.getName())){
                RocketAPI.getInstance().getMessages().tableLog(t,table + " already exist!");
                return t;
            }
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
            this.tables.add(table);
            RocketAPI.getInstance().getMessages().tableLog(table, "Table created.");
            return table;
        } catch (SQLException e) {
            RocketAPI.getInstance().getMessages().databaseWarning(this, "Cannot create Table '"+table+"'.");
            e.printStackTrace();
            return null;
        }
    }

    public void removeTable(Table table){
        this.tables.remove(table);
    }

    public String getName(){ return this.name; }

    public Connection getConnection(){ return this.connection; }

    private void log(String message){
        RocketAPI.getInstance().getMessages().databaseLog(this, message);
    }

}