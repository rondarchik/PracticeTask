package screens;

import entities.Role;
import repositories.RoleRepository;
import utils.BaseUtil;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import static java.lang.System.console;

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
                    console().printf("Enter the role name: ");
                    role.setRoleName(BaseUtil.in.next());
                    repository.create(role);
                    break;
                case 2:
                    List<Role> roles = repository.readTable();
                    for (Role i : roles) {
                        console().printf(i.toString());
                    }
                    break;
                case 3:
                    console().printf("Enter the role ID you want to change: ");
                    role.setId(UUID.fromString(BaseUtil.in.next()));
                    console().printf("Enter the new role name: ");
                    role.setRoleName(BaseUtil.in.next());
                    repository.update(role);
                    break;
                case 4:
                    console().printf("Enter the role ID you want to delete: ");
                    UUID roleID = UUID.fromString(BaseUtil.in.next());
                    repository.delete(roleID);
                    break;
                default:
                    break;
            }
        }
    }
}
