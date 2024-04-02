package screens;

import entities.Role;
import entities.User;
import repositories.RoleRepository;
import repositories.UserRepository;
import utils.BaseUtil;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                    console().printf("Enter the name: ");
                    user.setName(BaseUtil.in.next());
                    console().printf("Enter the surname: ");
                    user.setSurname(BaseUtil.in.next());
                    console().printf("Enter the email: ");
                    user.setEmail(BaseUtil.in.next());
                    console().printf("Enter the birth date (yyyy-mm-dd): ");
                    user.setBirthDate(new SimpleDateFormat(BaseUtil.DATE_PATTERN).parse(BaseUtil.in.next()));
                    console().printf("Enter the password: ");
                    user.setPasswordHash(BaseUtil.in.next());
                    List<UUID> roles = addRoles(connection);
                    user.setRoles(roles);
                    repository.create(user);
                    break;
                case 2:
                    List<User> users = repository.readTable();
                    for (User i : users) {
                        console().printf(i.toString());
                    }
                    break;
                case 3:
                    console().printf("Enter the user ID you want to change: ");
                    user.setId(UUID.fromString(BaseUtil.in.next()));
                    console().printf("Enter the new name: ");
                    user.setName(BaseUtil.in.next());
                    console().printf("Enter the new surname: ");
                    user.setSurname(BaseUtil.in.next());
                    console().printf("Enter the new email: ");
                    user.setEmail(BaseUtil.in.next());
                    console().printf("Enter the new birth date (yyyy-mm-dd): ");
                    user.setBirthDate(new SimpleDateFormat(BaseUtil.DATE_PATTERN).parse(BaseUtil.in.next()));
                    repository.update(user);
                    break;
                case 4:
                    console().printf("Enter the user ID you want to delete: ");
                    UUID userID = UUID.fromString(BaseUtil.in.next());
                    repository.delete(userID);
                    break;
                default:
                    break;
            }
        }
    }

    private static List<UUID> addRoles(Connection connection) {
        RoleRepository roleRepository = new RoleRepository(connection);

        List<Role> allRoles = roleRepository.readTable();
        List<UUID> roleIdList = new ArrayList<>();
        while (true) {
            console().printf("Select roles for user: \n");
            for (Role role : allRoles) {
                console().printf(role.toString());
            }
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
