

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import bean.ApplicationBean;
import bean.SelectedStudentsBean;
import bean.StudentLogin;

/**
 * Servlet implementation class StudentStatus
 */
public class StudentStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		if(request.getParameter("varname").equals("Accept"))
		{
		
			SelectedStudentsBean selectedbean = new SelectedStudentsBean();
			StudentLogin studentbean = new StudentLogin();
			studentbean.setStatus("Selected");
			
			
			ApplicationBean applicationbean = (ApplicationBean)session.getAttribute("applicationbean");
			applicationbean.setStatus("Selected");
			studentbean.setStudentUsername(applicationbean);
			
			selectedbean.setStudentUsername(applicationbean.getStudentUsername());
			selectedbean.setSchoolName(applicationbean.getSchoolName());
			selectedbean.setBranch(applicationbean.getBranch());
			selectedbean.setSchoolName(applicationbean.getSchoolName());
			selectedbean.setDepartment(applicationbean.getDepartment());
			selectedbean.setCollegeName(applicationbean.getCollegeName());
			
			Client client = ClientBuilder.newClient(new ClientConfig());
			String apiURL = "http://localhost:8022/ExtendedStudentAdmissionSystem/webapi/myresource";
			
			WebTarget target = client.target(apiURL).path("selectStudent");
			
			Invocation.Builder invocationbuilder = target.request(MediaType.APPLICATION_JSON);
			Response clientResponse = invocationbuilder.post(Entity.entity(selectedbean,MediaType.APPLICATION_JSON));
			String selected = clientResponse.readEntity(String.class);
			
			WebTarget target1 = client.target(apiURL).path("updateApplication");
			Invocation.Builder invocationbuilder1 = target1.request(MediaType.APPLICATION_JSON);
			Response clientResponse1 = invocationbuilder1.post(Entity.entity(applicationbean,MediaType.APPLICATION_JSON));
			String deleted = clientResponse1.readEntity(String.class);
			
			WebTarget target2 = client.target(apiURL).path("updateLogin");
			Invocation.Builder invocationbuilder2 = target2.request(MediaType.APPLICATION_JSON);
			Response clientResponse2 = invocationbuilder2.post(Entity.entity(studentbean,MediaType.APPLICATION_JSON));
			String update = clientResponse2.readEntity(String.class);
				
			out.println("Student selected");			
			out.println("<a href=\"collegeadmin.jsp\">Back</a>");
		}
		else if(request.getParameter("varname").equals("Reject"))
		{	
			ApplicationBean applicationbean = (ApplicationBean)session.getAttribute("applicationbean");
			Client client = ClientBuilder.newClient(new ClientConfig());
			String apiURL = "http://localhost:8022/ExtendedStudentAdmissionSystem/webapi/myresource";
			

			WebTarget target2 = client.target(apiURL).path("getCount");
			Invocation.Builder invocationbuilder2 = target2.request(MediaType.APPLICATION_JSON);
			Response clientResponse2 = invocationbuilder2.post(Entity.entity(applicationbean,MediaType.APPLICATION_JSON));
			int count = clientResponse2.readEntity(Integer.class);
			
			if(count<3)
			{
			StudentLogin studentbean = new StudentLogin();
			studentbean.setStatus("FirstLevel");
			studentbean.setCounter(count+1);
			
			
			studentbean.setStudentUsername(applicationbean);
			applicationbean.setStatus("FirstLevel");
			
			WebTarget target1 = client.target(apiURL).path("updateApplication");
			Invocation.Builder invocationbuilder1 = target1.request(MediaType.APPLICATION_JSON);
			Response clientResponse1 = invocationbuilder1.post(Entity.entity(applicationbean,MediaType.APPLICATION_JSON));
			String deleted = clientResponse1.readEntity(String.class);
			
			WebTarget target = client.target(apiURL).path("firstlevelstudent");
			Invocation.Builder invocationbuilder = target.request(MediaType.APPLICATION_JSON);
			Response clientResponse = invocationbuilder.post(Entity.entity(studentbean,MediaType.APPLICATION_JSON));
			String selected = clientResponse.readEntity(String.class);
			}
			else
			{
				StudentLogin studentbean = new StudentLogin();
				studentbean.setStatus("Rejected");
				
				studentbean.setStudentUsername(applicationbean);
				applicationbean.setStatus("Rejected");
				
				WebTarget target3 = client.target(apiURL).path("updateStudentApplication");
				Invocation.Builder invocationbuilder3 = target3.request(MediaType.APPLICATION_JSON);
				Response clientResponse3 = invocationbuilder3.post(Entity.entity(applicationbean,MediaType.APPLICATION_JSON));
				String reject = clientResponse3.readEntity(String.class);
			}
			out.println("Rejected Successfully!"); 
			out.println("<a href=\"collegeadmin.jsp\">Back</a>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
