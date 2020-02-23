package thai.security.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class UserEntity {
	@Id
	String userName;
	String Password;
	String fullName;
	String roleID;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	
	
}
