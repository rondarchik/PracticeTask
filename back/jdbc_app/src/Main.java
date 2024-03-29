import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import databaseconfig.java.DatabaseConfig;

public class Main {
    private static final Console obj = System.console();
    private static final Scanner in = new Scanner(System.in);

    public static Connection connect() {

        try {
            // Get database credentials from DatabaseConfig class
            var jdbcUrl = DatabaseConfig.getDbUrl();
            var user = DatabaseConfig.getDbUsername();
            var password = DatabaseConfig.getDbPassword();

            // Open a connection
            return DriverManager.getConnection(jdbcUrl, user, password);
        } catch (SQLException  e) {
            obj.printf(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) throws SQLException {
        CRUD db = new CRUD(connect());

        obj.printf("Connected to the PostgreSQL database.\n");

        // for table Role
        obj.printf("Select an action: \n");
        obj.printf("\t1 - Create\n");
        obj.printf("\t2 - Read\n");
        obj.printf("\t3 - Update\n");
        obj.printf("\t4 - Delete\n");

        obj.printf("Enter the action value: ");
        int action = in.nextInt();

        switch (action) {
            case 1:
                obj.printf("Enter the role name: ");
                String roleName = in.nextLine();
                db.createRole(roleName);
                break;
            case 2:
                db.readRoleTable();
                break;
            case 3:
                obj.printf("Enter the old role name: ");
                String oldRoleName = in.nextLine();
                obj.printf("Enter the new role name: ");
                String newRoleName = in.nextLine();
                db.updateRole(oldRoleName, newRoleName);
                break;
            case 4:
                obj.printf("Enter the role name: ");
                var roleID = db.getRoleID(in.nextLine());
                db.deleteRole(roleID);
                break;
            default:
                break;
        }
    }
}

