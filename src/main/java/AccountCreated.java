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

import dto.Principal;

@WebServlet("/accountcreated")
public class AccountCreated extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = (String)req.getParameter("name");
		String age1 =(String)req.getParameter("age");
		int age = Integer.parseInt(age1);
		String email = (String)req.getParameter("email");	
		String contact1 = (String)req.getParameter("contact");
		long contact = Long.parseLong(contact1);
		String password = (String)req.getParameter("password");
		String Password1 = (String)req.getParameter("password1");
		
		
		
		if(password.equals(Password1)) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("darshan");
			EntityManager em = emf.createEntityManager();
			EntityTransaction et = em.getTransaction();
			
			
			Query q1 = em.createQuery("select a from Principal a where a.contact=?1");
			q1.setParameter(1, contact);
			List<Principal> l1 = q1.getResultList();
			
			if(l1.isEmpty()) {
			
			Query q = em.createQuery("select a from Principal a where a.email=?1");
			q.setParameter(1, email);
			List<Principal> l = q.getResultList();
			if(l.isEmpty()) {
			
			Principal p = new Principal();
			p.setName(name);
			p.setAge(age);
			p.setEmail(email);
			p.setContact(contact);
			p.setPassword(password);
			et.begin();
			em.persist(p);
			et.commit();
			
			PrintWriter pw = resp.getWriter();
			pw.write("Account Created");
			RequestDispatcher rd = req.getRequestDispatcher("Login.html");
			rd.include(req, resp);
			resp.setContentType("text/html");
			}
			else
			{
				PrintWriter pw = resp.getWriter();
				pw.write("Email Already Exist");
				RequestDispatcher rd = req.getRequestDispatcher("CreateAccount.html");
				rd.include(req, resp);
				resp.setContentType("text/html");
			}
			}
			else {
				PrintWriter pw = resp.getWriter();
				pw.write("Contact number Already Exist");
				RequestDispatcher rd = req.getRequestDispatcher("CreateAccount.html");
				rd.include(req, resp);
				resp.setContentType("text/html");
			}
		}
		else {
			PrintWriter pw = resp.getWriter();
			pw.write("Password Doesn't matches");
			RequestDispatcher rd = req.getRequestDispatcher("CreateAccount.html");
			rd.include(req, resp);
			resp.setContentType("text/html");
		}
		
		
		
		
	}
}
