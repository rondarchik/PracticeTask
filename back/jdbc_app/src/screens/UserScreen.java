package screens;

import entities.User;
import repositories.RoleRepository;
import repositories.UserRepository;
import utils.BaseUtil;

import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.lang.System.console;

public class UserScreen {
    private UserScreen() {}

    public static void interactionWithUser(Connection connection) throws ParseException {
        UserRepository repository = new UserRepository(connection);

        while (true) {
            int action = BaseUtil.interaction();
            if (action == 0) {
                break;
            }

            User user = new User();
            switch (action) {
                case 1:
                    user.setName(BaseUtil.getStringInput("Enter the name: "));
                    user.setSurname(BaseUtil.getStringInput("Enter the surname: "));
                    user.setEmail(BaseUtil.getStringInput("Enter the email: "));
                    user.setBirthDate(BaseUtil.getDateInput("Enter the birth date (yyyy-mm-dd): "));
                    user.setPasswordHash(BaseUtil.getStringInput("Enter the password: "));
                    List<UUID> roles = addRoles(connection);
                    user.setRoles(roles);
                    repository.create(user);
                    break;
                case 2:
                    BaseUtil.display(repository.readTable());
                    break;
                case 3:
                    user.setId(BaseUtil.getIdInput("Enter the user ID you want to change: "));
                    user.setName(BaseUtil.getStringInput("Enter the new name: "));
                    user.setSurname(BaseUtil.getStringInput("Enter the new surname: "));
                    user.setEmail(BaseUtil.getStringInput("Enter the new email: "));
                    user.setBirthDate(BaseUtil.getDateInput("Enter the new birth date (yyyy-mm-dd): "));
                    repository.update(user);
                    break;
                case 4:
                    repository.delete(BaseUtil.getIdInput("Enter the user ID you want to delete: "));
                    break;
                default:
                    break;
            }
        }
    }

    private static List<UUID> addRoles(Connection connection) {
        RoleRepository roleRepository = new RoleRepository(connection);

        List<UUID> roleIdList = new ArrayList<>();
        while (true) {
            console().printf("Select roles for user: \n");
            BaseUtil.display(roleRepository.readTable());

            console().printf("\nEnter the role id (or write 0 to exit): ");
            String value = BaseUtil.in.next();
            if (value.equals("0")) {
                break;
            }
            UUID roleID = UUID.fromString(value);
            if (!roleIdList.contains(roleID)) {
                roleIdList.add(roleID);
                console().printf("Role added successfully.\n");
            } else {
                console().printf("The user already has such role!\n");
            }
        }

        return roleIdList;
    }

}
