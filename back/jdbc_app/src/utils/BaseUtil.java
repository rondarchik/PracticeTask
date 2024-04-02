package utils;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static java.lang.System.console;

public class BaseUtil {
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static final Scanner in = new Scanner(System.in);

    private BaseUtil() {}

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
        console().printf("\nSelect an action: \n");
        console().printf("\t1 - Create\n");
        console().printf("\t2 - Read\n");
        console().printf("\t3 - Update\n");
        console().printf("\t4 - Delete\n");
        console().printf("\t0 - EXIT\n");

        console().printf("Enter the action value: ");
        return BaseUtil.in.nextInt();
    }

    public static <T> void display(List<T> list) {
        for (T entity : list) {
            console().printf(entity.toString());
            console().printf("\n");
        }
    }

    public static String getStringInput(String prompt) {
        console().printf(prompt);
        return BaseUtil.in.next();
    }

    public static UUID getIdInput(String prompt) {
        console().printf(prompt);
        return UUID.fromString(BaseUtil.in.next());
    }

    public static Date getDateInput(String prompt) throws ParseException {
        console().printf(prompt);
        return new SimpleDateFormat(BaseUtil.DATE_PATTERN).parse(BaseUtil.in.next());
    }

    public static Double getDoubleInput(String prompt) {
        console().printf(prompt);
        return BaseUtil.in.nextDouble();
    }

    public static int getIntInput(String prompt) {
        console().printf(prompt);
        return BaseUtil.in.nextInt();
    }

    public static Boolean getBooleanInput(String prompt) {
        console().printf(prompt);
        return BaseUtil.in.nextBoolean();
    }
}
