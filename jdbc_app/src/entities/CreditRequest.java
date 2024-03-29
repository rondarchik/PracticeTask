package entities;

import java.util.Date;
import java.util.UUID;

public class CreditRequest {
    protected UUID id;
    protected UUID managerID;
    protected UUID clientID;
    protected UUID creditTypeID;
    protected Date dateOfRequest;
    protected Boolean status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getManagerID() {
        return managerID;
    }

    public void setManagerID(UUID managerID) {
        this.managerID = managerID;
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

    public Date getDateOfRequest() {
        return dateOfRequest;
    }

    public void setDateOfRequest(Date dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public CreditRequest() {}

    public CreditRequest(UUID id, UUID managerID, UUID clientID,
                         UUID creditTypeID, Date dateOfRequest, Boolean status) {
        this.id = id;
        this.managerID = managerID;
        this.clientID = clientID;
        this.creditTypeID = creditTypeID;
        this.dateOfRequest = dateOfRequest;
        this.status = status;
    }
}
