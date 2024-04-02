package screens;

import entities.CreditRequest;
import repositories.CreditRequestRepository;
import utils.BaseUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import static java.lang.System.console;

public class CreditRequestScreen {
    private CreditRequestScreen() {}

    public static void interactionWithCreditRequest(Connection connection) throws SQLException, ParseException {
        CreditRequestRepository repository = new CreditRequestRepository(connection);

        while (true) {
            int action = BaseUtil.interaction();
            if (action == 0) {
                break;
            }

            CreditRequest request = new CreditRequest();
            switch (action) {
                case 1:
                    console().printf("Enter the manager ID: ");
                    request.setManagerId(UUID.fromString(BaseUtil.in.next()));
                    console().printf("Enter the credit ID: ");
                    request.setCreditId(UUID.fromString(BaseUtil.in.next()));
                    console().printf("Enter the date of request (yyyy-mm-dd): ");
                    request.setDateOfRequest(new SimpleDateFormat(BaseUtil.DATE_PATTERN).parse(BaseUtil.in.next()));
                    console().printf("Enter the status ID: ");
                    request.setStatusId(UUID.fromString(BaseUtil.in.next()));
                    console().printf("Enter the rejection message: ");
                    request.setRejectionMessage(BaseUtil.in.next());
                    repository.create(request);
                    break;
                case 2:
                    List<CreditRequest> creditRequests = repository.readTable();
                    for (CreditRequest i : creditRequests) {
                        console().printf(i.toString());
                    }
                    break;
                case 3:
                    console().printf("Enter the credit request ID you want to change: ");
                    request.setId(UUID.fromString(BaseUtil.in.next()));
                    console().printf("Enter the new manager ID: ");
                    request.setManagerId(UUID.fromString(BaseUtil.in.next()));
                    console().printf("Enter the new credit ID: ");
                    request.setCreditId(UUID.fromString(BaseUtil.in.next()));
                    console().printf("Enter the new date of request (yyyy-mm-dd): ");
                    request.setDateOfRequest(new SimpleDateFormat(BaseUtil.DATE_PATTERN).parse(BaseUtil.in.next()));
                    console().printf("Enter the new status ID: ");
                    request.setStatusId(UUID.fromString(BaseUtil.in.next()));
                    console().printf("Enter the new rejection message: ");
                    request.setRejectionMessage(BaseUtil.in.next());
                    repository.update(request);
                    break;
                case 4:
                    console().printf("Enter the credit request ID you want to delete: ");
                    UUID creditRequestID = UUID.fromString(BaseUtil.in.next());
                    repository.delete(creditRequestID);
                    break;
                default:
                    break;
            }
        }
    }
}

