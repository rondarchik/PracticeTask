package repositories;

import entities.Credit;
import utils.BaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class CreditRepository implements IBaseRepository<Credit> {
    private final Connection connection;
    private static final Logger logger = Logger.getLogger(CreditRepository.class.getName());

    public CreditRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Credit> readTable() {
        List<Credit> credits = new ArrayList<>();
        String query = "SELECT * FROM credit;";

        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                logger.info("0 rows selected");
            }
            while (rs.next()) {
                credits.add(new Credit(UUID.fromString(rs.getString("id")),
                        UUID.fromString(rs.getString("id_client")),
                        UUID.fromString(rs.getString("id_credit_type")),
                        rs.getDouble("paid_amount"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getBoolean("status")));
            }
        } catch (SQLException e) {
            BaseUtil.printSQLException(e);
        }

        return credits;
    }

    @Override
    public void create(Credit credit) {
        String query = String.format("INSERT INTO credit VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                BaseUtil.generateUUID(), credit.getClientId().toString(),
                credit.getCreditTypeId().toString(), credit.getPaidAmount().toString(),
                credit.getStartDate().toString(), credit.getEndDate().toString(), credit.getStatus().toString());

        BaseRepository.executeQuery(connection, query, "Records created.");
    }

    @Override
    public void update(Credit credit) {
        String query = String.format("UPDATE credit SET id_client = '%s', id_credit_type = '%s', paid_amount = '%s', start_date = '%s', end_date = '%s', status = '%s' WHERE id = '%s'",
                credit.getClientId().toString(), credit.getCreditTypeId().toString(), credit.getPaidAmount().toString(),
                credit.getStartDate().toString(), credit.getEndDate().toString(),
                credit.getStatus().toString(), credit.getId().toString());

        BaseRepository.executeQuery(connection, query, "Record updated.");
    }

    @Override
    public void delete(UUID id) {
        String query = String.format("DELETE FROM credit WHERE id = '%s';", id.toString());

        BaseRepository.executeQuery(connection, query, "Records deleted.");
    }

}
