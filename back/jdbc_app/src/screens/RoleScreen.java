package screens;

import entities.Role;
import repositories.RoleRepository;
import utils.BaseUtil;

import java.sql.Connection;

public class RoleScreen {
    private RoleScreen() {}

    public static void interactionWithRole(Connection connection) {
        RoleRepository repository = new RoleRepository(connection);

        while (true) {
            int action = BaseUtil.interaction();
            if (action == 0) {
                break;
            }

            Role role = new Role();
            switch (action) {
                case 1:
                    role.setRoleName(BaseUtil.getStringInput("Enter the role name: "));
                    repository.create(role);
                    break;
                case 2:
                    BaseUtil.display(repository.readTable());
                    break;
                case 3:
                    role.setId(BaseUtil.getIdInput("Enter the role ID you want to change: "));
                    role.setRoleName(BaseUtil.getStringInput("Enter the new role name: "));
                    repository.update(role);
                    break;
                case 4:
                    repository.delete(BaseUtil.getIdInput("Enter the role ID you want to delete: "));
                    break;
                default:
                    break;
            }
        }
    }
}
