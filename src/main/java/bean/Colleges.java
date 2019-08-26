package bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Colleges {

	
	@Id
	private String collegeCode;
	@NotNull
	private String collegeName;
	@Override
	public String toString() {
		return "Colleges [collegeName=" + collegeName + ", collegeCode=" + collegeCode + "]";
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getCollegeCode() {
		return collegeCode;
	}
	public void setCollegeCode(String collegeCode) {
		this.collegeCode = collegeCode;
	}
	
}
