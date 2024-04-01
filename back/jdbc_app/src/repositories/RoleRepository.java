package repositories;

import entities.Role;
import utils.Base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class RoleRepository {
    private final Connection connection;

    public RoleRepository(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Role> readRoleTable() throws SQLException {
        ArrayList<Role> roles = new ArrayList<>();
        String query = "SELECT * FROM role;";

        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                roles.add(new Role(UUID.fromString(rs.getString("id")), rs.getString("role_name")));
                System.out.println(STR."\{rs.getString("id")}, \{rs.getString("role_name")}");
            }
        } catch (SQLException e) {
            Base.printSQLException(e);
        }

        return roles;
    }

    public UUID getRoleID(String roleName) throws SQLException {
        String query = String.format("SELECT id FROM role WHERE role_name = '%s';", roleName);
        UUID role = null;
        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                role = UUID.fromString(rs.getString("id"));
            }
        } catch (SQLException e) {
            Base.printSQLException(e);
        }

        return role;
    }

    public void createRole(String roleName) throws SQLException {
        String query = String.format("INSERT INTO role VALUES ('%s', '%s');",
                Base.generateUUID(), roleName);
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record created.");
        } catch (SQLException e) {
            Base.printSQLException(e);
        }
    }

    public void updateRole(String oldRoleName, String newRoleName) throws SQLException {
        String query = String.format("UPDATE role SET role_name = '%s' WHERE role_name = '%s';",
                newRoleName, oldRoleName);
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record updated.");
        } catch (SQLException e) {
            Base.printSQLException(e);
        }
    }

    public void deleteRole(UUID id) throws SQLException {
        String query = String.format("DELETE FROM role WHERE id = '%s';", id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            Base.printSQLException(e);
        }
    }

}
