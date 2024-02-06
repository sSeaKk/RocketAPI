package api.sseakk.rocketapi.database;

import api.sseakk.rocketapi.RocketAPI;


public abstract class Table {
    private String name;
    private Database database;
    private String[] columns;

    protected Table(Database database, String name, String... columns) {
        this.name = name;
        this.database = database;
        this.columns = columns;

        this.database.addTable(this);
    }

    public String getName() {
        return name;
    }

    public String getDatabaseName() {
        return this.database.getName();
    }

    public String[] getColumns() {
        return columns;
    }

    public Database getDatabase() {
        return database;
    }

    public void log(String message){
        RocketAPI.getInstance().getMessages().tableLog(this, message);
    }
}