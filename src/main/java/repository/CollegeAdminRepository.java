package repository;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import bean.ApplicationBean;
import bean.Colleges;
import bean.SelectedStudentsBean;
import bean.StudentLogin;

public class CollegeAdminRepository {
		
	EntityManagerFactory entityfactory = Persistence.createEntityManagerFactory("ExtendedStudentAdmissionSystem");
	EntityManager manager = entityfactory.createEntityManager();
	
	
	// This method is for the college admin to view applications of his particular college
	// Parameters: collegebean of type Colleges
	// Parameter use : College name of the college admin
	// Return type: Arraylist of applicationbean objects
	public ArrayList<ApplicationBean> viewAllApplications(Colleges collegebean) {
		
		manager.getTransaction().begin();
		ArrayList<ApplicationBean> applicationsBeanArray = new ArrayList<>();
		
		applicationsBeanArray = (ArrayList<ApplicationBean>) manager.createQuery("select c from ApplicationBean c where c.collegeName='"+collegebean.getCollegeName()+"' and c.status='Pending'").getResultList();
		manager.getTransaction().commit();
		
		return applicationsBeanArray;
	}
	
	
	// This method is to retrieve the college name from colleges table with collegeCode in Colleges table
	// Parameters: collegebean of type Colleges
	// Parameter use : College code of college admin
	// Return type: College Name of type string
	public String getcollegeName(Colleges collegebean) {
		
		manager.getTransaction().begin();
		String collegeName = (String)manager.createQuery("select c.collegeName from Colleges c where c.collegeCode='"+collegebean.getCollegeCode()+"'").getSingleResult();
		
		return collegeName;
	}

	
	// This method is to view all the selected students of the admin's college particularly
	// with the help of college name taken from collegebean 
	// Parameters: Collegebean of type Colleges
	// Parameter use : College name of the college admin
	// Return type: Arraylist of selectedstudentsbean objects
	public ArrayList<SelectedStudentsBean> viewSelectedStudents(Colleges collegebean) {
		
		manager.getTransaction().begin();
		ArrayList<SelectedStudentsBean> selectedStudents = new ArrayList<>();
		
		selectedStudents = (ArrayList<SelectedStudentsBean>) manager.createQuery("select c from SelectedStudentsBean c where c.collegeName='"+collegebean.getCollegeName()+"'").getResultList();
		manager.getTransaction().commit();
		manager.close();
		entityfactory.close();
		
		return selectedStudents;
	}
	

	// This method is to view the selected students of a particular college by the admin
	// according to the department selected by the college admin
	// Parameters: selectedbean of type SelectedStudentBean
	// Parameter use : College name and department of the selected applicants
	// Return type: Arraylist of selectedstudentbean objects
	public ArrayList<SelectedStudentsBean> viewSelectedDept(SelectedStudentsBean selectedbean) {
		
		manager.getTransaction().begin();
		ArrayList<SelectedStudentsBean> selectedStudents = new ArrayList<>();
		
		selectedStudents = (ArrayList<SelectedStudentsBean>) manager.createQuery("select c from SelectedStudentsBean c where c.collegeName='"+selectedbean.getCollegeName()+"' and c.department='"+selectedbean.getDepartment()+"'").getResultList();
		manager.getTransaction().commit();
		manager.close();
		entityfactory.close();

		return selectedStudents;
	}
	
	// This method is to persist the student details after the selection done by college admin
	// Parameter : selectedstudent of type SelectedStudentsBean
	// Parameter use : To persist
	public String selectStudent(SelectedStudentsBean selectedstudent) {
		
		manager.getTransaction().begin();
		manager.persist(selectedstudent);
		manager.getTransaction().commit();
		manager.close();
		return "yes";
	}
	
	// This method is to update the application table and set status when rejected or selected
	// Parameters : applicationbean of type ApplicationBean
	// Parameter use : Status and Username of a particular application
	public String updateApplication(ApplicationBean applicationbean) {
		
		manager.getTransaction().begin();
		int query = manager.createQuery("update ApplicationBean set status='"+applicationbean.getStatus()+"' where studentUsername='"+applicationbean.getStudentUsername()+"'").executeUpdate();
		manager.getTransaction().commit();
		manager.close();
		if(query != 0)
			return "yes";
		return null;
	}

