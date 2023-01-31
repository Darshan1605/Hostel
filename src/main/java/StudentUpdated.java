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

@WebServlet("/studentUpdated")
public class StudentUpdated extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id1 = (String)req.getParameter("id");
		int id = Integer.parseInt(id1);
		String name = (String)req.getParameter("name");
		String contact1 = (String)req.getParameter("contact");
		long contact = Long.parseLong(contact1);
		String fees1 = (String)req.getParameter("fees");
		double fees = Double.parseDouble(fees1);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("darshan");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		Student s = em.find(Student.class, id);
		if(s==null) {
			PrintWriter pw = resp.getWriter();
			pw.write("ID not fount");
			RequestDispatcher rd = req.getRequestDispatcher("UpdateStudent.html");
			rd.include(req, resp);
			resp.setContentType("text/html");
					
		}
		else {
			Student s1 = new Student();
			s1.setId(id);
			s1.setName(name);
			s1.setContactNo(contact);
			s1.setFees(fees);
			
			et.begin();
			em.merge(s1);
			et.commit();
			
			PrintWriter pw = resp.getWriter();
			pw.write("Student Updated");
			RequestDispatcher rd = req.getRequestDispatcher("Student.jsp");
			rd.include(req, resp);
			resp.setContentType("text/html");
			
		}
	}
}
