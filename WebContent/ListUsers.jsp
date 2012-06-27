<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="header.jsp" %>

<h1>Alle gebruikers</h1>

<table class="table table-striped">
	<thead>
	<tr>
		<th>#</th>
		<th>Gebruikersnaam</th>
		<th>Email</th>
		<th>Woonplaats</th>
	</tr>
	</thead>
	<c:forEach var="user" items="${Users}">
	<tr style="cursor: pointer;" onclick="window.location = 'users?id=${user.id}'">
		<td>${user.id}</td>
		<td>${user.username}</td>
		<td>${user.email}</td>
		<td>${user.woonplaats}</td>
		</tr>	
	</c:forEach>
</table>

<%@include file="footer.jsp" %>
