<%@page import="dto.Teacher"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.Query"%>
<%@page import="javax.persistence.EntityTransaction"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teacher Section</title>
</head>
<body>
<center>
<% EntityManagerFactory emf = Persistence.createEntityManagerFactory("darshan");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	Query q = em.createQuery("select a from Teacher a");
	List<Teacher> l  = q.getResultList();
	%>
	<table cellpadding = "20px" border="1">
	<h3>Teacher Data</h3>
		<th>Id</th>
		<th>Name</th>
		<th>ContactNo</th>
		<th>Salary</th>
		<%for(Teacher t:l){ %>
		<tr align="center">
		<td><%=t.getId()%></td>
		<td><%=t.getName()%></td>
		<td><%=t.getContactNo()%></td>
		<td><%=t.getSalary()%></td>
		</tr><%} %>
		
		
	</table>
	<br><br>
	<form action="addteacher" method="post">
	
	<input type = "submit" Value = "Add Teacher"><br><br>
	</form>
	
	<form action="updateteacher" method = "post">
	<input type = "submit" Value = "Update Teacher"><br><br>
	</form>
	
	<form action="removeteacher" method="post">
	
	<input type = "submit" Value = "Remove Teacher"><br><br>
	</form>
	
	<a href="http://localhost:8080/schoolmanagement/Principal.html">Main Menu</a><br><br>
	
</center>

</body>
</html>