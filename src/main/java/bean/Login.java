package bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="login")
public class Login {

	@Id
	private String username;
	@NotNull
	private String password;
	@OneToOne()
	@JoinColumn(name="collegeCode")
	private Colleges collegeCode;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Colleges getCollegeCode() {
		return collegeCode;
	}
	public void setCollegeCode(Colleges collegeCode) {
		this.collegeCode = collegeCode;
	}
	@Override
	public String toString() {
		return "Login [username=" + username + ", password=" + password + ", collegeCode=" + collegeCode + "]";
	}
	
	
	
}
