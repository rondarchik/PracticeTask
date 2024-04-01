package screens;

import repositories.PaymentRepository;
import utils.Base;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static java.lang.System.console;

public class PaymentScreen {
    private PaymentScreen() {}

    public static void interactionWithPayment(Connection connection) throws SQLException, ParseException {
        PaymentRepository repository = new PaymentRepository(connection);

        while (true) {
            int action = Base.interaction();
            if (action == 0) {
                break;
            }

            switch (action) {
                case 1:
                    console().printf("Enter the client ID: ");
                    UUID userID = UUID.fromString(Base.in.nextLine());
                    console().printf("Enter the credit ID: ");
                    UUID creditID = UUID.fromString(Base.in.nextLine());
                    console().printf("Enter the payment amount: ");
                    Double amount = Base.in.nextDouble();
                    console().printf("Enter the date (yyyy-mm-dd): ");
                    Date date = new SimpleDateFormat(Base.datePattern).parse(Base.in.nextLine());
                    repository.createPayment(userID, creditID, amount, date);
                    break;
                case 2:
                    repository.readPaymentTable();
                    break;
                case 3:
                    console().printf("Enter the payment ID you want to change: ");
                    UUID id = UUID.fromString(Base.in.nextLine());
                    console().printf("Enter the new client ID: ");
                    UUID newUserID = UUID.fromString(Base.in.nextLine());
                    console().printf("Enter the new credit ID: ");
                    UUID newCreditID = UUID.fromString(Base.in.nextLine());
                    console().printf("Enter the new payment amount: ");
                    Double newAmount = Base.in.nextDouble();
                    console().printf("Enter the new date (yyyy-mm-dd): ");
                    Date newDate = new SimpleDateFormat(Base.datePattern).parse(Base.in.nextLine());
                    repository.updatePayment(newUserID, newCreditID, newAmount, newDate, id);
                    break;
                case 4:
                    console().printf("Enter the status ID you want to delete: ");
                    UUID paymentID = UUID.fromString(Base.in.nextLine());
                    repository.deletePayment(paymentID);
                    break;
                default:
                    break;
            }
        }
    }
}
