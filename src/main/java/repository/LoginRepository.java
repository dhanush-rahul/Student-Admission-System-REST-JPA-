package repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import bean.Colleges;
import bean.Login;


public class LoginRepository {
	
	EntityManagerFactory entityfactory = Persistence.createEntityManagerFactory("ExtendedStudentAdmissionSystem");
	EntityManager manager = entityfactory.createEntityManager();
	
	// This method is to validate the admin login and get the role of the admin
	// so that admin will be either a college admin or main admin
	// Parameters : login of type Login
	// Parameter use : Username and Password of the admin
	// Return type : CollegeCode(String)
	public String getRole(Login login)
	{
		Login loginbean = new Login();
		try {
			loginbean = (Login) manager.createQuery("select c from Login c where c.username='"+login.getUsername()+"'").getSingleResult();
		}
		catch(Exception e)
		{
			return "error";
		}
	
		Colleges collegebean = loginbean.getCollegeCode();

		if(loginbean.getUsername().equals(login.getUsername()))
		{
			if(loginbean.getPassword().equals(login.getPassword()))
			{
				if(loginbean.getCollegeCode()!=null)
					return collegebean.getCollegeCode();
				else
					return "error";
			}
			else
				return "error";
		}
		return "error";
		
	}

	

}
