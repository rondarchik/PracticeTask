import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import entities.*;

public class CRUD {
    private final Connection connection;

    public CRUD(Connection connection) {
        this.connection = connection;
    }

    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println(STR."SQLState: \{((SQLException) e).getSQLState()}");
                System.err.println(STR."Error Code: \{((SQLException) e).getErrorCode()}");
                System.err.println(STR."Message: \{e.getMessage()}");
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println(STR."Cause: \{t}");
                    t = t.getCause();
                }
            }
        }
    }

    // Role CRUD
    public ArrayList<Role> readRoleTable() throws SQLException {
        ArrayList<Role> roles = new ArrayList<>();
        String query = "SELECT * FROM Roles";

        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                roles.add(new Role(UUID.fromString(rs.getString("id")),
                        rs.getString("role_name")));
                System.out.println(STR."\{rs.getString("id")}, \{rs.getString("role_name")}");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return roles;
    }

    public Role readRoleRecord(String roleName) throws SQLException {
        String query = String.format("SELECT * FROM Roles WHERE role_name = '%s';", roleName);
        Role role = new Role();
        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                role = new Role(UUID.fromString(rs.getString("id")),
                        rs.getString("role_name"));
                System.out.println(STR."\{rs.getString("id")}, \{rs.getString("role_name")}");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return role;
    }

    public UUID getRoleID(String roleName) throws SQLException {
        String query = String.format("SELECT id FROM Roles WHERE role_name = '%s';", roleName);
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
            printSQLException(e);
        }

        return role;
    }

    public void createRole(String roleName) throws SQLException {
        String query = String.format("INSERT INTO Roles VALUES ('%s', '%s');",
                generateUUID(), roleName);
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record created.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void updateRole(String oldRoleName, String newRoleName) throws SQLException {
        String query = String.format("UPDATE Roles SET role_name = '%s' WHERE role_name = '%s';",
                oldRoleName, newRoleName);
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record updated.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void deleteRole(String roleName) throws SQLException {
        String query = String.format("DELETE FROM Roles WHERE role_name = '%s';", roleName);
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    // User CRUD
    public ArrayList<User> readUserTable() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM Users";

        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                users.add(new User(UUID.fromString(rs.getString("id")),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email")));
                System.out.println(STR."\{rs.getString("id")}, \{rs.getString("name")}, \{rs.getString("surname")}, \{rs.getString("email")}");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return users;
    }

    public User readUserRecord(UUID id) throws SQLException {
        String query = String.format("SELECT * FROM Users WHERE id = '%s';", id.toString());
        User user = new User();
        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                user = new User(UUID.fromString(rs.getString("id")),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email"));
                System.out.println(STR."\{rs.getString("id")}, \{rs.getString("name")}, \{rs.getString("surname")}, \{rs.getString("email")}");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return user;
    }

    public UUID getUserID(String email) throws SQLException {
        String query = String.format("SELECT id FROM Users WHERE email = '%s';", email);
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
            printSQLException(e);
        }

        return user;
    }

    public void createUser(String name, String surname, String email) throws SQLException {
        String query = String.format("INSERT INTO Users VALUES ('%s', '%s', '%s', '%s');",
                generateUUID(), name, surname, email);
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record created.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void updateUser(String name, String surname, String email, UUID id) throws SQLException {
        String query = String.format("UPDATE Users SET name = '%s', surname = '%s', email = '%s' " +
                        "WHERE id = '%s'",
                name, surname, email, id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record updated.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void deleteUser(String email) throws SQLException {
        String query = String.format("DELETE FROM Users WHERE email = '%s';", email);

        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    // Client CRUD
    public ArrayList<Client> readClientTable() throws SQLException {
        ArrayList<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM Clients;";

        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                clients.add(new Client(UUID.fromString(rs.getString("client_id")),
                        rs.getDate("birth_date")));
                System.out.println(STR."\{rs.getString("client_id")}, \{rs.getString("birth_date")}");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return clients;
    }

    public Client readClientRecord(UUID clientID) throws SQLException {
        String query = String.format("SELECT * FROM Clients WHERE client_id = '%s';", clientID.toString());
        Client client = new Client();
        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                client = new Client(UUID.fromString(rs.getString("client_id")),
                        rs.getDate("birth_date"));
                System.out.println(STR."\{rs.getString("client_id")}, \{rs.getString("birth_date")}");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return client;
    }

    // if user exists
    public void createClient(String email, Date birthDate) throws SQLException {
        final String roleName = "Client";

        UUID roleID = getRoleID(roleName);
        UUID userID = getUserID(email);
        String queryClient = String.format("INSERT INTO Clients VALUES ('%s', '%s');",
                userID.toString(), birthDate.toString());
        // also add to UserRoles Table (m2m)
        String queryUserRoles = String.format("INSERT INTO User_Roles VALUES ('%s', '%s');",
                userID.toString(), roleID.toString());

        try (var statement = connection.createStatement()) {
            statement.executeUpdate(queryClient);
            statement.executeUpdate(queryUserRoles);
            System.out.println("Records created.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    // if user not exists
    public void createClient(String name, String surname,
                             String email, Date birthDate) throws SQLException {
        createUser(name, surname, email);

        createClient(email, birthDate);
    }

    public void updateClient(Date birthDate, UUID id) throws SQLException {
        String query = String.format("UPDATE Clients SET birth_date = '%s' " +
                "WHERE client_id = '%s'", birthDate.toString(), id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record updated.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void deleteClient(UUID id) throws SQLException {
        String queryClient = String.format("DELETE FROM Clients WHERE client_id = '%s';", id.toString());
        // and also need delete from user_roles
        String queryUserRoles = String.format("DELETE FROM User_Roles WHERE user_id = '%s';", id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(queryClient);
            statement.executeUpdate(queryUserRoles);
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    // Manager CRUD
    public ArrayList<Manager> readManagerTable() throws SQLException {
        ArrayList<Manager> managers = new ArrayList<>();
        String query = "SELECT * FROM Managers";

        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                managers.add(new Manager(UUID.fromString(rs.getString("manager_id"))));
                System.out.println(STR."\{rs.getString("manager_id")}");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return managers;
    }

    // if user exists
    public void createManager(String email) throws SQLException {
        final String roleName = "Manager";

        UUID roleID = getRoleID(roleName);
        UUID userID = getUserID(email);
        String queryManager = String.format("INSERT INTO Managers VALUES ('%s');", userID.toString());
        // also add to UserRoles Table (m2m)
        String queryUserRoles = String.format("INSERT INTO User_Roles VALUES ('%s', '%s');", userID.toString(), roleID.toString());

        try (var statement = connection.createStatement()) {
            statement.executeUpdate(queryManager);
            statement.executeUpdate(queryUserRoles);
            System.out.println("Records created.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    // if user not exists
    public void createManager(String name, String surname, String email) throws SQLException {
        createUser(name, surname, email);

        createManager(email);
    }

    // manager has only one field id - and it is unchangeable
    /*public void updateManager(UUID id) throws SQLException {
        String query = String.format("UPDATE Managers SET ???? " +
                "WHERE client_id = '%s'", id.toString());
        try (var statement = conn.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record updated.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }*/

    public void deleteManager(UUID id) throws SQLException {
        String queryManager = String.format("DELETE FROM Managers WHERE manager_id = '%s';", id.toString());
        // and also need delete from user_roles
        String queryUserRoles = String.format("DELETE FROM User_Roles WHERE user_id = '%s';", id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(queryManager);
            statement.executeUpdate(queryUserRoles);
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    // Credit Type CRUD
    public ArrayList<CreditType> readCreditTypeTable() throws SQLException {
        ArrayList<CreditType> creditTypes = new ArrayList<>();
        String query = "SELECT * FROM Credit_Types;";

        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                creditTypes.add(new CreditType(UUID.fromString(rs.getString("id")),
                        rs.getString("name"),
                        rs.getDouble("credit_amount"),
                        rs.getDouble("interest_rate"),
                        rs.getInt("term_in_months")));
                System.out.println(STR."\{rs.getString("id")}, \{rs.getString("name")}, \{rs.getDouble("credit_amount")}, \{rs.getDouble("interest_rate")}, \{rs.getInt("term_in_months")}");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return creditTypes;
    }

    public CreditType readCreditTypeRecord(String creditName) throws SQLException {
        String query = String.format("SELECT * FROM Credit_Types WHERE name = '%s';", creditName);
        CreditType creditType = new CreditType();
        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                creditType = new CreditType(UUID.fromString(rs.getString("id")),
                        rs.getString("name"),
                        rs.getDouble("credit_amount"),
                        rs.getDouble("interest_rate"),
                        rs.getInt("term_in_months"));
                System.out.println(STR."\{rs.getString("id")}, \{rs.getString("name")}, \{rs.getDouble("credit_amount")}, \{rs.getDouble("interest_rate")}, \{rs.getInt("term_in_months")}");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return creditType;
    }

    public UUID getCreditTypeID(String creditName) throws SQLException {
        String query = String.format("SELECT id FROM Credit_Types WHERE name = '%s';", creditName);
        UUID type = null;
        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                type = UUID.fromString(rs.getString("id"));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return type;
    }

    public void createCreditType(String name, Double creditAmount,
                                 Double interestRate, int termInMonths) throws SQLException {
        String query = String.format("INSERT INTO Credit_Types VALUES ('%s', '%s', '%s', '%s', '%s');",
                generateUUID(), name, creditAmount, interestRate, termInMonths);
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record created.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void updateCreditType(String name, Double creditAmount,
                                 Double interestRate, int termInMonths, UUID id) throws SQLException {
        String query = String.format("UPDATE Credit_Types SET name = '%s', credit_amount = '%s', interest_rate = '%s', " +
                        "term_in_months = '%s' WHERE id = '%s'",
                name, creditAmount.toString(), interestRate.toString(), termInMonths, id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record updated.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void deleteCreditType(String creditName) throws SQLException {
        String query = String.format("DELETE FROM Credit_Types WHERE name = '%s';", creditName);
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    // Credit Request CRUD
    public ArrayList<CreditRequest> readCreditRequestTable() throws SQLException {
        ArrayList<CreditRequest> creditRequests = new ArrayList<>();
        String query = "SELECT * FROM Credit_Request;";

        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                creditRequests.add(new CreditRequest(UUID.fromString(rs.getString("id")),
                        UUID.fromString(rs.getString("manager_id")),
                        UUID.fromString(rs.getString("client_id")),
                        UUID.fromString(rs.getString("credit_type_id")),
                        rs.getDate("date_of_request"),
                        rs.getBoolean("status")));
                System.out.println(STR."\{rs.getString("id")}, \{rs.getString("manager_id")}, \{rs.getString("client_id")}, \{rs.getString("credit_type_id")}, \{rs.getDate("date_of_request")}, \{rs.getBoolean("status")}");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return creditRequests;
    }

    public CreditRequest readCreditRequestRecord(UUID id) throws SQLException {
        String query = String.format("SELECT * FROM Credit_Request WHERE id = '%s';", id.toString());
        CreditRequest creditRequest = new CreditRequest();
        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                creditRequest = new CreditRequest(UUID.fromString(rs.getString("id")),
                        UUID.fromString(rs.getString("manager_id")),
                        UUID.fromString(rs.getString("client_id")),
                        UUID.fromString(rs.getString("credit_type_id")),
                        rs.getDate("date_of_request"),
                        rs.getBoolean("status"));
                System.out.println(STR."\{rs.getString("id")}, \{rs.getString("manager_id")}, \{rs.getString("client_id")}, \{rs.getString("credit_type_id")}, \{rs.getDate("date_of_request")}, \{rs.getBoolean("status")}");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return creditRequest;
    }

    public UUID getCreditRequestID(UUID id) throws SQLException {
        String query = String.format("SELECT id FROM Credit_Request WHERE id = '%s';", id.toString());
        UUID request = null;
        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                request = UUID.fromString(rs.getString("id"));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return request;
    }

    public void createCreditRequest(UUID managerID, UUID clientID,
                                    UUID creditTypeID, Date dateOfRequest, Boolean status) throws SQLException {
        String query = String.format("INSERT INTO Credit_Request VALUES ('%s', '%s', '%s', '%s', '%s', '%s');",
                generateUUID(), managerID.toString(), clientID.toString(), creditTypeID.toString(), dateOfRequest.toString(), status.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record created.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void updateCreditRequest(UUID managerID, UUID clientID, UUID creditTypeID,
                                    Date dateOfRequest, Boolean status, UUID id) throws SQLException {
        String query = String.format("UPDATE Credit_Request SET manager_id = '%s', client_id = '%s', credit_type_id = '%s', " +
                        "date_of_request = '%s', status = '%s' WHERE id = '%s'",
                managerID.toString(), clientID.toString(), creditTypeID.toString(), dateOfRequest.toString(),
                status.toString(), id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record updated.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void deleteCreditRequest(UUID id) throws SQLException {
        String query = String.format("DELETE FROM Credit_Request WHERE id = '%s';", id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    // Credit CRUD
    public ArrayList<Credit> readCreditTable() throws SQLException {
        ArrayList<Credit> credits = new ArrayList<>();
        String query = "SELECT * FROM Credits;";

        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                credits.add(new Credit(UUID.fromString(rs.getString("id")),
                        UUID.fromString(rs.getString("client_id")),
                        UUID.fromString(rs.getString("credit_type_id")),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getBoolean("status")));
                System.out.println(STR."\{rs.getString("id")}, \{rs.getString("client_id")}, \{rs.getString("credit_type_id")}, \{rs.getDate("start_date")}, \{rs.getDate("end_date")}, \{rs.getBoolean("status")}");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return credits;
    }

    public Credit readCreditRecord(UUID id) throws SQLException {
        String query = String.format("SELECT * FROM Credits WHERE id = '%s';", id.toString());
        Credit credit = new Credit();
        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                credit = new Credit(UUID.fromString(rs.getString("id")),
                        UUID.fromString(rs.getString("client_id")),
                        UUID.fromString(rs.getString("credit_type_id")),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getBoolean("status"));
                System.out.println(STR."\{rs.getString("id")}, \{rs.getString("client_id")}, \{rs.getString("credit_type_id")}, \{rs.getDate("start_date")}, \{rs.getDate("end_date")}, \{rs.getBoolean("status")}");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return credit;
    }

    public UUID getCreditID(UUID id) throws SQLException {
        String query = String.format("SELECT id FROM Credits WHERE id = '%s';", id.toString());
        UUID credit = null;
        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                credit = UUID.fromString(rs.getString("id"));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return credit;
    }

    public void createCredit(UUID clientID, UUID creditTypeID,
                             Date startDate, Date endDate, Boolean status) throws SQLException {
        String query = String.format("INSERT INTO Credits VALUES ('%s', '%s', '%s', '%s', '%s', '%s');",
                generateUUID(), clientID.toString(), creditTypeID.toString(),
                startDate.toString(), endDate.toString(), status.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record created.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void updateCredit(UUID clientID, UUID creditTypeID,
                             Date startDate, Date endDate, Boolean status, UUID id) throws SQLException {
        String query = String.format("UPDATE Credits SET client_id = '%s', credit_type_id = '%s', " +
                        "start_date = '%s', end_date = '%s', status = '%s' WHERE id = '%s'",
                clientID.toString(), creditTypeID.toString(), startDate.toString(), endDate.toString(),
                status.toString(), id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record updated.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void deleteCredit(UUID id) throws SQLException {
        String query = String.format("DELETE FROM Credits WHERE id = '%s';", id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

}
