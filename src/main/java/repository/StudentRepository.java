package repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import bean.ApplicationBean;
import bean.StudentLogin;

public class StudentRepository {
	
	EntityManagerFactory entityfactory = Persistence.createEntityManagerFactory("ExtendedStudentAdmissionSystem");
	EntityManager manager = entityfactory.createEntityManager();

	// This method is to validate the student when the student
	// tries to login using his/her credentials
	// Parameters : applicationbean of type ApplicationBean
	// Parameter use : StudentUsername and password of student
	public String validateStudent(ApplicationBean applicationbean) {

		StudentLogin studentbean1 = new StudentLogin();
		
		try {
		studentbean1 = (StudentLogin)manager.createQuery("select c from StudentLogin c where c.studentUsername='"+applicationbean.getStudentUsername()+"'").getSingleResult() ;
		}
		catch(Exception e)
		{
			return "error";
		}
		ApplicationBean applicationbean1 = studentbean1.getStudentUsername();
		if(applicationbean1.getStudentUsername().equals(applicationbean.getStudentUsername()))
		{
			if(studentbean1.getPassword().equals(applicationbean.getPassword()))
			{
				if(studentbean1.getStatus()!=null)
					return studentbean1.getStatus();
				else
					return "error";
			}
		}
		return "error";
		
	}

}
