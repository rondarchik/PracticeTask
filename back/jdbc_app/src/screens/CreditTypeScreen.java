package screens;

import entities.CreditType;
import repositories.CreditTypeRepository;
import utils.BaseUtil;

import java.sql.Connection;

public class CreditTypeScreen {
    private CreditTypeScreen() {}

    public static void interactionWithCreditType(Connection connection) {
        CreditTypeRepository repository = new CreditTypeRepository(connection);

        while (true) {
            int action = BaseUtil.interaction();
            if (action == 0) {
                break;
            }

            CreditType type = new CreditType();
            switch (action) {
                case 1:
                    type.setName(BaseUtil.getStringInput("Enter the credit name: "));
                    type.setCreditAmount(BaseUtil.getDoubleInput("Enter the credit amount: "));
                    type.setInterestRate(BaseUtil.getDoubleInput("Enter the interest rate: "));
                    type.setTermInMonths(BaseUtil.getIntInput("Enter the term (in months): "));
                    repository.create(type);
                    break;
                case 2:
                    BaseUtil.display(repository.readTable());
                    break;
                case 3:
                    type.setId(BaseUtil.getIdInput("Enter the credit type ID you want to change: "));
                    type.setName(BaseUtil.getStringInput("Enter the new credit name: "));
                    type.setCreditAmount(BaseUtil.getDoubleInput("Enter the new credit amount: "));
                    type.setInterestRate(BaseUtil.getDoubleInput("Enter the new interest rate: "));
                    type.setTermInMonths(BaseUtil.getIntInput("Enter the new term (in months): "));
                    repository.update(type);
                    break;
                case 4:
                    repository.delete(BaseUtil.getIdInput("Enter the credit type ID you want to delete: "));
                    break;
                default:
                    break;
            }
        }
    }
}
