package screens;

import repositories.RoleRepository;
import utils.Base;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.console;

public class RoleScreen {
    private static final Scanner in = new Scanner(System.in);

    private RoleScreen() {

    }

    public static void interactionWithRole(Connection connection) throws SQLException {
        RoleRepository repository = new RoleRepository(connection);

        while (true) {
            Base.printActionSelect();

            console().printf("Enter the action value: ");
            int action = in.nextInt();

            if (action == 0) {
                break;
            }

            switch (action) {
                case 1:
                    console().printf("Enter the role name: ");
                    String roleName = in.nextLine();
                    repository.createRole(roleName);
                    break;
                case 2:
                    repository.readRoleTable();
                    break;
                case 3:
                    console().printf("Enter the old role name: ");
                    String oldRoleName = in.nextLine();
                    console().printf("Enter the new role name: ");
                    String newRoleName = in.nextLine();
                    repository.updateRole(oldRoleName, newRoleName);
                    break;
                case 4:
                    console().printf("Enter the role name: ");
                    var roleID = repository.getRoleID(in.nextLine());
                    repository.deleteRole(roleID);
                    break;
                default:
                    break;
            }
        }
    }
}
