<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.SelectedStudentsBean,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
<th>Name</th>
<th>Board</th>
<th>School Name</th>
<th>College Name</th>
<th>Department Choice</th>


<%
			ArrayList<SelectedStudentsBean> list = (ArrayList<SelectedStudentsBean>) request.getAttribute("selectedapplications");
			
			for(SelectedStudentsBean dept: list){
				%>
				<tr>

					<td><%= dept.getStudentUsername()%></td>
					<td><%= dept.getBranch()%></td>
					<td><%= dept.getSchoolName()%></td>
					<td><%= dept.getCollegeName()%></td>
					<td><%= dept.getDepartment()%></td> 
			
				</tr>
			<%	}
			%>
			</table>
</body>
</html>