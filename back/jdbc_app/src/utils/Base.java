package utils;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

import static java.lang.System.console;

public class Base {
    public final static String datePattern = "yyyy-MM-dd";

    public static final Scanner in = new Scanner(System.in);

    private Base() {}

    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println(STR."SQLState: \{((SQLException) e).getSQLState()}");
                System.err.println(STR."Error Code: \{((SQLException) e).getErrorCode()}");
                System.err.println(STR."Message: \{e.getMessage()}");
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println(STR."Cause: \{t}");
                    t = t.getCause();
                }
            }
        }
    }

    public static int interaction() {
        console().printf("Select an action: \n");
        console().printf("\t1 - Create\n");
        console().printf("\t2 - Read\n");
        console().printf("\t3 - Update\n");
        console().printf("\t4 - Delete\n");
        console().printf("\t0 - EXIT\n");

        console().printf("Enter the action value: ");
        int action = Base.in.nextInt();

        return action;
    }
}
