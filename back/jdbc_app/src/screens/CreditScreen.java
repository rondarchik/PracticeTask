package screens;

import repositories.CreditRepository;
import utils.Base;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static java.lang.System.console;

public class CreditScreen {
    private CreditScreen() {

    }

    public static void interactionWithCredit(Connection connection) throws SQLException, ParseException {
        CreditRepository repository = new CreditRepository(connection);

        while (true) {
            Base.printActionSelect();

            console().printf("Enter the action value: ");
            int action = Base.in.nextInt();

            if (action == 0) {
                break;
            }

            switch (action) {
                case 1:
                    console().printf("Enter the client ID: ");
                    UUID userID = UUID.fromString(Base.in.nextLine());
                    console().printf("Enter the credit type ID: ");
                    UUID creditTypeID = UUID.fromString(Base.in.nextLine());
                    console().printf("Enter the paid amount: ");
                    Double amount = Base.in.nextDouble();
                    console().printf("Enter the start date (yyyy-mm-dd): ");
                    Date startDate = new SimpleDateFormat(Base.datePattern).parse(Base.in.nextLine());
                    console().printf("Enter the end date (yyyy-mm-dd): ");
                    Date endDate = new SimpleDateFormat(Base.datePattern).parse(Base.in.nextLine());
                    console().printf("Enter the status: ");
                    Boolean status = Base.in.nextBoolean();
                    repository.createCredit(userID, creditTypeID, amount, startDate, endDate, status);
                    break;
                case 2:
                    repository.readCreditTable();
                    break;
                case 3:
                    console().printf("Enter the credit type ID you want to change: ");
                    UUID id = UUID.fromString(Base.in.nextLine());
                    console().printf("Enter the new client ID: ");
                    UUID newUserID = UUID.fromString(Base.in.nextLine());
                    console().printf("Enter the new credit type ID: ");
                    UUID newCreditTypeID = UUID.fromString(Base.in.nextLine());
                    console().printf("Enter the new paid amount: ");
                    Double newAmount = Base.in.nextDouble();
                    console().printf("Enter the new start date (yyyy-mm-dd): ");
                    Date newStartDate = new SimpleDateFormat(Base.datePattern).parse(Base.in.nextLine());
                    console().printf("Enter the new end date (yyyy-mm-dd): ");
                    Date newEndDate = new SimpleDateFormat(Base.datePattern).parse(Base.in.nextLine());
                    console().printf("Enter the new status: ");
                    Boolean newStatus = Base.in.nextBoolean();
                    repository.updateCredit(newUserID, newCreditTypeID, newAmount, newStartDate, newEndDate, newStatus, id);
                    break;
                case 4:
                    console().printf("Enter the credit ID you want to delete: ");
                    UUID creditID = UUID.fromString(Base.in.nextLine());
                    repository.deleteCredit(creditID);
                    break;
                default:
                    break;
            }
        }
    }
}

