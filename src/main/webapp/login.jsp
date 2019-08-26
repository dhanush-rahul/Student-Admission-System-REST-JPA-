<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="form-wrapper" style="max-width: 500px; margin: auto;">

		<form action="AdminLogin" method="post">
			<table>
				<tr>
					<td>Admin username:</td>
					<td><input type="text" name="username" required="required"></td>
				</tr>
				<tr>
					<td>Admin password:</td>
					<td><input type="password" name="password" required="required"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Login" ></td>
				</tr>
			</table>
		</form>

		<a href="AdmissionFormServlet"><input type="button"
			value="Student Registration"></a>
			<a href="studentlogin.jsp"><input type="button" value="Student Login"></a>
	</div>
</body>
</html>