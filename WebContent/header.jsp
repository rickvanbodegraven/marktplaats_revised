<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<html lang="en">
<head>
<base href="/marktplaats_revised/" />
<meta charset="utf-8">
<title>Marktplaats revised</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link href="/marktplaats_revised/css/bootstrap.css" rel="stylesheet">
<style>
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}

form {
margin: 3px 0 0 0;
}

#search_advert_bar input {
margin: 3px 0 0 0;
}

#search_advert_bar  {
margin: 0 0 0 0;
float: right;
padding: 3px 0 0 0;
}

form
{
	margin:0;
}
form input.btn
{
	float:left;

	margin:0;
		margin-right:10px;
}
.CommonTextTxt
{
	min-width:300px;
	height:100px;
}

</style>
<link href="/marktplaats_revised/css/bootstrap-responsive.css" rel="stylesheet">

<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>
    <div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a> <a class="brand" href="/marktplaats_revised">Marktplaats revised</a>
			
			<div class="nav-collapse">
				<ul class="nav">
					<li><a href="/marktplaats_revised">Voorpagina</a></li>
					<c:choose>
						<c:when test="${not empty pageContext.request.userPrincipal}">
							<li><a href="logout">Uitloggen</a></li>
							<li><a href="adminUser?id=${pageContext.request.userPrincipal}">Mijn profiel (${pageContext.request.userPrincipal})</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="signup.jsp">Sign up</a></li>
							<li><a href="login.jsp">Login</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
				
			<div id="search_advert_bar">
				<form method="post" action="advertisements">
					<input type="text" placeholder="Zoek advertentie"
						name="advertisement_search_keyword" />
				</form>
			</div>				
				
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	</div>

    <div class="container">
      <div class="row-fluid">
        <div class="span3">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
            	<li class="nav-header">Opties</li>              
				<li><a href="AddAdvertisement.jsp">Voeg een advertentie toe</a></li>
				<li><a href="advertisements">Alle advertenties</a></li>
				<li><a href="users">Alle gebruikers</a></li>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
        
			<div class="span9">
        
        