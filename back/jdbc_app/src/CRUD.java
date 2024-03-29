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
            printSQLException(e);
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
            printSQLException(e);
        }

        return role;
    }

    public void createRole(String roleName) throws SQLException {
        String query = String.format("INSERT INTO role VALUES ('%s', '%s');",
                generateUUID(), roleName);
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record created.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void updateRole(String oldRoleName, String newRoleName) throws SQLException {
        String query = String.format("UPDATE role SET role_name = '%s' WHERE role_name = '%s';",
                newRoleName, oldRoleName);
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record updated.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void deleteRole(UUID id) throws SQLException {
        String query = String.format("DELETE FROM role WHERE id = '%s';", id.toString());
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
            printSQLException(e);
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
            printSQLException(e);
        }

        return user;
    }

    public void createUser(String name, String surname, String email,
                           Date birthDate, String password, String roleName) throws SQLException {
        var userID = generateUUID();
        String queryUser = String.format("INSERT INTO user VALUES ('%s', '%s', '%s', '%s', '%s', '%s');",
                userID, name, surname, email, birthDate, password);

        var roleID = getRoleID(roleName);
        String queryUserRole = String.format("INSERT INTO user_role VALUES ('%s', '%s', '%s');",
                generateUUID(), userID.toString(), roleID.toString());

        try (var statement = connection.createStatement()) {
            statement.executeUpdate(queryUser);
            statement.executeUpdate(queryUserRole);
            System.out.println("Records created.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void updateUser(String name, String surname, String email, Date birthDate, UUID id) throws SQLException {
        String query = String.format("UPDATE user SET name = '%s', surname = '%s', email = '%s', birth_date = '%s' WHERE id = '%s'",
                name, surname, email, birthDate.toString(), id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record updated.");
        } catch (SQLException e) {
            printSQLException(e);
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
            printSQLException(e);
        }
    }

    // Credit Type CRUD
    public ArrayList<CreditType> readCreditTypeTable() throws SQLException {
        ArrayList<CreditType> creditTypes = new ArrayList<>();
        String query = "SELECT * FROM credit_type;";

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

    public UUID getCreditTypeID(String creditName) throws SQLException {
        String query = String.format("SELECT id FROM credit_types WHERE name = '%s';", creditName);
        UUID typeID = null;
        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                typeID = UUID.fromString(rs.getString("id"));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return typeID;
    }

    public void createCreditType(String name, Double creditAmount,
                                 Double interestRate, int termInMonths) throws SQLException {
        String query = String.format("INSERT INTO credit_type VALUES ('%s', '%s', '%s', '%s', '%s');",
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
        String query = String.format("UPDATE credit_type SET name = '%s', credit_amount = '%s', interest_rate = '%s', term_in_months = '%s' WHERE id = '%s'",
                name, creditAmount.toString(), interestRate.toString(), termInMonths, id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record updated.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void deleteCreditType(UUID id) throws SQLException {
        String query = String.format("DELETE FROM credit_type WHERE id = '%s';", id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    // Credit Request Status CRUD
    public ArrayList<RequestStatus> readRequestStatusTable() throws SQLException {
        ArrayList<RequestStatus> requestStatuses = new ArrayList<>();
        String query = "SELECT * FROM request_status;";

        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                requestStatuses.add(new RequestStatus(UUID.fromString(rs.getString("id")), rs.getString("status")));
                System.out.println(STR."\{rs.getString("id")}, \{rs.getString("status")}");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return requestStatuses;
    }

    public UUID getRequestStatusID(String status) throws SQLException {
        String query = String.format("SELECT id FROM request_status WHERE status = '%s';", status);
        UUID statusID = null;
        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                statusID = UUID.fromString(rs.getString("id"));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return statusID;
    }

    public void createRequestStatus(String status) throws SQLException {
        String query = String.format("INSERT INTO request_status VALUES ('%s', '%s');",
                generateUUID(), status);
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record created.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void updateRequestStatus(String oldStatusName, String newStatusName) throws SQLException {
        String query = String.format("UPDATE request_status SET status = '%s' WHERE status = '%s';",
                newStatusName, oldStatusName);
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record updated.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void deleteRequestStatus(UUID id) throws SQLException {
        String query = String.format("DELETE FROM request_status WHERE id = '%s';", id.toString());
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
        String query = "SELECT * FROM credit_request;";

        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                creditRequests.add(new CreditRequest(UUID.fromString(rs.getString("id")),
                        UUID.fromString(rs.getString("id_manager")),
                        UUID.fromString(rs.getString("id_credit")),
                        rs.getDate("date_of_request"),
                        UUID.fromString(rs.getString("id_status")),
                        rs.getString("rejection_message")));
                System.out.println(STR."\{rs.getString("id")}, \{rs.getString("id_manager")}, \{rs.getString("id_credit")}, \{rs.getDate("date_of_request")}, \{rs.getString("id_status")}, \{rs.getString("rejection_message")}");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return creditRequests;
    }

    public void createCreditRequest(UUID managerID, UUID creditID, Date dateOfRequest,
                                    UUID statusID, String rejectionMessage) throws SQLException {
        String query = String.format("INSERT INTO credit_request VALUES ('%s', '%s', '%s', '%s', '%s', '%s');",
                generateUUID(), managerID.toString(), creditID.toString(),
                dateOfRequest.toString(), statusID.toString(), rejectionMessage);
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record created.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void updateCreditRequest(UUID managerID, UUID creditID, Date dateOfRequest,
                                    UUID statusID, String rejectionMessage, UUID id) throws SQLException {
        String query = String.format("UPDATE credit_request SET id_manager = '%s', id_client = '%s', date_of_request = '%s', id_status = '%s', rejection_message = '%s' WHERE id = '%s'",
                managerID.toString(), creditID.toString(), dateOfRequest.toString(),
                statusID.toString(), rejectionMessage, id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record updated.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void deleteCreditRequest(UUID id) throws SQLException {
        String query = String.format("DELETE FROM credit_request WHERE id = '%s';", id.toString());
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
        String query = "SELECT * FROM credit;";

        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                credits.add(new Credit(UUID.fromString(rs.getString("id")),
                        UUID.fromString(rs.getString("id_client")),
                        UUID.fromString(rs.getString("id_credit_type")),
                        rs.getDouble("paid_amount"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getBoolean("status")));
                System.out.println(STR."\{rs.getString("id")}, \{rs.getString("id_client")}, \{rs.getString("id_credit_type")}, \{rs.getDouble("paid_amount")}, \{rs.getDate("start_date")}, \{rs.getDate("end_date")}, \{rs.getBoolean("status")}");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return credits;
    }

    public void createCredit(UUID clientID, UUID creditTypeID, Double paidAmount,
                             Date startDate, Date endDate, Boolean status) throws SQLException {
        String query = String.format("INSERT INTO credit VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                generateUUID(), clientID.toString(), creditTypeID.toString(), paidAmount.toString(),
                startDate.toString(), endDate.toString(), status.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record created.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void updateCredit(UUID clientID, UUID creditTypeID, Double paidAmount,
                             Date startDate, Date endDate, Boolean status, UUID id) throws SQLException {
        String query = String.format("UPDATE credit SET id_client = '%s', id_credit_type = '%s', paid_amount = '%s', start_date = '%s', end_date = '%s', status = '%s' WHERE id = '%s'",
                clientID.toString(), creditTypeID.toString(), paidAmount.toString(),
                startDate.toString(), endDate.toString(), status.toString(), id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record updated.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void deleteCredit(UUID id) throws SQLException {
        String query = String.format("DELETE FROM credit WHERE id = '%s';", id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    // Credit Payment CRUD
    public ArrayList<Payment> readPaymentTable() throws SQLException {
        ArrayList<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM payment;";

        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                System.out.println("0 rows selected");
            }
            while (rs.next()) {
                payments.add(new Payment(UUID.fromString(rs.getString("id")),
                        UUID.fromString(rs.getString("id_client")),
                        UUID.fromString(rs.getString("id_credit")),
                        rs.getDouble("amount"),
                        rs.getDate("payment_date")));
                System.out.println(STR."\{rs.getString("id")}, \{rs.getString("id_client")}, \{rs.getString("id_credit")}, \{rs.getDouble("amount")}, \{rs.getDate("payment_date")}");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return payments;
    }

    public void createPayment(UUID clientID, UUID creditID, Double amount, Date paymentDate) throws SQLException {
        String query = String.format("INSERT INTO payment VALUES ('%s', '%s', '%s', '%s', '%s');",
                generateUUID(), clientID.toString(), creditID.toString(), amount.toString(), paymentDate.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record created.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void updatePayment(UUID clientID, UUID creditID, Double amount,
                              Date paymentDate, UUID id) throws SQLException {
        String query = String.format("UPDATE payment SET id_client = '%s', id_credit = '%s', amount = '%s', payment_date = '%s' WHERE id = '%s'",
                clientID.toString(), creditID.toString(), amount.toString(), paymentDate.toString(), id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record updated.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void deletePayment(UUID id) throws SQLException {
        String query = String.format("DELETE FROM payment WHERE id = '%s';", id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
}
