
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>World Adventures Airlines</title>
<link rel="stylesheet" href="resources/css/normalize.css">
<link rel="stylesheet" href="resources/css/theme.css">
</head>
<body>
	<div class="container">
		<div class="title">Add a passenger</div>
		<form action="AddPassenger" method="post">
			<%
				Boolean errors = (Boolean) request.getAttribute("errors");
				if (errors != null && errors) {
			%>
			<fieldset>
				<legend>Errors</legend>
				<ul>
					<%
						if (request.getAttribute("first_name_error") != null) {
					%>
					<li class="error">First name error</li>
					<%
						}
					%>
					<%
						if (request.getAttribute("last_name_error") != null) {
					%>
					<li class="error">Last name error</li>
					<%
						}
					%>
					<%
						if (request.getAttribute("birthdate_error") != null) {
					%>
					<li class="error">Birthday error</li>
					<%
						}
					%>
					<%
						if (request.getAttribute("gender_error") != null) {
					%>
					<li class="error">Gender error</li>
					<%
						}
					%>
				</ul>
			</fieldset>
			<%
				}
			%>
			<fieldset>
				<legend>Passenger details</legend>
				<div class="inputField">
					<label for="first-name" class="inputLabel">First name: </label> <input
						name="first-name" type="text"
						value="<%=request.getAttribute("first-name")%>"></input>
				</div>

				<div class="inputField">
					<label for="last-name" class="inputLabel">Last name: </label> <input
						name="last-name" type="text"
						value="<%=request.getAttribute("last-name")%>"></input>
				</div>

				<div class="inputField">
					<label for="dob" class="inputLabel">Date of birth: </label> <input
						name="dob" type="text"
						value="<%=request.getAttribute("dob")%>"></input>
				</div>

				<div class="inputField">
					<label for="gender" class="inputLabel">Gender : </label> <select
						name="gender">
						<option value="Male">Male</option>
						<option value="Female">Female</option>
					</select>
				</div>
			</fieldset>

			<div class="inputField" id="submitField">
				<input id="submitBtn" type="submit" value="Add new passenger"></input>
			</div>
		</form>
	</div>
</body>
</html>