	// This method is to update the studentlogin table and set status when rejected or selected
	// Parameters: applicationbean of type ApplicationBean
	// Parameter use : username of a particular application
	public String updateLogin(ApplicationBean applicationbean) {
		
		manager.getTransaction().begin();
		int query = manager.createQuery("update StudentLogin set status='Selected' where studentUsername='"+applicationbean.getStudentUsername()+"'").executeUpdate();
		manager.getTransaction().commit();
		manager.close();
		if(query != 0)
			return "yes";
		return null;
	}

	// This method is to update the status of student to first level when the admin rejects the application
	// and updates the counter in the StudentLogin table
	// Parameters : applicationbean of type ApplicationBean , studentbean of type StudentLogin
	// Parameter use : Counter and status of student, Username of the applicaiton
	public String firstLevelStudent(ApplicationBean applicationbean,StudentLogin studentbean) {
		
		manager.getTransaction().begin();
		int query1 = manager.createQuery("update StudentLogin set counter="+studentbean.getCounter()+",status='"+studentbean.getStatus()+"' where studentUsername='"+applicationbean.getStudentUsername()+"'").executeUpdate();
		manager.getTransaction().commit();
		manager.close();
		if(query1 != 0)
			return "yes";
		return null;
	}

	// This method is to update the application table by setting the college name,department
	// and status when the student registers again after he gets rejected once
	// Parameters : applicationbean of type ApplicationBean
	// Parameter use : College name, department and StudentUsername of the applicant
	public String updateApplicationCollege(ApplicationBean applicationbean) {
		
		manager.getTransaction().begin();
		Integer query = manager.createQuery("update ApplicationBean set collegeName='"+applicationbean.getCollegeName()+"' , department='"+applicationbean.getDepartment()+"' , status='Pending' where studentUsername='"+applicationbean.getStudentUsername()+"'").executeUpdate();
		manager.getTransaction().commit();
		manager.close();
		if(query != 0)
			return "yes";
		return null;
	}

	// This method is to retrieve the counter in the StudentLogin table so that
	// when a applicant gets rejected more than once, the applicant will be rejected 
	// and won't be able to register again
	// Parameters : applicationbean of type ApplicationBean
	// Parameter use : StudentUsername of a particular application
	public int getCount(ApplicationBean applicationbean) {
		
		manager.getTransaction().begin();
		String name=applicationbean.getStudentUsername();
		StudentLogin studentbean = (StudentLogin)manager.createQuery("select c from StudentLogin c where c.studentUsername='"+name+"'").getSingleResult();
		manager.getTransaction().commit();
		manager.close();
		return studentbean.getCounter();
	}

	// This method is to update the Application table and StudentLogin table by setting the
	// status of the application to "Rejected" after getting the counter(>3)
	// Parameters : applicationbean ApplicationBean
	// Parameter use : Student username of a particular applicaiton
	public String updateStudentApplication(ApplicationBean applicationbean) {
		
		manager.getTransaction().begin();
		int query = manager.createQuery("update ApplicationBean set status='Rejected' where studentUsername='"+applicationbean.getStudentUsername()+"'").executeUpdate();
		int query1 = manager.createQuery("update StudentLogin set status='Rejected' where studentUsername='"+applicationbean.getStudentUsername()+"'").executeUpdate();
		manager.getTransaction().commit();
		return "yes";
	}

	// This method is to update the counter in StudentLogin
	// Parameters : applicationbean of type ApplicationBean, studentbean of type StudentLogin
	// Parameter use : Counter in studentbean and studentusername in applicationbean
	public String updateStudentLogin(ApplicationBean applicationbean, StudentLogin studentbean) {
		
		manager.getTransaction().begin();
		Integer query1 = manager.createQuery("update StudentLogin set counter="+studentbean.getCounter()+" where studentUsername='"+applicationbean.getStudentUsername()+"'").executeUpdate();
		manager.getTransaction().commit();
		manager.close();
		return null;
	}
}
