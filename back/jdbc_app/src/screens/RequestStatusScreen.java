package screens;

import entities.RequestStatus;
import repositories.RequestStatusRepository;
import utils.BaseUtil;

import java.sql.Connection;

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
                    status.setStatus(BaseUtil.getStringInput("Enter the status name: "));
                    repository.create(status);
                    break;
                case 2:
                    BaseUtil.display(repository.readTable());
                    break;
                case 3:
                    status.setId(BaseUtil.getIdInput("Enter the status ID you want to change: "));
                    status.setStatus(BaseUtil.getStringInput("Enter the new status name: "));
                    repository.update(status);
                    break;
                case 4:
                    repository.delete(BaseUtil.getIdInput("Enter the status ID you want to delete: "));
                    break;
                default:
                    break;
            }
        }
    }
}
