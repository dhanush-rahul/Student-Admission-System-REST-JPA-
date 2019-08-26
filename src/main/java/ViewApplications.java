

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import bean.ApplicationBean;
import bean.Login;


public class ViewApplications extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public ViewApplications() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String apiURL = "http://localhost:8022/ExtendedStudentAdmissionSystem/webapi/myresource";
		Client client = ClientBuilder.newClient(new ClientConfig());
		WebTarget target = client.target(apiURL).path("viewallapplications");
		Invocation.Builder invocationbuilder = target.request(MediaType.APPLICATION_JSON);
		
		Response clientResponse = invocationbuilder.get();
		
		GenericType<ArrayList<ApplicationBean>> genericApplication = new GenericType<ArrayList<ApplicationBean>>() {};
		ArrayList<ApplicationBean> list = clientResponse.readEntity(genericApplication);
		request.setAttribute("Applications", list);
		RequestDispatcher dispatch = request.getRequestDispatcher("viewAllApplications.jsp");
		dispatch.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
