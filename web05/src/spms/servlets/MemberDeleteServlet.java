package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			ServletContext sc = this.getServletContext();
			
			// AppInit Context에서 생성한 connection 객체
			conn = (Connection) sc.getAttribute("conn");

			stmt = conn.prepareStatement("delete from members where mno = ?");
			stmt.setString(1, req.getParameter("no"));
			stmt.executeUpdate();

			resp.sendRedirect("list");

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
	}
}
