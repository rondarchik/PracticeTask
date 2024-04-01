package repositories;

import entities.Credit;
import utils.Base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class CreditRepository {
    private final Connection connection;

    public CreditRepository(Connection connection) {
        this.connection = connection;
    }

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
            Base.printSQLException(e);
        }

        return credits;
    }

    public void createCredit(UUID clientID, UUID creditTypeID, Double paidAmount,
                             Date startDate, Date endDate, Boolean status) throws SQLException {
        String query = String.format("INSERT INTO credit VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                Base.generateUUID(), clientID.toString(), creditTypeID.toString(), paidAmount.toString(),
                startDate.toString(), endDate.toString(), status.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record created.");
        } catch (SQLException e) {
            Base.printSQLException(e);
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
            Base.printSQLException(e);
        }
    }

    public void deleteCredit(UUID id) throws SQLException {
        String query = String.format("DELETE FROM credit WHERE id = '%s';", id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            Base.printSQLException(e);
        }
    }

}
