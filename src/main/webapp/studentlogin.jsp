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

		<form action="StudentLoginServlet" method="post">
			<table>
				<tr>
					<td>Student username:</td>
					<td><input type="text" name="username" required="required"></td>
				</tr>
				<tr>
					<td>Student password:</td>
					<td><input type="password" name="password" required="required"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Login"></td>
				</tr>
			</table>
		</form>

		<a href="login.jsp"><input type="button"
			value="Back"></a>
	</div>
</body>
</html>