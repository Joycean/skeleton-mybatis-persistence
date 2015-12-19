package skeleton.persistence.model;

public class User extends BaseModel {
	public static final String ROLE_ADMIN = "ADMIN";
	public static final String ROLE_USER = "USER";
	
    String name;
    String password;
    String email;
    String role;
    long groupId;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
    
    public static String getRoleAdmin() {
        return ROLE_ADMIN;
    }
    public static String getRoleUser() {
        return ROLE_USER;
    }
    public long getGroupId() {
        return groupId;
    }
    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
    
    
    
}
