package Extended.ExtendedStudentAdmissionSystem;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bean.ApplicationBean;
import bean.Colleges;
import bean.Login;
import bean.SelectedStudentsBean;
import bean.StudentLogin;
import repository.AdminRepository;
import repository.ApplicationRepository;
import repository.CollegeAdminRepository;
import repository.LoginRepository;
import repository.StudentRepository;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    @Path("validate")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String collegeCode(Login login)
    {
    	LoginRepository loginrepository = new LoginRepository();
    	
    	return loginrepository.getRole(login);
    }
    
    
    @Path("addCollege")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String addCollege(Colleges collegebean)
    {
    	AdminRepository adminrepository = new AdminRepository();
    	
    	return adminrepository.addCollege(collegebean);
    }
    
    @Path("addadmin")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String addAdmin(Login login)
    {
    	AdminRepository adminrepository = new AdminRepository();
    	System.out.println(login);
    	return adminrepository.addAdmin(login);
    }
    
    @Path("viewadmin")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Login> viewAdmin()
    {
    	AdminRepository adminrepository = new AdminRepository();
    	
    	return adminrepository.viewAdmin();
    }
    
    
    @Path("getcolleges")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Colleges> getColleges()
    {
    	ApplicationRepository applicationrepository = new ApplicationRepository();
    	
    	return applicationrepository.getColleges();
    }
    
    @Path("register")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String register(ApplicationBean applicationbean)
    {
    	ApplicationRepository applicationrepository = new ApplicationRepository();
    	
    	return applicationrepository.register(applicationbean);
    }
    
    
    @Path("studentregister")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String studentregister(StudentLogin studentloginbean)
    {
    	ApplicationRepository applicationrepository = new ApplicationRepository();
    	
    	return applicationrepository.studentregister(studentloginbean);
    }
    
    @Path("viewallapplications")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<ApplicationBean> viewAllApplications()
    {
    	AdminRepository adminrepository = new AdminRepository();
    	
    	return adminrepository.viewAllApplications();
    }
    
    
    @Path("getCollegeName")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getCollegeName(Colleges collegebean)
    {
    	CollegeAdminRepository collegeadminrepository = new CollegeAdminRepository();
    	return collegeadminrepository.getcollegeName(collegebean);
    }
    
    @Path("getcollegeapplications")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<ApplicationBean> viewCollegeApplications(Colleges collegebean)
    {
    	CollegeAdminRepository collegeadminrepository = new CollegeAdminRepository();
    	
    	return collegeadminrepository.viewAllApplications(collegebean);
    }
    
    @Path("getDetailsOfParticularApplication")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ApplicationBean getDetails(ApplicationBean applicationbean)
    {
    	ApplicationRepository applicationrepository = new ApplicationRepository();
    	 
    	return applicationrepository.getStudentDetails(applicationbean);
    }
    
    @Path("selectStudent")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String selectStudent(SelectedStudentsBean selectedstudent)
    {
    	CollegeAdminRepository collegeadmin = new CollegeAdminRepository();
    	 
    	return collegeadmin.selectStudent(selectedstudent);
    }
    
    @Path("updateApplication")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String updateApplication(ApplicationBean applicationbean)
    {
    	CollegeAdminRepository collegeadmin = new CollegeAdminRepository();
    	 
    	return collegeadmin.updateApplication(applicationbean);
    }
    
    @Path("updateLogin")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String updateLogin(StudentLogin studentbean)
    {
    	CollegeAdminRepository collegeadmin = new CollegeAdminRepository();
    	 ApplicationBean applicationbean = studentbean.getStudentUsername();
    	return collegeadmin.updateLogin(applicationbean);
    }
    
    @Path("firstlevelstudent")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String firstLevelStudent(StudentLogin studentbean)
    {
    	CollegeAdminRepository collegeadmin = new CollegeAdminRepository();
    	 ApplicationBean applicationbean = studentbean.getStudentUsername();
    	return collegeadmin.firstLevelStudent(applicationbean,studentbean);
    }
    
    @Path("validateStudent")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String validateStudent(ApplicationBean applicationbean)
    {
    	StudentRepository studentrepository = new StudentRepository();
    	
    	return studentrepository.validateStudent(applicationbean);
    }
    
    @Path("getApplication")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ApplicationBean getApplication(ApplicationBean applicationbean)
    {
    	ApplicationRepository applicationrepository = new ApplicationRepository();
    	
    	return applicationrepository.getStudentDetails(applicationbean);
    }
    
    @Path("getStudent")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public StudentLogin getStudent(ApplicationBean applicationbean)
    {
    	ApplicationRepository applicationrepository = new ApplicationRepository();
    	
    	return applicationrepository.getStudentLogin(applicationbean);
    }

    @Path("updateApplicationCollege")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String updateApplicationCollege(ApplicationBean applicationbean)
    {
    	CollegeAdminRepository collegeadmin = new CollegeAdminRepository();
    	 
    	return collegeadmin.updateApplicationCollege(applicationbean);
    }
    @Path("updateStudentLogin")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String updateStudentLogin(StudentLogin studentbean)
    {
    	CollegeAdminRepository collegeadmin = new CollegeAdminRepository();
    	 ApplicationBean applicationbean = studentbean.getStudentUsername();
    	return collegeadmin.updateStudentLogin(applicationbean,studentbean);
    }
    
    @Path("getCount")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public int getCount(ApplicationBean applicationbean)
    {
    	CollegeAdminRepository collegeadmin = new CollegeAdminRepository();
    	 
    	return collegeadmin.getCount(applicationbean);
    }
    
    @Path("updateStudentApplication")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String updateStudentApplication(ApplicationBean applicationbean)
    {
    	CollegeAdminRepository collegeadmin = new CollegeAdminRepository();
    	 
    	return collegeadmin.updateStudentApplication(applicationbean);
    }
    
    @Path("viewSelectedStudents")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<SelectedStudentsBean> viewSelectedStudents(Colleges collegebean)
    {
    	CollegeAdminRepository collegeadminrepository = new CollegeAdminRepository();
    	
    	return collegeadminrepository.viewSelectedStudents(collegebean);
    }
    
    @Path("viewSelectedDept")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<SelectedStudentsBean> viewSelectedDept(SelectedStudentsBean selectedbean)
    {
    	CollegeAdminRepository collegeadminrepository = new CollegeAdminRepository();
    	
    	return collegeadminrepository.viewSelectedDept(selectedbean);
    }
}
