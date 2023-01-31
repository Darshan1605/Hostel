import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Student;
import dto.Teacher;

@WebServlet("/teacherUpdated")
public class TeacherUpdated extends HttpServlet{
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
			PrintWriter pw = resp.getWriter();
			pw.write("ID not fount");
			RequestDispatcher rd = req.getRequestDispatcher("UpdateTeacher.html");
			rd.include(req, resp);
			resp.setContentType("text/html");
					
		}
		else {
			Teacher s1 = new Teacher();
			s1.setId(id);
			s1.setName(name);
			s1.setContactNo(contact);
			s1.setSalary(salary);
			
			et.begin();
			em.merge(s1);
			et.commit();
			
			PrintWriter pw = resp.getWriter();
			pw.write("Teacher Updated");
			RequestDispatcher rd = req.getRequestDispatcher("Teachers.jsp");
			rd.include(req, resp);
			resp.setContentType("text/html");
			
		}
	}
}
