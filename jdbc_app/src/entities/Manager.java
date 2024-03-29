package entities;

import java.util.UUID;

public class Manager extends User {
    protected UUID managerID;

    public UUID getManagerID() {
        return managerID;
    }

    public void setManagerID(UUID managerID) {
        this.managerID = managerID;
    }

    public Manager() {

    }

    public Manager(UUID managerID) {
        this.managerID = managerID;
    }

    public Manager(UUID id, String name, String surname,
                   String email) {
        super(id, name, surname, email);
        this.managerID = super.getId();
    }
}
