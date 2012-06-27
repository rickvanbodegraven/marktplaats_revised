<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="header.jsp"%>

<h1>${User.username}</h1>
<p>${User.woonplaats} - ${User.email}</p>


<table class="table table-striped">
	<thead>
	<tr>
		<th>#</th>
		<th>Titel</th>
		<th>Prijs</th>
		<th>Geplaatst door</th>
		<th>Locatie</th>
		</tr>
	</thead>
	<c:forEach var="advertisement" items="${User.advertisements}">
	<tr style="cursor: pointer;" onclick="window.location = 'advertisements?id=${advertisement.id}'">
		<td>${advertisement.id}</td>
		<td>
			<c:choose>
				<c:when test="${advertisement.status == 1}">
				${advertisement.title} - <b>VERKOCHT</b>
				</c:when>
				<c:otherwise>
					${advertisement.title}
				</c:otherwise>
			</c:choose>
		</td>
		<td>${advertisement.price}</td>
		<td>${advertisement.advertiser.username}</td>
		<td>${advertisement.advertiser.woonplaats}</td>
		</tr>	
	</c:forEach>
</table>


<%@include file="footer.jsp"%>