package entities;

import java.util.Date;
import java.util.UUID;

public class CreditRequest {
    private UUID id;
    private UUID managerID;
    private UUID creditID;
    private Date dateOfRequest;
    private UUID statusID;
    private String rejectionMessage;

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

    public UUID getCreditID() {
        return creditID;
    }

    public void setCreditID(UUID creditID) {
        this.creditID = creditID;
    }

    public Date getDateOfRequest() {
        return dateOfRequest;
    }

    public void setDateOfRequest(Date dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }

    public UUID getStatus() {
        return statusID;
    }

    public void setStatus(UUID statusID) {
        this.statusID = statusID;
    }

    public String getRejectionMessage() {
        return rejectionMessage;
    }

    public void setRejectionMessage(String rejectionMessage) {
        this.rejectionMessage = rejectionMessage;
    }

    public CreditRequest() {}

    public CreditRequest(UUID id, UUID managerID, UUID creditID, Date dateOfRequest,
                         UUID statusID, String rejectionMessage) {
        this.id = id;
        this.managerID = managerID;
        this.creditID = creditID;
        this.dateOfRequest = dateOfRequest;
        this.statusID = statusID;
        this.rejectionMessage = rejectionMessage;
    }
}
