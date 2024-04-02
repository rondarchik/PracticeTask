package entities;

import java.util.Date;
import java.util.UUID;

public class Payment {
    private UUID id;
    private UUID clientId;
    private UUID creditId;
    private Double amount;
    private Date paymentDate;

    public Payment() {}

    public Payment(UUID id, UUID clientId, UUID creditId, Double amount, Date paymentDate) {
        this.id = id;
        this.clientId = clientId;
        this.creditId = creditId;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public UUID getCreditId() {
        return creditId;
    }

    public void setCreditId(UUID creditId) {
        this.creditId = creditId;
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

    @Override
    public String toString() {
        return STR."\{id.toString()}, \{clientId.toString()}, \{creditId.toString()}, \{amount.toString()}, \{paymentDate.toString()}";
    }
}
