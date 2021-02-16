package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("<html>\r\n" + "  <head>\r\n" + "    <title>회원 등록</title>\r\n" + "  </head>\r\n" + "  <body>\r\n"
				+ "    <h1>회원 등록</h1>\r\n" + "    <form action=\"add\" method=\"post\" accept-charset=\"utf-8\">\r\n"
				+ "      이름: <input type=\"text\" name=\"name\" /><br />\r\n"
				+ "      이메일: <input type=\"text\" name=\"email\" /><br />\r\n"
				+ "      암호: <input type=\"password\" name=\"password\" /><br />\r\n"
				+ "      <input type=\"submit\" value=\"추가\" />\r\n" + "      <input type=\"reset\" value=\"취소\" />\r\n"
				+ "    </form>\r\n" + "  </body>\r\n" + "</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));

			conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"),
					sc.getInitParameter("password"));
			stmt = conn.prepareStatement(
					"insert into members(email, pwd, mname, cre_date, mod_date) values(?,?,?,now(),now())");
			stmt.setString(1, req.getParameter("email"));
			stmt.setString(2, req.getParameter("password"));
			stmt.setString(3, req.getParameter("name"));
			stmt.executeUpdate();

			resp.sendRedirect("list");

			// *리다이렉트는 HTML을 출력하지 않는다.
//			resp.setContentType("text/html; charset=utf-8");
//			PrintWriter out = resp.getWriter();
//			out.println("<html>\r\n" 
//			+ "  <head>\r\n" 
//			+ "    <title>회원등록결과</title>\r\n"
//			+ "<meta http-equiv='Refresh' content='1; url=list'>"
//			+ "  </head>\r\n"
//			+ "  <body>\r\n" 
//			+ "    <p>등록 성공입니다!</p>\r\n" 
//			+ "  </body>\r\n" 
//			+ "</html>\r\n" + "");

			// 리프래시 정보를 응답 헤더에 추가
			// resp.addHeader("Refresh", "1;url=list");
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
