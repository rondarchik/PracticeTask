package entities;

import java.util.UUID;

public class RequestStatus {
    private UUID id;
    private String status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RequestStatus() {

    }

    public RequestStatus(UUID id, String status) {
        this.id = id;
        this.status = status;
    }
}
