

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import bean.ApplicationBean;
import bean.Colleges;
import bean.StudentLogin;

public class StudentLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public StudentLoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationBean applicationbean = new ApplicationBean();
		applicationbean.setStudentUsername(request.getParameter("username"));
		
		String apiURL = "http://localhost:8022/ExtendedStudentAdmissionSystem/webapi/myresource";
		Client client = ClientBuilder.newClient(new ClientConfig());
		
		WebTarget target = client.target(apiURL).path("getApplication");
		Invocation.Builder invocationbuilder = target.request(MediaType.APPLICATION_JSON);
		Response clientResponse = invocationbuilder.post(Entity.entity(applicationbean,MediaType.APPLICATION_JSON));
		ApplicationBean applicationbean1 = clientResponse.readEntity(ApplicationBean.class);
			

		WebTarget target1 = client.target(apiURL).path("validateStudent");
		Invocation.Builder invocationbuilder1 = target1.request(MediaType.APPLICATION_JSON);
		Response clientResponse1 = invocationbuilder1.post(Entity.entity(applicationbean1,MediaType.APPLICATION_JSON));
		String status = clientResponse1.readEntity(String.class);
		request.setAttribute("applicationbean", applicationbean1);
		
		if(status.equals("Pending"))
		{
			RequestDispatcher dispatch = request.getRequestDispatcher("viewStudentApplication.jsp");
			dispatch.forward(request, response);
		}
		else if(status.equals("FirstLevel"))
		{
			
			WebTarget target2 = client.target(apiURL).path("getcolleges");
			Invocation.Builder invocationbuilder2 = target2.request(MediaType.APPLICATION_JSON);
			Response clientResponse2 = invocationbuilder2.get();
			GenericType<ArrayList<Colleges>> genericApplication = new GenericType<ArrayList<Colleges>>() {};
			ArrayList<Colleges> collegebean = clientResponse2.readEntity(genericApplication);
			
			request.setAttribute("collegebean", collegebean);
			RequestDispatcher dispatch = request.getRequestDispatcher("registerAgain.jsp");
			dispatch.forward(request, response);
		}
		else if(status.equals("Rejected"))
		{
			RequestDispatcher dispatch = request.getRequestDispatcher("rejected.jsp");
			dispatch.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
