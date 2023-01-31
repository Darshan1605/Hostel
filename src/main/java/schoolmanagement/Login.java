package schoolmanagement;

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

@WebServlet("/Login")
public class Login extends HttpServlet{
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("darshan");
			EntityManager em = emf.createEntityManager();
			EntityTransaction et = em.getTransaction();
			
			String email = (String)req.getParameter("email");
			String password = (String)req.getParameter("password");
			
			Query q = em.createQuery("select a from Principal a where a.email=?1 and a.password=?2");
			q.setParameter(1, email);
			q.setParameter(2, password);
			List<Principal> l = q.getResultList();
			if(l.isEmpty()) {
				PrintWriter pw = resp.getWriter();
				pw.write("Invalid Credential");
				RequestDispatcher rd = req.getRequestDispatcher("Login.html");
				rd.include(req, resp);
				resp.setContentType("text/html");
			}
			else {
				RequestDispatcher rd = req.getRequestDispatcher("Principal.html");
				rd.forward(req, resp);
			}
			}

}
