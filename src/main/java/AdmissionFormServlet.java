

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

import bean.Colleges;
import bean.Login;

public class AdmissionFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdmissionFormServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String apiURL = "http://localhost:8022/ExtendedStudentAdmissionSystem/webapi/myresource";
		Client client = ClientBuilder.newClient(new ClientConfig());
		WebTarget target = client.target(apiURL).path("getcolleges");
		Invocation.Builder invocationbuilder = target.request(MediaType.APPLICATION_JSON);
		Response clientResponse = invocationbuilder.get();
		
		GenericType<ArrayList<Colleges>> genericApplication = new GenericType<ArrayList<Colleges>>() {};
		ArrayList<Colleges> list = clientResponse.readEntity(genericApplication);
		request.setAttribute("list", list);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("admissionform.jsp");
		dispatch.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
