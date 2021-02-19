package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.vo.Member;

@WebServlet("/auth/login")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/auth/LogInForm.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Login servlet doPost");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			stmt = conn.prepareStatement("select mname, email from members where email = ? and pwd = ?");
			stmt.setString(1, req.getParameter("email"));
			stmt.setString(2, req.getParameter("password"));
			rs = stmt.executeQuery();

			if (rs.next()) {
				Member member = new Member(0, rs.getString("email"), null, rs.getString("mname"), null, null);
				HttpSession session = req.getSession();
				session.setAttribute("member", member);

				resp.sendRedirect("../member/list");
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("/auth/LogInFail.jsp");
				rd.forward(req, resp);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {

			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {

			}
		}
	}

}
