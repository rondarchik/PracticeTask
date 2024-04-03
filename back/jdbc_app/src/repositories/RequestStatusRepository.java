package repositories;

import entities.RequestStatus;
import utils.BaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class RequestStatusRepository implements IBaseRepository<RequestStatus> {
    private final Connection connection;
    private static final Logger logger = Logger.getLogger(RequestStatusRepository.class.getName());

    public RequestStatusRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<RequestStatus> readTable() {
        List<RequestStatus> requestStatuses = new ArrayList<>();
        String query = "SELECT * FROM request_status;";

        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                logger.info("0 rows selected");
            }
            while (rs.next()) {
                requestStatuses.add(new RequestStatus(UUID.fromString(rs.getString("id")), rs.getString("status")));
            }
        } catch (SQLException e) {
            BaseUtil.printSQLException(e);
        }

        return requestStatuses;
    }

    @Override
    public void create(RequestStatus status) {
        String query = String.format("INSERT INTO request_status VALUES ('%s', '%s');",
                BaseUtil.generateUUID(), status.getStatus());

        BaseRepository.executeQuery(connection, query, "Records created.");
    }

    @Override
    public void update(RequestStatus status) {
        String query = String.format("UPDATE request_status SET status = '%s' WHERE id = '%s';",
                status.getStatus(), status.getId());

        BaseRepository.executeQuery(connection, query, "Record updated.");
    }

    @Override
    public void delete(UUID id) {
        String query = String.format("DELETE FROM request_status WHERE id = '%s';", id.toString());

        BaseRepository.executeQuery(connection, query, "Records deleted.");
    }

}
