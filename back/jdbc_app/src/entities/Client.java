package entities;

import java.util.Date;
import java.util.UUID;

public class Client extends User {
    protected UUID clientID;
    protected Date birthDate;

    public UUID getClientID() {
        return clientID;
    }

    public void setClientID(UUID clientID) {
        this.clientID = clientID;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Client() {}

    public Client(UUID clientID, Date birthDate) {
        this.clientID = clientID;
        this.birthDate = birthDate;
    }

    public Client(UUID id, String name, String surname,
                  String email, Date birthDate) {
        super(id, name, surname, email);
        this.clientID = super.getId();
        this.birthDate = birthDate;
    }
}
