<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.util.*,bean.Login,bean.Colleges"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
ArrayList<Colleges> list = (ArrayList<Colleges>) request.getAttribute("list");
int count=0;
%>
<form action="RegistrationServlet" method="post">
		<div id="form-wrapper" style="max-width: 500px; margin: auto;">
			<table align="center">
				<tr>
					<td>Enter Name of the Applicant*:</td>
					<td><input type="text" name="name" required="required"></td>
				</tr>
				<tr>
				<td>Enter Password*:</td>
				<td><input type="password" name="password" required="required"></td> 
				<tr>
					<td>Board (SSC or CBSE)*:</td>
					<td><select name="branch" id="board1" required>
					<option value="">Choose</option>
							<option value="SSC">SSC</option>
							<option value="CBSE">CBSE</option></select></td>
				</tr>

				<tr>
					<td>Total Marks obtained*:</td>
					<td><input type="text" name="totalmarks" required="required"></td>
				</tr>
				<tr>
					<td>Percentage obtained(if state board)*:</td>
					<td><input type="text" name="percentage" required="required"></td>
				</tr>
				<tr>
					<td>GPA (if CBSE)*</td>
					<td><input type="text" name="gpa" required="required"></td>
				</tr>
				<tr>
					<td>Enter School Name*</td>
					<td><input type="text" name="school" required="required"></td>
				</tr>
				<tr>
					<td>Choose college: </td>
					<td><select name="college" required="required">
					<option value="">Choose</option>
  						  <%for(Colleges college : list) {count++;if(count==1) continue;%>
				       			 <option value="<%=college.getCollegeName()%>"><%=college.getCollegeName()%></option>
				       			 <%} %><td>
				 		  
					</select>
				</tr>
				<tr>
					<td>Choice of department*</td>
					<td><select name="dept_choice" required="required">
					<option value="">Choose</option>
							<option value="CSE">CSE</option>
							<option value="ECE">ECE</option>
							<option value="IT">IT</option>
							<option value="MECH">MECH</option>
					</select></td>
				</tr>
				<tr>
					<td><input type="submit" value="Register"></td>
				</tr>

			</table>
		</div>
	</form>
</body>
</html>