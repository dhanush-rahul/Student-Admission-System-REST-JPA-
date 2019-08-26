<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method=post action=AddCollegeServlet>
<table>
<tr><td>Enter Name of the college: </td><td><input type="text" name="collegeName"></td></tr>
<tr><td>Enter College Code: </td><td><input type="text" name="collegeCode"></td></tr>
<tr><td>Enter College Admin Username: </td><td><input type="text" name="adminName"></td></tr>
<tr><td>Enter College Admin Password: </td><td><input type="text" name="adminPassword"></td></tr>
<tr><td><input type="submit" value="Add college"></td></tr>
</table>
</form>
</body>
</html>