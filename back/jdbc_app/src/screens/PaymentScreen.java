package screens;

import entities.Payment;
import repositories.PaymentRepository;
import utils.BaseUtil;

import java.sql.Connection;
import java.text.ParseException;

public class PaymentScreen {
    private PaymentScreen() {}

    public static void interactionWithPayment(Connection connection) throws ParseException {
        PaymentRepository repository = new PaymentRepository(connection);

        while (true) {
            int action = BaseUtil.interaction();
            if (action == 0) {
                break;
            }

            Payment payment = new Payment();
            switch (action) {
                case 1:
                    payment.setClientId(BaseUtil.getIdInput("Enter the client ID: "));
                    payment.setCreditId(BaseUtil.getIdInput("Enter the credit ID: "));
                    payment.setAmount(BaseUtil.getDoubleInput("Enter the payment amount: "));
                    payment.setPaymentDate(BaseUtil.getDateInput("Enter the date (yyyy-mm-dd): "));
                    repository.create(payment);
                    break;
                case 2:
                    BaseUtil.display(repository.readTable());
                    break;
                case 3:
                    payment.setId(BaseUtil.getIdInput("Enter the payment ID you want to change: "));
                    payment.setClientId(BaseUtil.getIdInput("Enter the new client ID: "));
                    payment.setCreditId(BaseUtil.getIdInput("Enter the new credit ID: "));
                    payment.setAmount(BaseUtil.getDoubleInput("Enter the new payment amount: "));
                    payment.setPaymentDate(BaseUtil.getDateInput("Enter the new date (yyyy-mm-dd): "));
                    repository.update(payment);
                    break;
                case 4:
                    repository.delete(BaseUtil.getIdInput("Enter the payment ID you want to delete: "));
                    break;
                default:
                    break;
            }
        }
    }
}
