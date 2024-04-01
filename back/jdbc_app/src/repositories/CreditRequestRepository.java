package repositories;

import entities.CreditRequest;
import utils.Base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class CreditRequestRepository {
    private final Connection connection;

    public CreditRequestRepository(Connection connection) {
        this.connection = connection;
    }

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
            Base.printSQLException(e);
        }

        return creditRequests;
    }

    public void createCreditRequest(UUID managerID, UUID creditID, Date dateOfRequest,
                                    UUID statusID, String rejectionMessage) throws SQLException {
        String query = String.format("INSERT INTO credit_request VALUES ('%s', '%s', '%s', '%s', '%s', '%s');",
                Base.generateUUID(), managerID.toString(), creditID.toString(),
                dateOfRequest.toString(), statusID.toString(), rejectionMessage);
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record created.");
        } catch (SQLException e) {
            Base.printSQLException(e);
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
            Base.printSQLException(e);
        }
    }

    public void deleteCreditRequest(UUID id) throws SQLException {
        String query = String.format("DELETE FROM credit_request WHERE id = '%s';", id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            Base.printSQLException(e);
        }
    }

}
