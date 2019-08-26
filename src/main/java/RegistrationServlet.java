

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import bean.ApplicationBean;
import bean.StudentLogin;

/**
 * Servlet implementation class RegistrationServlet
 */
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		StudentLogin studentloginbean = new StudentLogin();
		studentloginbean.setPassword(request.getParameter("password"));
		studentloginbean.setStatus("Pending");
		studentloginbean.setCounter(1);
		
		ApplicationBean applicationbean = new ApplicationBean();
		studentloginbean.setStudentUsername(applicationbean);
		applicationbean.setStudentUsername(request.getParameter("name"));
		applicationbean.setPassword(request.getParameter("password"));
		applicationbean.setBranch(request.getParameter("branch"));
		applicationbean.setDepartment(request.getParameter("dept_choice"));
		applicationbean.setTotalMarks(Float.parseFloat(request.getParameter("totalmarks")));
		applicationbean.setPercentage(Float.parseFloat(request.getParameter("percentage")));
		applicationbean.setGpa(Float.parseFloat(request.getParameter("gpa")));
		applicationbean.setSchoolName(request.getParameter("school"));
		applicationbean.setCollegeName(request.getParameter("college"));
		applicationbean.setStatus("Pending");
		
			
		String apiURL = "http://localhost:8022/ExtendedStudentAdmissionSystem/webapi/myresource";
		Client client = ClientBuilder.newClient(new ClientConfig());
		
		WebTarget target1 = client.target(apiURL).path("studentregister");
		Invocation.Builder invocationbuilder1 = target1.request(MediaType.APPLICATION_JSON);
		Response clientResponse1 = invocationbuilder1.post(Entity.entity(studentloginbean,MediaType.APPLICATION_JSON));
		String validate1 = clientResponse1.readEntity(String.class);		
		
		WebTarget target = client.target(apiURL).path("register");
		Invocation.Builder invocationbuilder = target.request(MediaType.APPLICATION_JSON);
		Response clientResponse = invocationbuilder.post(Entity.entity(applicationbean,MediaType.APPLICATION_JSON));
		String validate = clientResponse.readEntity(String.class);
		
		
		if(validate.equals("yes") && validate.equals("yes"))
		{
			out.println("Registration successfull! Please Login using your student credentials to check your status.");
			out.println("<a href=\"login.jsp\">Login</a>");
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
