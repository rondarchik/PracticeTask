package entities;

import java.util.UUID;

public class Role {
    protected UUID id;
    protected String roleName;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Role() {}

    public Role(UUID id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }
}
