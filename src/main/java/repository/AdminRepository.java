package repository;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import bean.ApplicationBean;
import bean.Colleges;
import bean.Login;
import bean.SelectedStudentsBean;
import bean.StudentLogin;

public class AdminRepository {
	
	EntityManagerFactory entityfactory = Persistence.createEntityManagerFactory("ExtendedStudentAdmissionSystem");
	EntityManager manager = entityfactory.createEntityManager();
	

	// This method helps the Main Admin to add college in Colleges Table
	// Parameters: collegebean of type Colleges
	// Parameter use : To persist
	// Return type: String
	public String addCollege(Colleges collegebean) {
		
		manager.getTransaction().begin();
		manager.persist(collegebean);
		manager.getTransaction().commit();
		manager.close();
		return "yes";
	}
	
	// This method helps the Main Admin to add admin to a respective College
	// Parameters: loginbean of type Login
	// Parameter use : To persist
	// Return Type: String
	public String addAdmin(Login loginbean) {
		
		manager.getTransaction().begin();
		manager.persist(loginbean);
		manager.getTransaction().commit();
		manager.close();
		return "yes";
	}
	
	// This method helps the Main Admin to view all the admins along with their
	// respective College name and College code that he added previously.
	// Return Type: ArrayList of Login objects
	public ArrayList<Login> viewAdmin() {
		
		manager.getTransaction().begin();
		ArrayList<Login> loginBeanArray = new ArrayList<>();

		loginBeanArray = (ArrayList<Login>) manager.createQuery("select c from Login c").getResultList();

		manager.getTransaction().commit();
		manager.close();

		return loginBeanArray;
	
	}
	
	// This method helps the main admin to view all the applicants who are registered
	// and their status
	// Return type: Arraylist of applicationbean objects

	public ArrayList<ApplicationBean> viewAllApplications() {

		manager.getTransaction().begin();
		ArrayList<ApplicationBean> applicationsBeanArray = new ArrayList<>();
		
		applicationsBeanArray = (ArrayList<ApplicationBean>) manager.createQuery("select c from ApplicationBean c").getResultList();
		manager.getTransaction().commit();
		manager.close();
		
		return applicationsBeanArray;
	}

	
}
