package repositories;

import utils.BaseUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class BaseRepository {
    private BaseRepository() {}

    private static final Logger logger = Logger.getLogger(BaseRepository.class.getName());

    public static void executeQuery(Connection connection, String query, String message) {
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            logger.info(message);
        } catch (SQLException e) {
            BaseUtil.printSQLException(e);
        }
    }

    public static void executeQuery(Connection connection, String query1,  String query2, String message) {
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query1);
            statement.executeUpdate(query2);
            logger.info(message);
        } catch (SQLException e) {
            BaseUtil.printSQLException(e);
        }
    }
}
