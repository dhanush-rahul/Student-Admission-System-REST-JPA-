package repository;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import bean.ApplicationBean;
import bean.Colleges;
import bean.StudentLogin;


public class ApplicationRepository {
	
	EntityManagerFactory entityfactory = Persistence.createEntityManagerFactory("ExtendedStudentAdmissionSystem");
	EntityManager manager = entityfactory.createEntityManager();
	
	
	// This method is for retrieving the college names and show it in Admission Form 
	// so that applicant can choose one of the colleges listed using this method
	// Return type: Arraylist of Colleges object
	public ArrayList<Colleges> getColleges() {
		
		manager.getTransaction().begin();
		ArrayList<Colleges> collegesBeanArray = new ArrayList<>();

		collegesBeanArray = (ArrayList<Colleges>) manager.createQuery("select c from Colleges c").getResultList();

		manager.getTransaction().commit();

		return collegesBeanArray;

	}
	
	// This method is for persisting the data given by the applicant
	// in the admission form to the applications table
	// Parameters: applicationbean of type ApplicationBean
	// Parameter use : To persist
	public String register(ApplicationBean applicationbean) {
		
		manager.getTransaction().begin();
		manager.persist(applicationbean);
		manager.getTransaction().commit();
		return "yes";
	}
	
	// This method is for persisting the Username, password and status of the application
	// to the StudentLogin table which will be used by the applicant to view the status 
	// of the application
	// Parameters: studentloginbean
	// Parameter use : To persist
	public String studentregister(StudentLogin studentloginbean) {
		
		manager.getTransaction().begin();
		manager.persist(studentloginbean);
		manager.getTransaction().commit();
		return "yes";
	}
	
	// This method retrieves the student details from the Applications table and displays them.
	// Parameters: applicationbean of type ApplicationBean
	// Parameter use : StudentUsername of applicationbean
	// Return Type: applicationbean1 of type ApplicationBean
	public ApplicationBean getStudentDetails(ApplicationBean applicationbean) {
		
		manager.getTransaction().begin();
		ApplicationBean applicationbean1 = new ApplicationBean();
		try {
		applicationbean1 = (ApplicationBean) manager.createQuery("select c from ApplicationBean c where c.studentUsername='"+applicationbean.getStudentUsername()+"'").getSingleResult();
		}
		catch(Exception e)
		{
			return null;
		}
		return applicationbean1;
	}
	
	// This method retrieves student login details from the StudentLogin table
	// Parameters: applicationbean of type ApplicationBean
	// Parameter use : StudentUsername of applicationbean
	// Return type: studentbean of type StudentLogin
	public StudentLogin getStudentLogin(ApplicationBean applicationbean) {
		
		manager.getTransaction().begin();
		StudentLogin studentbean = new StudentLogin();
		studentbean = (StudentLogin) manager.createQuery("select c from StudentLogin c where c.studentUsername='"+applicationbean.getStudentUsername()+"'").getSingleResult();
		return studentbean;
		
	}
}
