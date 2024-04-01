package screens;

import repositories.CreditRequestRepository;
import utils.Base;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import static java.lang.System.console;

public class CreditRequestScreen {
    private static final Scanner in = new Scanner(System.in);

    private CreditRequestScreen() {

    }

    public static void interactionWithCreditRequest(Connection connection) throws SQLException, ParseException {
        CreditRequestRepository repository = new CreditRequestRepository(connection);

        while (true) {
            Base.printActionSelect();

            console().printf("Enter the action value: ");
            int action = in.nextInt();

            if (action == 0) {
                break;
            }

            switch (action) {
                case 1:
                    console().printf("Enter the manager ID: ");
                    UUID userID = UUID.fromString(in.nextLine());
                    console().printf("Enter the credit ID: ");
                    UUID creditID = UUID.fromString(in.nextLine());
                    console().printf("Enter the date of request (yyyy-mm-dd): ");
                    Date date = new SimpleDateFormat(Base.datePattern).parse(in.nextLine());
                    console().printf("Enter the status ID: ");
                    UUID statusID = UUID.fromString(in.nextLine());
                    console().printf("Enter the rejection message: ");
                    String message = in.nextLine();
                    repository.createCreditRequest(userID, creditID, date, statusID, message);
                    break;
                case 2:
                    repository.readCreditRequestTable();
                    break;
                case 3:
                    console().printf("Enter the credit request ID you want to change: ");
                    UUID id = UUID.fromString(in.nextLine());
                    console().printf("Enter the new manager ID: ");
                    UUID newUserID = UUID.fromString(in.nextLine());
                    console().printf("Enter the new credit ID: ");
                    UUID newCreditID = UUID.fromString(in.nextLine());
                    console().printf("Enter the new date of request (yyyy-mm-dd): ");
                    Date newDate = new SimpleDateFormat(Base.datePattern).parse(in.nextLine());
                    console().printf("Enter the new status ID: ");
                    UUID newStatusID = UUID.fromString(in.nextLine());
                    console().printf("Enter the new rejection message: ");
                    String newMessage = in.nextLine();
                    repository.updateCreditRequest(newUserID, newCreditID, newDate, newStatusID, newMessage, id);
                    break;
                case 4:
                    console().printf("Enter the credit request ID you want to delete: ");
                    UUID creditRequestID = UUID.fromString(in.nextLine());
                    repository.deleteCreditRequest(creditRequestID);
                    break;
                default:
                    break;
            }
        }
    }
}

