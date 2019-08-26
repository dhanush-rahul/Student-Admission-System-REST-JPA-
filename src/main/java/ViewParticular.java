

import java.io.IOException;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import bean.ApplicationBean;
import bean.StudentLogin;

/**
 * Servlet implementation class ViewParticular
 */
public class ViewParticular extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewParticular() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ApplicationBean applicationbean = new ApplicationBean();
		applicationbean.setStudentUsername(request.getParameter("varname"));
		
		Client client = ClientBuilder.newClient(new ClientConfig());
		String apiURL = "http://localhost:8022/ExtendedStudentAdmissionSystem/webapi/myresource";
		
		WebTarget target = client.target(apiURL).path("getDetailsOfParticularApplication");
		
		Invocation.Builder invocationbuilder = target.request(MediaType.APPLICATION_JSON);
		Response clientResponse = invocationbuilder.post(Entity.entity(applicationbean,MediaType.APPLICATION_JSON));
		ApplicationBean applicationbean1 = new ApplicationBean();
		applicationbean1 = clientResponse.readEntity(ApplicationBean.class);
		request.setAttribute("application", applicationbean1);
		RequestDispatcher dispatch = request.getRequestDispatcher("viewparticular.jsp");
		dispatch.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
