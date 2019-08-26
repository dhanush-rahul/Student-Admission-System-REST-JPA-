

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
 * Servlet implementation class DeptServlet
 */
public class DeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeptServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Colleges collegebean = (Colleges) session.getAttribute("collegebean");
		SelectedStudentsBean selectedbean = new SelectedStudentsBean();
		selectedbean.setDepartment(request.getParameter("dept"));
		selectedbean.setCollegeName(collegebean.getCollegeName());
		
		Client client = ClientBuilder.newClient(new ClientConfig());
		String apiURL = "http://localhost:8022/ExtendedStudentAdmissionSystem/webapi/myresource";
		
		WebTarget target1 = client.target(apiURL).path("viewSelectedDept");

		Invocation.Builder invocationbuilder1 = target1.request(MediaType.APPLICATION_JSON);
		Response clientResponse1 = invocationbuilder1.post(Entity.entity(selectedbean,MediaType.APPLICATION_JSON));
		
		GenericType<ArrayList<SelectedStudentsBean>> genericApplication = new GenericType<ArrayList<SelectedStudentsBean>>() {};
		ArrayList<SelectedStudentsBean> list = clientResponse1.readEntity(genericApplication);
		request.setAttribute("selecteddept", list);
		RequestDispatcher dispatch = request.getRequestDispatcher("selecteddept.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
