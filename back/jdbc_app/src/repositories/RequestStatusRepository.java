package repositories;

import entities.RequestStatus;
import utils.Base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class RequestStatusRepository {
    private final Connection connection;

    public RequestStatusRepository(Connection connection) {
        this.connection = connection;
    }

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
            Base.printSQLException(e);
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
            Base.printSQLException(e);
        }

        return statusID;
    }

    public void createRequestStatus(String status) throws SQLException {
        String query = String.format("INSERT INTO request_status VALUES ('%s', '%s');",
                Base.generateUUID(), status);
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record created.");
        } catch (SQLException e) {
            Base.printSQLException(e);
        }
    }

    public void updateRequestStatus(String oldStatusName, String newStatusName) throws SQLException {
        String query = String.format("UPDATE request_status SET status = '%s' WHERE status = '%s';",
                newStatusName, oldStatusName);
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record updated.");
        } catch (SQLException e) {
            Base.printSQLException(e);
        }
    }

    public void deleteRequestStatus(UUID id) throws SQLException {
        String query = String.format("DELETE FROM request_status WHERE id = '%s';", id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            Base.printSQLException(e);
        }
    }

}
