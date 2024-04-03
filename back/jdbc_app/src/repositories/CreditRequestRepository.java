package repositories;

import entities.CreditRequest;
import utils.BaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class CreditRequestRepository implements IBaseRepository<CreditRequest> {
    private final Connection connection;
    private static final Logger logger = Logger.getLogger(CreditRequestRepository.class.getName());
    
    public CreditRequestRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<CreditRequest> readTable() {
        List<CreditRequest> creditRequests = new ArrayList<>();
        String query = "SELECT * FROM credit_request;";

        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                logger.info("0 rows selected");
            }
            while (rs.next()) {
                creditRequests.add(new CreditRequest(UUID.fromString(rs.getString("id")),
                        UUID.fromString(rs.getString("id_manager")),
                        UUID.fromString(rs.getString("id_credit")),
                        rs.getDate("date_of_request"),
                        UUID.fromString(rs.getString("id_status")),
                        rs.getString("rejection_message")));            }
        } catch (SQLException e) {
            BaseUtil.printSQLException(e);
        }

        return creditRequests;
    }

    @Override
    public void create(CreditRequest request) {
        String query = String.format("INSERT INTO credit_request VALUES ('%s', '%s', '%s', '%s', '%s', '%s');",
                BaseUtil.generateUUID(), request.getManagerId().toString(), request.getCreditId().toString(),
                request.getDateOfRequest().toString(), request.getStatusId().toString(), request.getRejectionMessage());

        BaseRepository.executeQuery(connection, query, "Records created.");
    }

    @Override
    public void update(CreditRequest request) {
        String query = String.format("UPDATE credit_request SET id_manager = '%s', id_client = '%s', date_of_request = '%s', id_status = '%s', rejection_message = '%s' WHERE id = '%s'",
                request.getManagerId().toString(), request.getCreditId().toString(), request.getDateOfRequest().toString(),
                request.getStatusId().toString(), request.getRejectionMessage(), request.getId().toString());

        BaseRepository.executeQuery(connection, query, "Record updated.");
    }

    @Override
    public void delete(UUID id) {
        String query = String.format("DELETE FROM credit_request WHERE id = '%s';", id.toString());

        BaseRepository.executeQuery(connection, query, "Records deleted.");
    }

}
