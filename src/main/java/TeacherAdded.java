import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Student;
import dto.Teacher;

@WebServlet("/teacheradded")
public class TeacherAdded extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id1 = (String)req.getParameter("id");
		int id = Integer.parseInt(id1);
		String name = (String)req.getParameter("name");
		String contact1 = (String)req.getParameter("contact");
		long contact = Long.parseLong(contact1);
		String salary1 = (String)req.getParameter("salary");
		double salary = Double.parseDouble(salary1);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("darshan");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		Teacher s = em.find(Teacher.class, id);
		if(s==null) {
			Query q = em.createQuery("select a from Teacher a where a.contactNo=?1");
			q.setParameter(1, contact);
			List<Student> l = q.getResultList();
			if(l.isEmpty()) {
				Teacher s1 = new Teacher();
				s1.setId(id);
				s1.setName(name);
				s1.setContactNo(contact);
				s1.setSalary(salary);
				
				et.begin();
				em.persist(s1);
				et.commit();
				
				PrintWriter pw = resp.getWriter();
				pw.write("Teacher Added...");
				RequestDispatcher rd = req.getRequestDispatcher("Teachers.jsp");
				rd.include(req, resp);
				resp.setContentType("text/html");
			
				
			}else {
				PrintWriter pw = resp.getWriter();
				pw.write("Contact Already Present");
				RequestDispatcher rd = req.getRequestDispatcher("AddTeacher.html");
				rd.include(req, resp);
				resp.setContentType("text/html");
			}
			
		}
		else {
			PrintWriter pw = resp.getWriter();
			pw.write("Id Already Present");
			RequestDispatcher rd = req.getRequestDispatcher("AddTeacher.html");
			rd.include(req, resp);
			resp.setContentType("text/html");
		}
	}

}
