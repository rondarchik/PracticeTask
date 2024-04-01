package repositories;

import entities.Payment;
import utils.Base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class PaymentRepository {
    private final Connection connection;

    public PaymentRepository(Connection connection) {
        this.connection = connection;
    }

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
            Base.printSQLException(e);
        }

        return payments;
    }

    public void createPayment(UUID clientID, UUID creditID, Double amount, Date paymentDate) throws SQLException {
        String query = String.format("INSERT INTO payment VALUES ('%s', '%s', '%s', '%s', '%s');",
                Base.generateUUID(), clientID.toString(), creditID.toString(), amount.toString(), paymentDate.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record created.");
        } catch (SQLException e) {
            Base.printSQLException(e);
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
            Base.printSQLException(e);
        }
    }

    public void deletePayment(UUID id) throws SQLException {
        String query = String.format("DELETE FROM payment WHERE id = '%s';", id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            Base.printSQLException(e);
        }
    }

}