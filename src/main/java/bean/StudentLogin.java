package bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;

@Entity
@Table(name="StudentLogin")
public class StudentLogin implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="studentUsername")
	private ApplicationBean studentUsername;

	@NotNull
		private String password;
	@NotNull
	@DefaultValue(value="Pending")
	private String status;
	private int counter;
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public ApplicationBean getStudentUsername() {
		return studentUsername;
	}
	public void setStudentUsername(ApplicationBean studentUsername) {
		this.studentUsername = studentUsername;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "StudentLogin [studentUsername=" + studentUsername + ", password=" + password + ", status=" + status
				+ ", counter=" + counter + "]";
	}

	
}
