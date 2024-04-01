package repositories;

import entities.User;
import utils.Base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class UserRepository {
    private final Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<User> readUserTable() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM user;";

        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                users.add(new User(UUID.fromString(rs.getString("id")),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        rs.getDate("birth_date"),
                        rs.getString("password_hash")));

                System.out.println(STR."\{rs.getString("id")}, \{rs.getString("name")}, \{rs.getString("surname")}, \{rs.getString("email")}, \{rs.getDate("birth_date")}");
            }
        } catch (SQLException e) {
            Base.printSQLException(e);
        }

        return users;
    }

    public UUID getUserID(String email) throws SQLException {
        String query = String.format("SELECT id FROM user WHERE email = '%s';", email);
        UUID user = null;
        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                user = UUID.fromString(rs.getString("id"));
            }
        } catch (SQLException e) {
            Base.printSQLException(e);
        }

        return user;
    }

    public void createUser(String name, String surname, String email,
                           Date birthDate, String password, UUID roleID) throws SQLException {
        var userID = Base.generateUUID();
        String queryUser = String.format("INSERT INTO user VALUES ('%s', '%s', '%s', '%s', '%s', '%s');",
                userID, name, surname, email, birthDate, password);

        String queryUserRole = String.format("INSERT INTO user_role VALUES ('%s', '%s', '%s');",
                Base.generateUUID(), userID.toString(), roleID.toString());

        try (var statement = connection.createStatement()) {
            statement.executeUpdate(queryUser);
            statement.executeUpdate(queryUserRole);
            System.out.println("Records created.");
        } catch (SQLException e) {
            Base.printSQLException(e);
        }
    }

    public void updateUser(String name, String surname, String email, Date birthDate, UUID id) throws SQLException {
        String query = String.format("UPDATE user SET name = '%s', surname = '%s', email = '%s', birth_date = '%s' WHERE id = '%s'",
                name, surname, email, birthDate.toString(), id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record updated.");
        } catch (SQLException e) {
            Base.printSQLException(e);
        }
    }

    public void deleteUser(UUID id) throws SQLException {
        String queryUser = String.format("DELETE FROM user WHERE id = '%s';", id.toString());
        String queryUserRole = String.format("DELETE FROM user_role WHERE id_user = '%s';", id.toString());

        try (var statement = connection.createStatement()) {
            statement.executeUpdate(queryUser);
            statement.executeUpdate(queryUserRole);
            System.out.println("Records deleted.");
        } catch (SQLException e) {
            Base.printSQLException(e);
        }
    }

}
