package screens;

import entities.CreditRequest;
import repositories.CreditRequestRepository;
import utils.BaseUtil;

import java.sql.Connection;
import java.text.ParseException;

public class CreditRequestScreen {
    private CreditRequestScreen() {}

    public static void interactionWithCreditRequest(Connection connection) throws ParseException {
        CreditRequestRepository repository = new CreditRequestRepository(connection);

        while (true) {
            int action = BaseUtil.interaction();
            if (action == 0) {
                break;
            }

            CreditRequest request = new CreditRequest();
            switch (action) {
                case 1:
                    request.setManagerId(BaseUtil.getIdInput("Enter the manager ID: "));
                    request.setCreditId(BaseUtil.getIdInput("Enter the credit ID: "));
                    request.setDateOfRequest(BaseUtil.getDateInput("Enter the date of request (yyyy-mm-dd): "));
                    request.setStatusId(BaseUtil.getIdInput("Enter the status ID: "));
                    request.setRejectionMessage(BaseUtil.getStringInput("Enter the rejection message: "));
                    repository.create(request);
                    break;
                case 2:
                    BaseUtil.display(repository.readTable());
                    break;
                case 3:
                    request.setId(BaseUtil.getIdInput("Enter the credit request ID you want to change: "));
                    request.setManagerId(BaseUtil.getIdInput("Enter the manager ID: "));
                    request.setCreditId(BaseUtil.getIdInput("Enter the credit ID: "));
                    request.setDateOfRequest(BaseUtil.getDateInput("Enter the date of request (yyyy-mm-dd): "));
                    request.setStatusId(BaseUtil.getIdInput("Enter the status ID: "));
                    request.setRejectionMessage(BaseUtil.getStringInput("Enter the rejection message: "));
                    repository.update(request);
                    break;
                case 4:
                    repository.delete(BaseUtil.getIdInput("Enter the credit request ID you want to delete: "));
                    break;
                default:
                    break;
            }
        }
    }
}

