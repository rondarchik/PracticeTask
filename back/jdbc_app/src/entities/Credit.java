package entities;

import java.util.Date;
import java.util.UUID;

public class Credit {
    private UUID id;
    private UUID clientId;
    private UUID creditTypeId;
    private Double paidAmount;
    private Date startDate;
    private Date endDate;
    private Boolean status;

    public Credit() {}

    public Credit(UUID id, UUID clientId, UUID creditTypeId, Double paidAmount,
                  Date startDate, Date endDate, Boolean status) {
        this.id = id;
        this.clientId = clientId;
        this.creditTypeId = creditTypeId;
        this.paidAmount = paidAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
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

    public UUID getCreditTypeId() {
        return creditTypeId;
    }

    public void setCreditTypeId(UUID creditTypeId) {
        this.creditTypeId = creditTypeId;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return STR."\{id.toString()}, \{clientId.toString()}, \{creditTypeId.toString()}, \{paidAmount.toString()}, \{startDate.toString()}, \{endDate.toString()}, \{status.toString()}";
    }
}
