<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,bean.Login,bean.Colleges"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border=1>
<%
ArrayList<Login> list = (ArrayList<Login>) request.getAttribute("admins");
int count=0;
for(Login login : list)
{ count++;
%>
	<tr>
	<%if(count==1)
		continue;
	Colleges collegebean = login.getCollegeCode(); %>
			<td><%=login.getUsername()%></td>
			<td><%=collegebean.getCollegeName() %></td>
			<td><%=collegebean.getCollegeCode()%></td>
	</tr>		
		<%} %>			
					</table>
</body>
</html>