import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import databaseconfig.java.DatabaseConfig;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static Connection connect() {

        try {
            // Get database credentials from DatabaseConfig class
            var jdbcUrl = DatabaseConfig.getDbUrl();
            var user = DatabaseConfig.getDbUsername();
            var password = DatabaseConfig.getDbPassword();

            // Open a connection
            return DriverManager.getConnection(jdbcUrl, user, password);
        } catch (SQLException  e) {
            logger.warning(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) throws SQLException {
        CRUD db = new CRUD(connect());
        logger.info("Connected to the PostgreSQL database.");

        db.readRoleTable();
    }
}

