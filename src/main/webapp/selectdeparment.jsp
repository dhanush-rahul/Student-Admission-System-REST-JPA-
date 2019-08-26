<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="collegeadmin.jsp"%>

					 
						<form action="DeptServlet">
						<p>Choose department :</p>
						<input type="radio" name="dept" value="CSE">CSE
						<input type="radio" name="dept" value="ECE">ECE 
						<input type="radio" name="dept" value="IT">IT
						<input type="radio" name="dept" value="MECH">MECH 
						<input type="submit" value="View">
						</form> 
						
</body>
</html>