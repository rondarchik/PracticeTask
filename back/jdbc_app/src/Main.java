import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import config.DatabaseConfig;
import screens.*;

import static java.lang.System.*;

public class Main {
    private static final Scanner in = new Scanner(System.in);


    public static void main(String[] args) throws SQLException, ParseException {
        // Get database credentials from DatabaseConfig class
        var jdbcUrl = DatabaseConfig.getDbUrl();
        var user = DatabaseConfig.getDbUsername();
        var password = DatabaseConfig.getDbPassword();

        try (var connection =  DriverManager.getConnection(jdbcUrl, user, password)) {
            while (true) {
                console().printf("Select a table: \n");
                console().printf("\t1 - Role\n");
                console().printf("\t2 - User\n");
                console().printf("\t3 - Credit Type\n");
                console().printf("\t4 - Credit\n");
                console().printf("\t5 - Credit Request\n");
                console().printf("\t6 - Request Status\n");
                console().printf("\t7 - Payment\n");
                console().printf("\t0 - EXIT\n");

                console().printf("Enter the action value: ");
                int selectTableAction = in.nextInt();
                if (selectTableAction == 0) {
                    break;
                }

                switch (selectTableAction) {
                    case 1:
                        RoleScreen.interactionWithRole(connection);
                        break;
                    case 2:
                        UserScreen.interactionWithUser(connection);
                        break;
                    case 3:
                        CreditTypeScreen.interactionWithCreditType(connection);
                        break;
                    case 4:
                        CreditScreen.interactionWithCredit(connection);
                        break;
                    case 5:
                        CreditRequestScreen.interactionWithCreditRequest(connection);
                        break;
                    case 6:
                        RequestStatusScreen.interactionWithRequestStatus(connection);
                        break;
                    case 7:
                        PaymentScreen.interactionWithPayment(connection);
                        break;
                    default:
                        break;
                }
            }

        } catch (SQLException  e) {
            console().printf(e.getMessage());
        } 
    }
}

