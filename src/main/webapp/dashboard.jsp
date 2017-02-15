<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#dob").datepicker({ dateFormat: 'yy-mm-dd' });
});

</script>


<style type="text/css">
body	{
	padding: 25px;
}
</style>

</head>
<body>
	
<h1>Welcome ${name}!</h1> 	
<p><a href="logout.com">Log out</a></p>

	
	<div class="panel panel-primary">
	  <div class="panel-heading">
	    <h3 class="panel-title">User Entry</h3>
	  </div>
	  
	  <div class="panel-body">
	    <form:form action="processUserDetail.com" method="post" modelAttribute="user">
	    
	      <form:hidden path="id" id="id"/>
	    
		  <div class="form-group">
		    <label for="name">Name</label>
		    <form:input cssClass="form-control" id="name" path="name" placeholder="Name"/>
		  </div>
		  <div class="form-group">
		    <label for="dob">DOB</label>
		    <form:input cssClass="form-control" id="dob" path="dob" placeholder="DOB"/>
		  </div>
		  <div class="form-group">
		    <label for="phoneNo">Phone Number</label>
		    <form:input cssClass="form-control" id="phoneNo" path="phoneNo" placeholder="Phone Number" />
		  </div>
		  <div class="form-group">
		    <label for="username">Username</label>
		    <form:input cssClass="form-control" id="username" path="username" placeholder="Username"/>
		  </div>
		  <div class="form-group">
		    <label for="password">Password</label>
		    <form:input type="password" cssClass="form-control" id="password" path="password" placeholder="Password" />
		  </div>
		  		  
		  <button type="submit" class="btn btn-default">Submit</button>
		</form:form>
	  </div>
	</div>
	
	<div class="panel panel-primary">
	  <div class="panel-heading">
	    <h3 class="panel-title">User Details</h3>
	  </div>
	  
	  <div class="panel-body">
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>Id</th>				
					<th>Name</th>
					<th>Phone Number</th>
					<th>Username</th>
					<th>DOB</th>
					<th>Actions</th>					
				</tr>
			</thead>
			<tbody>
				<%-- <%
					List<User> list = (List<User>) request.getAttribute("list");
					
		    		for(User user : list)	{
		    	%> --%>
		    	
		    	<!-- JSTL -->
		    	<c:forEach items="${users}" var="row">
		    		<tr>
		    			<td>${row.id}</td>
		    			<td>${row.name}</td>
		    			<td>${row.phoneNo}</td>
		    			<td>${row.username}</td>
		    			<td><fmt:formatDate value="${row.dob}" pattern="yyyy-MM-dd" /></td>
		    			<td><a href="edit.com?id=${row.id}">Edit</a> / <a href="delete.com?id=${row.id}">Delete</a></td>
		    		</tr> 
		    	
		    	</c:forEach>
		    		
		    	<%-- <%	
		    		}
				%> --%>
			
			
			</tbody>
		
		</table>
		
	  </div>
	</div>


</body>
</html>









