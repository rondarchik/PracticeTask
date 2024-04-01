package screens;

import repositories.RoleRepository;
import repositories.UserRepository;
import utils.Base;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import static java.lang.System.console;

public class UserScreen {
    private static final Scanner in = new Scanner(System.in);

    private UserScreen() {}

    public static void interactionWithUser(Connection connection) throws ParseException, SQLException {
        UserRepository repository = new UserRepository(connection);
        RoleRepository roleRepository = new RoleRepository(connection);

        while (true) {
            Base.printActionSelect();

            console().printf("Enter the action value: ");
            int action = in.nextInt();

            if (action == 0) {
                break;
            }

            switch (action) {
                case 1:
                    console().printf("Enter the name: ");
                    String name = in.nextLine();
                    console().printf("Enter the surname: ");
                    String surname = in.nextLine();
                    console().printf("Enter the email: ");
                    String email = in.nextLine();
                    console().printf("Enter the birth date (yyyy-mm-dd): ");
                    Date birthDate = new SimpleDateFormat(Base.datePattern).parse(in.nextLine());
                    console().printf("Enter the password: ");
                    String password = in.nextLine();
                    console().printf("Enter the role name: ");
                    String roleName = in.nextLine();
                    repository.createUser(name, surname, email, birthDate, password, roleRepository.getRoleID(roleName));
                    break;
                case 2:
                    repository.readUserTable();
                    break;
                case 3:
                    console().printf("Enter the user ID you want to change: ");
                    UUID id = UUID.fromString(in.nextLine());
                    console().printf("Enter the new name: ");
                    String newName = in.nextLine();
                    console().printf("Enter the new surname: ");
                    String newSurname = in.nextLine();
                    console().printf("Enter the new email: ");
                    String newEmail = in.nextLine();
                    console().printf("Enter the new birth date (yyyy-mm-dd): ");
                    Date newBirthDate = new SimpleDateFormat(Base.datePattern).parse(in.nextLine());
                    repository.updateUser(newName, newSurname, newEmail, newBirthDate, id);
                    break;
                case 4:
                    console().printf("Enter the user ID you want to delete: ");
                    UUID userID = UUID.fromString(in.nextLine());
                    repository.deleteUser(userID);
                    break;
                default:
                    break;
            }
        }
    }
}
