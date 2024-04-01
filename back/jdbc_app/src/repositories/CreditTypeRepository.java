package repositories;

import entities.CreditType;
import utils.Base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class CreditTypeRepository {
    private final Connection connection;

    public CreditTypeRepository(Connection connection) {
        this.connection = connection;
    }

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
            Base.printSQLException(e);
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
            Base.printSQLException(e);
        }

        return typeID;
    }

    public void createCreditType(String name, Double creditAmount,
                                 Double interestRate, int termInMonths) throws SQLException {
        String query = String.format("INSERT INTO credit_type VALUES ('%s', '%s', '%s', '%s', '%s');",
                Base.generateUUID(), name, creditAmount, interestRate, termInMonths);
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record created.");
        } catch (SQLException e) {
            Base.printSQLException(e);
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
            Base.printSQLException(e);
        }
    }

    public void deleteCreditType(UUID id) throws SQLException {
        String query = String.format("DELETE FROM credit_type WHERE id = '%s';", id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            Base.printSQLException(e);
        }
    }

}
