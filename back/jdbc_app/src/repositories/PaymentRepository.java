package repositories;

import entities.Payment;
import utils.BaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class PaymentRepository implements IBaseRepository<Payment> {
    private final Connection connection;
    private static final Logger logger = Logger.getLogger(PaymentRepository.class.getName());

    public PaymentRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Payment> readTable() {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM payment;";

        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                logger.info("0 rows selected");
            }
            while (rs.next()) {
                payments.add(new Payment(UUID.fromString(rs.getString("id")),
                        UUID.fromString(rs.getString("id_client")),
                        UUID.fromString(rs.getString("id_credit")),
                        rs.getDouble("amount"),
                        rs.getDate("payment_date")));
            }
        } catch (SQLException e) {
            BaseUtil.printSQLException(e);
        }

        return payments;
    }

    @Override
    public void create(Payment payment) {
        String query = String.format("INSERT INTO payment VALUES ('%s', '%s', '%s', '%s', '%s');",
                BaseUtil.generateUUID(), payment.getClientId().toString(), payment.getCreditId().toString(),
                payment.getAmount().toString(), payment.getPaymentDate().toString());

        BaseRepository.executeQuery(connection, query, "Records created.");
    }

    @Override
    public void update(Payment payment) {
        String query = String.format("UPDATE payment SET id_client = '%s', id_credit = '%s', amount = '%s', payment_date = '%s' WHERE id = '%s'",
                payment.getClientId().toString(), payment.getCreditId().toString(),
                payment.getAmount().toString(), payment.getPaymentDate().toString(), payment.getId().toString());

        BaseRepository.executeQuery(connection, query, "Record updated.");
    }

    @Override
    public void delete(UUID id) {
        String query = String.format("DELETE FROM payment WHERE id = '%s';", id.toString());

        BaseRepository.executeQuery(connection, query, "Records deleted.");
    }

}