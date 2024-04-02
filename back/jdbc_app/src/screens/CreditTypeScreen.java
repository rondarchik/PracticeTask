package screens;

import entities.CreditType;
import repositories.CreditTypeRepository;
import utils.BaseUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static java.lang.System.console;

public class CreditTypeScreen {
    private CreditTypeScreen() {}

    public static void interactionWithCreditType(Connection connection) throws SQLException {
        CreditTypeRepository repository = new CreditTypeRepository(connection);

        while (true) {
            int action = BaseUtil.interaction();
            if (action == 0) {
                break;
            }

            CreditType type = new CreditType();
            switch (action) {
                case 1:
                    console().printf("Enter the credit name: ");
                    type.setName(BaseUtil.in.next());
                    console().printf("Enter the credit amount: ");
                    type.setCreditAmount(BaseUtil.in.nextDouble());
                    console().printf("Enter the interest rate: ");
                    type.setInterestRate(BaseUtil.in.nextDouble());
                    console().printf("Enter the term (in months): ");
                    type.setTermInMonths(BaseUtil.in.nextInt());
                    repository.create(type);
                    break;
                case 2:
                    List<CreditType> creditTypes = repository.readTable();
                    for (CreditType i : creditTypes) {
                        console().printf(i.toString());
                    }
                    break;
                case 3:
                    console().printf("Enter the credit type ID you want to change: ");
                    type.setId(UUID.fromString(BaseUtil.in.next()));
                    console().printf("Enter the new credit name: ");
                    type.setName(BaseUtil.in.next());
                    console().printf("Enter the new  credit amount: ");
                    type.setCreditAmount(BaseUtil.in.nextDouble());
                    console().printf("Enter the new interest rate: ");
                    type.setInterestRate(BaseUtil.in.nextDouble());
                    console().printf("Enter the new term (in months): ");
                    type.setTermInMonths(BaseUtil.in.nextInt());
                    repository.update(type);
                    break;
                case 4:
                    console().printf("Enter the new credit type ID you want to delete: ");
                    UUID typeID = UUID.fromString(BaseUtil.in.next());
                    repository.delete(typeID);
                    break;
                default:
                    break;
            }
        }
    }
}
