

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
import bean.StudentLogin;

/**
 * Servlet implementation class RegisterAgainServlet
 */
public class RegisterAgainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterAgainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ApplicationBean applicationbean = new ApplicationBean();
		HttpSession session = request.getSession();
		applicationbean = (ApplicationBean)session.getAttribute("applicationbean");
		ApplicationBean applicationbean1 = new ApplicationBean();
		applicationbean1.setStudentUsername(applicationbean.getStudentUsername());
		applicationbean1.setDepartment(request.getParameter("dept_choice"));
		applicationbean1.setCollegeName(request.getParameter("college"));
		applicationbean1.setStatus("Pending");
		
		Client client = ClientBuilder.newClient(new ClientConfig());
		String apiURL = "http://localhost:8022/ExtendedStudentAdmissionSystem/webapi/myresource";
	
		WebTarget target1 = client.target(apiURL).path("updateApplicationCollege");
		Invocation.Builder invocationbuilder1 = target1.request(MediaType.APPLICATION_JSON);
		Response clientResponse1 = invocationbuilder1.post(Entity.entity(applicationbean1,MediaType.APPLICATION_JSON));
		String deleted = clientResponse1.readEntity(String.class);
		
		StudentLogin studentbean = new StudentLogin();
		studentbean.setStudentUsername(applicationbean);
		
		studentbean.setCounter(3);
		WebTarget target2 = client.target(apiURL).path("updateStudentLogin");
		Invocation.Builder invocationbuilder2 = target2.request(MediaType.APPLICATION_JSON);
		Response clientResponse2 = invocationbuilder2.post(Entity.entity(studentbean,MediaType.APPLICATION_JSON));
		String updated = clientResponse2.readEntity(String.class);
		
		
		out.println("Registered Sucessfully!!");
		out.println("<a href=\"login.jsp\">Login</a>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
