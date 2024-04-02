package entities;

import java.util.Date;
import java.util.UUID;

public class CreditRequest {
    private UUID id;
    private UUID managerId;
    private UUID creditId;
    private Date dateOfRequest;
    private UUID statusId;
    private String rejectionMessage;

    public CreditRequest() {}

    public CreditRequest(UUID id, UUID managerId, UUID creditId, Date dateOfRequest,
                         UUID statusId, String rejectionMessage) {
        this.id = id;
        this.managerId = managerId;
        this.creditId = creditId;
        this.dateOfRequest = dateOfRequest;
        this.statusId = statusId;
        this.rejectionMessage = rejectionMessage;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getManagerId() {
        return managerId;
    }

    public void setManagerId(UUID managerId) {
        this.managerId = managerId;
    }

    public UUID getCreditId() {
        return creditId;
    }

    public void setCreditId(UUID creditId) {
        this.creditId = creditId;
    }

    public Date getDateOfRequest() {
        return dateOfRequest;
    }

    public void setDateOfRequest(Date dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }

    public UUID getStatusId() {
        return statusId;
    }

    public void setStatusId(UUID statusId) {
        this.statusId = statusId;
    }

    public String getRejectionMessage() {
        return rejectionMessage;
    }

    public void setRejectionMessage(String rejectionMessage) {
        this.rejectionMessage = rejectionMessage;
    }

    @Override
    public String toString() {
        return STR."\{id.toString()}, \{managerId.toString()}, \{creditId.toString()}, \{dateOfRequest.toString()}, \{statusId.toString()}, \{rejectionMessage}";
    }
}
