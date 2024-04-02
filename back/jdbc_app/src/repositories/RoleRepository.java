package repositories;

import entities.Role;
import utils.BaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class RoleRepository implements IBaseRepository<Role> {
    private final Connection connection;
    private static final Logger logger = Logger.getLogger(RoleRepository.class.getName());

    public RoleRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Role> readTable() {
        List<Role> roles = new ArrayList<>();
        String query = "SELECT * FROM role;";

        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                logger.info("0 rows selected");
            }
            while (rs.next()) {
                roles.add(new Role(UUID.fromString(rs.getString("id")), rs.getString("role_name")));
            }
        } catch (SQLException e) {
            BaseUtil.printSQLException(e);
        }

        return roles;
    }

    @Override
    public void create(Role role) {
        String query = String.format("INSERT INTO role VALUES ('%s', '%s');",
                BaseUtil.generateUUID(), role.getRoleName());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            logger.info("Record created.");
        } catch (SQLException e) {
            BaseUtil.printSQLException(e);
        }
    }

    @Override
    public void update(Role role) {
        String query = String.format("UPDATE role SET role_name = '%s' WHERE id = '%s';",
                role.getRoleName(), role.getId());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            logger.info("Record updated.");
        } catch (SQLException e) {
            BaseUtil.printSQLException(e);
        }
    }

    @Override
    public void delete(UUID id) {
        String query = String.format("DELETE FROM role WHERE id = '%s';", id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            logger.info("Record deleted.");
        } catch (SQLException e) {
            BaseUtil.printSQLException(e);
        }
    }

}
