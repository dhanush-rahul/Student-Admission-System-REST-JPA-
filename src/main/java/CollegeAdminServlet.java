

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import bean.ApplicationBean;
import bean.Colleges;
import bean.SelectedStudentsBean;



/**
 * Servlet implementation class CollegeAdminServlet
 */
public class CollegeAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollegeAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String collegeCode =(String) session.getAttribute("collegeCode");
		
		Colleges collegebean = new Colleges();
		collegebean.setCollegeCode(collegeCode);
		Client client = ClientBuilder.newClient(new ClientConfig());
		String apiURL = "http://localhost:8022/ExtendedStudentAdmissionSystem/webapi/myresource";
		
		WebTarget target = client.target(apiURL).path("getCollegeName");
		
		Invocation.Builder invocationbuilder = target.request(MediaType.APPLICATION_JSON);
		Response clientResponse = invocationbuilder.post(Entity.entity(collegebean,MediaType.APPLICATION_JSON));
		collegebean.setCollegeName(clientResponse.readEntity(String.class));
		
		switch (Integer.parseInt(request.getParameter("adminfunc"))) {
		case 1:
		{
			WebTarget target1 = client.target(apiURL).path("getcollegeapplications");
			
			Invocation.Builder invocationbuilder1 = target1.request(MediaType.APPLICATION_JSON);
			Response clientResponse1 = invocationbuilder1.post(Entity.entity(collegebean,MediaType.APPLICATION_JSON));
			
			GenericType<ArrayList<ApplicationBean>> genericApplication = new GenericType<ArrayList<ApplicationBean>>() {};
			ArrayList<ApplicationBean> list = clientResponse1.readEntity(genericApplication);
			request.setAttribute("Applications", list);
			RequestDispatcher dispatch = request.getRequestDispatcher("viewCollegeApplications.jsp");
			dispatch.forward(request, response);
			break;
		}
		case 2:
		{
			
			WebTarget target1 = client.target(apiURL).path("viewSelectedStudents");

			Invocation.Builder invocationbuilder1 = target1.request(MediaType.APPLICATION_JSON);
			Response clientResponse1 = invocationbuilder1.post(Entity.entity(collegebean,MediaType.APPLICATION_JSON));
			
			GenericType<ArrayList<SelectedStudentsBean>> genericApplication = new GenericType<ArrayList<SelectedStudentsBean>>() {};
			ArrayList<SelectedStudentsBean> list = clientResponse1.readEntity(genericApplication);
			request.setAttribute("selectedapplications", list);
			RequestDispatcher dispatch = request.getRequestDispatcher("viewCollegeSelects.jsp");
			dispatch.forward(request, response);
			break;
		}
		case 3:
		{
			session.setAttribute("collegebean", collegebean);
			RequestDispatcher dispatch = request.getRequestDispatcher("selectdeparment.jsp");
			dispatch.forward(request, response);
			break;
		}

		default:
			break;
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
