package screens;

import entities.Credit;
import repositories.CreditRepository;
import utils.BaseUtil;

import java.sql.Connection;
import java.text.ParseException;

public class CreditScreen {
    private CreditScreen() {}

    public static void interactionWithCredit(Connection connection) throws ParseException {
        CreditRepository repository = new CreditRepository(connection);

        while (true) {
            int action = BaseUtil.interaction();
            if (action == 0) {
                break;
            }

            Credit credit = new Credit();
            switch (action) {
                case 1:
                    credit.setClientId(BaseUtil.getIdInput("Enter the client ID: "));
                    credit.setCreditTypeId(BaseUtil.getIdInput("Enter the credit type ID: "));
                    credit.setPaidAmount(BaseUtil.getDoubleInput("Enter the paid amount: "));
                    credit.setStartDate(BaseUtil.getDateInput("Enter the start date (yyyy-mm-dd): "));
                    credit.setEndDate(BaseUtil.getDateInput("Enter the end date (yyyy-mm-dd): "));
                    credit.setStatus(BaseUtil.getBooleanInput("Enter the status: "));
                    repository.create(credit);
                    break;
                case 2:
                    BaseUtil.display(repository.readTable());
                    break;
                case 3:
                    credit.setId(BaseUtil.getIdInput("Enter the credit type ID you want to change: "));
                    credit.setClientId(BaseUtil.getIdInput("Enter the new client ID: "));
                    credit.setCreditTypeId(BaseUtil.getIdInput("Enter the new credit type ID: "));
                    credit.setPaidAmount(BaseUtil.getDoubleInput("Enter the new paid amount: "));
                    credit.setStartDate(BaseUtil.getDateInput("Enter the new start date (yyyy-mm-dd): "));
                    credit.setEndDate(BaseUtil.getDateInput("Enter the new end date (yyyy-mm-dd): "));
                    credit.setStatus(BaseUtil.getBooleanInput("Enter the new status: "));
                    repository.update(credit);
                    break;
                case 4:
                    repository.delete(BaseUtil.getIdInput("Enter the credit ID you want to delete: "));
                    break;
                default:
                    break;
            }
        }
    }
}

