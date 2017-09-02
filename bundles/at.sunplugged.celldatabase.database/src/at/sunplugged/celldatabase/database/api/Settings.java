package at.sunplugged.celldatabase.database.api;

/**
 * Holds all settings Strings required for the database that are save in the
 * Eclipse context.
 * 
 * @author Jascha Riedel
 *
 */
public class Settings {

	public static final String DRIVER = "databaseDriver";

	public static final String DRIVER_HSQL = "HsqlDb";

	public static final String DRIVER_SQL_EXPRESS = "SQLExpress";

	public static final String PASSWORD = "databasePassword";

	public static final String USERNAME = "databaseUsername";

	public static final String ADDRESS = "databaseAddress";

	public static final String DATABASE = "databaseDatabaseName";

	private Settings() {

	}
}
