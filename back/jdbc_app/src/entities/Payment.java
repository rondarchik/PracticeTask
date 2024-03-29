package entities;

import java.util.Date;
import java.util.UUID;

public class Payment {
    private UUID id;
    private UUID clientID;
    private UUID creditID;
    private Double amount;
    private Date paymentDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getClientID() {
        return clientID;
    }

    public void setClientID(UUID clientID) {
        this.clientID = clientID;
    }

    public UUID getCreditID() {
        return creditID;
    }

    public void setCreditID(UUID creditID) {
        this.creditID = creditID;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Payment() {

    }

    public Payment(UUID id, UUID clientID, UUID creditID, Double amount, Date paymentDate) {
        this.id = id;
        this.clientID = clientID;
        this.creditID = creditID;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }
}
