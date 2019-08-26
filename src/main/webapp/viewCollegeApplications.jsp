<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,bean.ApplicationBean,bean.StudentLogin"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border=1>
<th>Student Username</th>
<th>School Name</th>
<th>Branch</th>
<th>Total Marks</th>
<th>Percentage</th>
<th>GPA</th>
<th>Department</th>
<th>College Choosed</th>
<th>View </th>

<%

ArrayList<ApplicationBean> list = (ArrayList<ApplicationBean>) request.getAttribute("Applications");
for(ApplicationBean applications : list)
{
%>
	<tr>
			<td><%=applications.getStudentUsername()%></td>
			<td><%=applications.getSchoolName() %></td>	
			<td><%=applications.getBranch()%></td>
			<td><%=applications.getTotalMarks()%></td>
			<td><%=applications.getPercentage()%></td>
			<td><%=applications.getGpa()%></td>
			<td><%=applications.getDepartment()%></td>
			<td><%=applications.getCollegeName()%></td>
			<td><a href="ViewParticular?varname=<%=applications.getStudentUsername()%>">View</a></td>
	</tr>		
		<%} %>			
					</table>
</body>
</html>