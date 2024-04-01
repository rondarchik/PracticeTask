package screens;

import repositories.RequestStatusRepository;
import utils.Base;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import static java.lang.System.console;

public class RequestStatusScreen {
    private RequestStatusScreen() {

    }

    public static void interactionWithRequestStatus(Connection connection) throws SQLException {
        RequestStatusRepository repository = new RequestStatusRepository(connection);

        while (true) {
            Base.printActionSelect();

            console().printf("Enter the action value: ");
            int action = Base.in.nextInt();

            if (action == 0) {
                break;
            }

            switch (action) {
                case 1:
                    console().printf("Enter the status name: ");
                    String statusName = Base.in.nextLine();
                    repository.createRequestStatus(statusName);
                    break;
                case 2:
                    repository.readRequestStatusTable();
                    break;
                case 3:
                    console().printf("Enter the old status name: ");
                    String oldStatusName = Base.in.nextLine();
                    console().printf("Enter the new status name: ");
                    String newStatusName = Base.in.nextLine();
                    repository.updateRequestStatus(oldStatusName, newStatusName);
                    break;
                case 4:
                    console().printf("Enter the status ID you want to delete: ");
                    UUID userID = UUID.fromString(Base.in.nextLine());
                    repository.deleteRequestStatus(userID);
                    break;
                default:
                    break;
            }
        }
    }
}
