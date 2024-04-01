package screens;

import repositories.RoleRepository;
import utils.Base;

import java.sql.Connection;
import java.sql.SQLException;

import static java.lang.System.console;

public class RoleScreen {
    private RoleScreen() {}

    public static void interactionWithRole(Connection connection) throws SQLException {
        RoleRepository repository = new RoleRepository(connection);

        while (true) {
            int action = Base.interaction();
            if (action == 0) {
                break;
            }

            switch (action) {
                case 1:
                    console().printf("Enter the role name: ");
                    String roleName = Base.in.nextLine();
                    repository.createRole(roleName);
                    break;
                case 2:
                    repository.readRoleTable();
                    break;
                case 3:
                    console().printf("Enter the old role name: ");
                    String oldRoleName = Base.in.nextLine();
                    console().printf("Enter the new role name: ");
                    String newRoleName = Base.in.nextLine();
                    repository.updateRole(oldRoleName, newRoleName);
                    break;
                case 4:
                    console().printf("Enter the role name: ");
                    var roleID = repository.getRoleID(Base.in.nextLine());
                    repository.deleteRole(roleID);
                    break;
                default:
                    break;
            }
        }
    }
}
