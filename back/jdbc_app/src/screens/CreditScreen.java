package screens;

import entities.Credit;
import repositories.CreditRepository;
import utils.BaseUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import static java.lang.System.console;

public class CreditScreen {
    private CreditScreen() {}

    public static void interactionWithCredit(Connection connection) throws SQLException, ParseException {
        CreditRepository repository = new CreditRepository(connection);

        while (true) {
            int action = BaseUtil.interaction();
            if (action == 0) {
                break;
            }

            Credit credit = new Credit();
            switch (action) {
                case 1:
                    console().printf("Enter the client ID: ");
                    credit.setClientId(UUID.fromString(BaseUtil.in.next()));
                    console().printf("Enter the credit type ID: ");
                    credit.setCreditTypeId(UUID.fromString(BaseUtil.in.next()));
                    console().printf("Enter the paid amount: ");
                    credit.setPaidAmount(BaseUtil.in.nextDouble());
                    console().printf("Enter the start date (yyyy-mm-dd): ");
                    credit.setStartDate(new SimpleDateFormat(BaseUtil.DATE_PATTERN).parse(BaseUtil.in.next()));
                    console().printf("Enter the end date (yyyy-mm-dd): ");
                    credit.setEndDate(new SimpleDateFormat(BaseUtil.DATE_PATTERN).parse(BaseUtil.in.next()));
                    console().printf("Enter the status: ");
                    credit.setStatus(BaseUtil.in.nextBoolean());
                    repository.create(credit);
                    break;
                case 2:
                    List<Credit> credits =  repository.readTable();
                    for (Credit i : credits) {
                        console().printf(i.toString());
                    }
                    break;
                case 3:
                    console().printf("Enter the credit type ID you want to change: ");
                    credit.setId(UUID.fromString(BaseUtil.in.next()));
                    console().printf("Enter the new client ID: ");
                    credit.setClientId(UUID.fromString(BaseUtil.in.next()));
                    console().printf("Enter the new credit type ID: ");
                    credit.setCreditTypeId(UUID.fromString(BaseUtil.in.next()));
                    console().printf("Enter the new paid amount: ");
                    credit.setPaidAmount(BaseUtil.in.nextDouble());
                    console().printf("Enter the new start date (yyyy-mm-dd): ");
                    credit.setStartDate(new SimpleDateFormat(BaseUtil.DATE_PATTERN).parse(BaseUtil.in.next()));
                    console().printf("Enter the new end date (yyyy-mm-dd): ");
                    credit.setEndDate(new SimpleDateFormat(BaseUtil.DATE_PATTERN).parse(BaseUtil.in.next()));
                    console().printf("Enter the new status: ");
                    credit.setStatus(BaseUtil.in.nextBoolean());
                    repository.update(credit);
                    break;
                case 4:
                    console().printf("Enter the credit ID you want to delete: ");
                    repository.delete(UUID.fromString(BaseUtil.in.next()));
                    break;
                default:
                    break;
            }
        }
    }
}

