package repositories;

import entities.User;
import utils.BaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class UserRepository implements IBaseRepository<User> {
    private final Connection connection;
    private static final Logger logger = Logger.getLogger(UserRepository.class.getName());
    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> readTable() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM \"user\";";

        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                logger.info("0 rows selected");
            }
            while (rs.next()) {
                users.add(new User(UUID.fromString(rs.getString("id")),
                                     rs.getString("name"),
                                     rs.getString("surname"),
                                     rs.getString("email"),
                                     rs.getDate("birth_date"),
                                     rs.getString("password_hash")));
            }
        } catch (SQLException e) {
            BaseUtil.printSQLException(e);
        }
        return users;
    }

    @Override
    public void create(User user) {
        var userID = BaseUtil.generateUUID();

        String queryUser = String.format("INSERT INTO \"user\" VALUES ('%s', '%s', '%s', '%s', '%s', '%s');",
                userID, user.getName(), user.getSurname(), user.getEmail(),
                user.getBirthDate().toString(), user.getPasswordHash());

        StringBuilder queryUserRole = new StringBuilder("INSERT INTO user_role VALUES");
        for (UUID roleID : user.getRoles()) {
            queryUserRole.append(String.format("('%s', '%s'), ", userID, roleID.toString()));
        }
        queryUserRole = new StringBuilder(queryUserRole.substring(0, queryUserRole.length() - 2));
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(queryUser);
            statement.executeUpdate(queryUserRole.toString());
            logger.info("Records created.");
        } catch (SQLException e) {
            BaseUtil.printSQLException(e);
        }
    }

    @Override
    public void update(User user) {
        String query = String.format("UPDATE \"user\" SET name = '%s', surname = '%s', email = '%s', birth_date = '%s' WHERE id = '%s'",
                user.getName(), user.getSurname(), user.getEmail(),
                user.getBirthDate().toString(), user.getId().toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            logger.info("Record updated.");
        } catch (SQLException e) {
            BaseUtil.printSQLException(e);
        }
    }

    @Override
    public void delete(UUID id) {
        String queryUser = String.format("DELETE FROM \"user\" WHERE id = '%s';", id.toString());
        String queryUserRole = String.format("DELETE FROM user_role WHERE id_user = '%s';", id);

        try (var statement = connection.createStatement()) {
            statement.executeUpdate(queryUser);
            statement.executeUpdate(queryUserRole);
            logger.info("Records deleted.");
        } catch (SQLException e) {
            BaseUtil.printSQLException(e);
        }
    }

}
