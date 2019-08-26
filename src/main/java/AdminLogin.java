

import java.io.IOException;
import java.io.PrintWriter;

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

import bean.Login;

public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch;
		PrintWriter out = response.getWriter();
		
		Login login = new Login();
		login.setUsername(request.getParameter("username"));
		login.setPassword(request.getParameter("password"));
		
		Client client = ClientBuilder.newClient(new ClientConfig());
		String apiURL = "http://localhost:8022/ExtendedStudentAdmissionSystem/webapi/myresource";
		
		WebTarget target = client.target(apiURL).path("validate");
		
		Invocation.Builder invocationbuilder = target.request(MediaType.APPLICATION_JSON);
		Response clientResponse = invocationbuilder.post(Entity.entity(login,MediaType.APPLICATION_JSON));
		String validate = clientResponse.readEntity(String.class);
		
		if(validate.equals("000"))
		{
			dispatch = request.getRequestDispatcher("mainadmin.jsp");
			dispatch.forward(request, response);
		}
		else if(validate.equals("error"))
		{
			dispatch = request.getRequestDispatcher("login.jsp");
			dispatch.forward(request, response);
		}
		else
		{
			request.setAttribute("collegecode", validate);
			dispatch = request.getRequestDispatcher("collegeadmin.jsp");
			dispatch.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
