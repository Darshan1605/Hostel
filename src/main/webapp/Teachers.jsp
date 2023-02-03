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
<style>
.a{
    background: none;
    background-color: #00a400;
    border: none;
    border-radius: 6px;
    box-shadow: none;
    color: #fff;
    font-size: 18px;
    font-weight: 600;
    height: 36px;
    overflow: hidden;
    padding: 0 32px;
    text-shadow: none;
    cursor: pointer;
}
.r{
    background: none;
    background-color: red;
    border: none;
    border-radius: 6px;
    box-shadow: none;
    color: #fff;
    font-size: 18px;
    font-weight: 600;
    height: 36px;
    overflow: hidden;
    padding: 0 32px;
    text-shadow: none;
    cursor: pointer;
}
.u{
    background: none;
    background-color: rgb(196, 213, 40);
    border: none;
    border-radius: 6px;
    box-shadow: none;
    color: #fff;
    font-size: 18px;
    font-weight: 600;
    height: 36px;
    overflow: hidden;
    padding: 0 32px;
    text-shadow: none;
    cursor: pointer;
}
#m{
	font-size: 20px;
	font-style: italic;

}
#h{
	font-style: italic;
	
	color: purple;
	border: 20px;
	}
</style>
<center>
<% EntityManagerFactory emf = Persistence.createEntityManagerFactory("darshan");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	Query q = em.createQuery("select a from Teacher a");
	List<Teacher> l  = q.getResultList();
	%>
	<table cellpadding = "20px" border="1">
	<h3 id="h">Teacher Data</h3>
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
	
	<input class="a" type = "submit" Value = "Add Teacher"><br><br>
	</form>
	
	<form action="updateteacher" method = "post">
	<input class = "u" type = "submit" Value = "Update Teacher"><br><br>
	</form>
	
	<form action="removeteacher" method="post">
	
	<input class = "r" type = "submit" Value = "Remove Teacher"><br><br>
	</form>
	
	<a id="m" href="http://localhost:8080/schoolmanagement/Principal.html">Main Menu</a><br><br>
	
</center>

</body>
</html>