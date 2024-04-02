package screens;

import entities.Payment;
import repositories.PaymentRepository;
import utils.BaseUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import static java.lang.System.console;

public class PaymentScreen {
    private PaymentScreen() {}

    public static void interactionWithPayment(Connection connection) throws SQLException, ParseException {
        PaymentRepository repository = new PaymentRepository(connection);

        while (true) {
            int action = BaseUtil.interaction();
            if (action == 0) {
                break;
            }

            Payment payment = new Payment();
            switch (action) {
                case 1:
                    console().printf("Enter the client ID: ");
                    payment.setClientId(UUID.fromString(BaseUtil.in.next()));
                    console().printf("Enter the credit ID: ");
                    payment.setCreditId(UUID.fromString(BaseUtil.in.next()));
                    console().printf("Enter the payment amount: ");
                    payment.setAmount(BaseUtil.in.nextDouble());
                    console().printf("Enter the date (yyyy-mm-dd): ");
                    payment.setPaymentDate(new SimpleDateFormat(BaseUtil.DATE_PATTERN).parse(BaseUtil.in.next()));
                    repository.create(payment);
                    break;
                case 2:
                    List<Payment> payments = repository.readTable();
                    for (Payment i : payments) {
                        console().printf(i.toString());
                    }
                    break;
                case 3:
                    console().printf("Enter the payment ID you want to change: ");
                    payment.setId(UUID.fromString(BaseUtil.in.next()));
                    console().printf("Enter the new client ID: ");
                    payment.setClientId(UUID.fromString(BaseUtil.in.next()));
                    console().printf("Enter the new credit ID: ");
                    payment.setCreditId(UUID.fromString(BaseUtil.in.next()));
                    console().printf("Enter the new payment amount: ");
                    payment.setAmount(BaseUtil.in.nextDouble());
                    console().printf("Enter the new date (yyyy-mm-dd): ");
                    payment.setPaymentDate(new SimpleDateFormat(BaseUtil.DATE_PATTERN).parse(BaseUtil.in.next()));
                    repository.update(payment);
                    break;
                case 4:
                    console().printf("Enter the status ID you want to delete: ");
                    UUID paymentID = UUID.fromString(BaseUtil.in.next());
                    repository.delete(paymentID);
                    break;
                default:
                    break;
            }
        }
    }
}
