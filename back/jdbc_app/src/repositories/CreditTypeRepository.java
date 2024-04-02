package repositories;

import entities.CreditType;
import utils.BaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class CreditTypeRepository implements IBaseRepository<CreditType> {
    private final Connection connection;
    private static final Logger logger = Logger.getLogger(CreditTypeRepository.class.getName());

    public CreditTypeRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<CreditType> readTable() {
        List<CreditType> creditTypes = new ArrayList<>();
        String query = "SELECT * FROM credit_type;";

        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                logger.info("0 rows selected");
            }
            while (rs.next()) {
                creditTypes.add(new CreditType(UUID.fromString(rs.getString("id")),
                        rs.getString("name"),
                        rs.getDouble("credit_amount"),
                        rs.getDouble("interest_rate"),
                        rs.getInt("term_in_months")));
            }
        } catch (SQLException e) {
            BaseUtil.printSQLException(e);
        }

        return creditTypes;
    }

    @Override
    public void create(CreditType type) {
        String query = String.format("INSERT INTO credit_type VALUES ('%s', '%s', '%s', '%s', '%s');",
                BaseUtil.generateUUID(), type.getName(), type.getCreditAmount().toString(),
                type.getInterestRate().toString(), type.getTermInMonths());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            logger.info("Record created.");
        } catch (SQLException e) {
            BaseUtil.printSQLException(e);
        }
    }

    @Override
    public void update(CreditType type) {
        String query = String.format("UPDATE credit_type SET name = '%s', credit_amount = '%s', interest_rate = '%s', term_in_months = '%s' WHERE id = '%s'",
                type.getName(), type.getCreditAmount().toString(), type.getInterestRate().toString(),
                type.getTermInMonths(), type.getId().toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            logger.info("Record updated.");
        } catch (SQLException e) {
            BaseUtil.printSQLException(e);
        }
    }

    @Override
    public void delete(UUID id) {
        String query = String.format("DELETE FROM credit_type WHERE id = '%s';", id.toString());
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
            logger.info("Record deleted.");
        } catch (SQLException e) {
            BaseUtil.printSQLException(e);
        }
    }

}
