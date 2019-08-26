<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%String collegeCode =(String) request.getAttribute("collegecode"); 
session.setAttribute("collegeCode",collegeCode);
%>
<form action="CollegeAdminServlet" method=post>

<input type="radio" name="adminfunc" value="1">View Applications<br>
<input type="radio" name="adminfunc" value="2">View Selected Students<br>
<input type="radio" name="adminfunc" value="3">View Selected Students department wise<br>

<input type="submit" value="submit">
<a href = "login.jsp"><input type="button" value="Logout"></a>
</form>
</body>
</html>