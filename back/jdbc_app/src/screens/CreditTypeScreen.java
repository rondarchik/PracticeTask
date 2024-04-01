package screens;

import repositories.CreditTypeRepository;
import utils.Base;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import static java.lang.System.console;

public class CreditTypeScreen {
    private CreditTypeScreen() {}

    public static void interactionWithCreditType(Connection connection) throws SQLException {
        CreditTypeRepository repository = new CreditTypeRepository(connection);

        while (true) {
            int action = Base.interaction();
            if (action == 0) {
                break;
            }

            switch (action) {
                case 1:
                    console().printf("Enter the credit name: ");
                    String name = Base.in.nextLine();
                    console().printf("Enter the credit amount: ");
                    Double amount = Base.in.nextDouble();
                    console().printf("Enter the interest rate: ");
                    Double rate = Base.in.nextDouble();
                    console().printf("Enter the term (in months): ");
                    int term = Base.in.nextInt();
                    repository.createCreditType(name, amount, rate, term);
                    break;
                case 2:
                    repository.readCreditTypeTable();
                    break;
                case 3:
                    console().printf("Enter the new credit type ID you want to change: ");
                    UUID id = UUID.fromString(Base.in.nextLine());
                    console().printf("Enter the new credit name: ");
                    String newName = Base.in.nextLine();
                    console().printf("Enter the new  credit amount: ");
                    Double newAmount = Base.in.nextDouble();
                    console().printf("Enter the new interest rate: ");
                    Double newRate = Base.in.nextDouble();
                    console().printf("Enter the new term (in months): ");
                    int newTerm = Base.in.nextInt();
                    repository.updateCreditType(newName, newAmount, newRate, newTerm, id);
                    break;
                case 4:
                    console().printf("Enter the new credit type ID you want to delete: ");
                    UUID typeID = UUID.fromString(Base.in.nextLine());
                    repository.deleteCreditType(typeID);
                    break;
                default:
                    break;
            }
        }
    }
}
