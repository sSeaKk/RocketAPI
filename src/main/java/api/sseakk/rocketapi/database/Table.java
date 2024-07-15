package api.sseakk.rocketapi.database;

import api.sseakk.rocketapi.RocketAPI;

/**
 * Handles the creation of a table in a database.
 * @author sSeaKk
 * @version 1.0
 * @since RocketAPI-1.3
 */
public abstract class Table {
    private final String name;
    private final Database database;
    private final String[] columns;

    /**
     * Creates a new table in the database.
     * @since RocketAPI-1.3
     * @param database database to create the table in
     * @param name name of the table
     * @param columns columns names of the table
     */
    protected Table(Database database, String name, String... columns) {
        this.name = name.toLowerCase();
        this.database = database;
        this.columns = columns;

        this.database.addTable(this);
    }

    /**
     * Returns the name of the table.
     * @since RocketAPI-1.3
     * @return name of the table
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the name of the database.
     * @since RocketAPI-1.3
     * @return name of the database
     */
    public String getDatabaseName() {
        return this.database.getName();
    }

    /**
     * Returns the columns of the table.
     * @since RocketAPI-1.3
     * @return columns of the table
     */
    public String[] getColumns() {
        return columns;
    }

    /**
     * Returns the database of the table.
     * @since RocketAPI-1.3
     * @return database of the table
     */
    public Database getDatabase() {
        return database;
    }

    /**
     * Logs a message to the console.
     * @since RocketAPI-1.3
     * @param message message to log from table
     */
    public void log(String message){
        RocketAPI.getInstance().getMessages().tableLog(this, message);
    }
}