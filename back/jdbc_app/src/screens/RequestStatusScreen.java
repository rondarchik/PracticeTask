package screens;

import entities.RequestStatus;
import repositories.RequestStatusRepository;
import utils.BaseUtil;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import static java.lang.System.console;

public class RequestStatusScreen {
    private RequestStatusScreen() {}

    public static void interactionWithRequestStatus(Connection connection) {
        RequestStatusRepository repository = new RequestStatusRepository(connection);

        while (true) {
            int action = BaseUtil.interaction();
            if (action == 0) {
                break;
            }

            RequestStatus status = new RequestStatus();
            switch (action) {
                case 1:
                    console().printf("Enter the status name: ");
                    status.setStatus(BaseUtil.in.next());
                    repository.create(status);
                    break;
                case 2:
                    List<RequestStatus> statuses = repository.readTable();
                    for (RequestStatus i : statuses) {
                        console().printf(i.toString());
                    }
                    break;
                case 3:
                    console().printf("Enter the status ID you want to change: ");
                    status.setId(UUID.fromString(BaseUtil.in.next()));
                    console().printf("Enter the new status name: ");
                    status.setStatus(BaseUtil.in.next());
                    repository.update(status);
                    break;
                case 4:
                    console().printf("Enter the status ID you want to delete: ");
                    UUID statusID = UUID.fromString(BaseUtil.in.next());
                    repository.delete(statusID);
                    break;
                default:
                    break;
            }
        }
    }
}
