package entities;

import java.util.Date;
import java.util.UUID;

public class Credit {
    protected UUID id;
    protected UUID clientID;
    protected UUID creditTypeID;
    protected Date startDate;
    protected Date endDate;
    protected Boolean status;

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

    public UUID getCreditTypeID() {
        return creditTypeID;
    }

    public void setCreditTypeID(UUID creditTypeID) {
        this.creditTypeID = creditTypeID;
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

    public Credit() {}

    public Credit(UUID id, UUID clientID, UUID creditTypeID,
                  Date startDate, Date endDate, Boolean status) {
        this.id = id;
        this.clientID = clientID;
        this.creditTypeID = creditTypeID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }
}
