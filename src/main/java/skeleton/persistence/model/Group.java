package skeleton.persistence.model;

import java.util.List;

import javax.persistence.Transient;

public class Group extends BaseModel {
    String name;
    
    @Transient
    List<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    
}
