

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

import bean.Colleges;
import bean.Login;

public class AddCollegeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCollegeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		Login loginbean = new Login();
		Colleges collegebean = new Colleges();
		loginbean.setUsername(request.getParameter("adminName"));
		loginbean.setPassword(request.getParameter("adminPassword"));
		collegebean.setCollegeCode(request.getParameter("collegeCode"));
		collegebean.setCollegeName(request.getParameter("collegeName"));
		loginbean.setCollegeCode(collegebean);

		String apiURL = "http://localhost:8022/ExtendedStudentAdmissionSystem/webapi/myresource";
		
		Client client = ClientBuilder.newClient(new ClientConfig());
		WebTarget target = client.target(apiURL).path("addCollege");
		
		Invocation.Builder invocationbuilder = target.request(MediaType.APPLICATION_JSON);
		Response clientResponse = invocationbuilder.post(Entity.entity(collegebean,MediaType.APPLICATION_JSON));
		String validate = clientResponse.readEntity(String.class);
		
		
		WebTarget target1 = client.target(apiURL).path("addadmin");
		
		Invocation.Builder invocationbuilder1 = target1.request(MediaType.APPLICATION_JSON);	
		Response clientResponse1 = invocationbuilder1.post(Entity.entity(loginbean, MediaType.APPLICATION_JSON));
		String validate1 = clientResponse1.readEntity(String.class);

		
		if(validate1.equals("yes") && validate.equals("yes"))
			out.println("College Added Sucessfully");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
