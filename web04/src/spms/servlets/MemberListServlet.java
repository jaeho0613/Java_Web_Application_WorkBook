package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 컨텍스트 초기화 변수
			ServletContext sc = this.getServletContext();

			// 설정한 드라이버 구현체 등록
			Class.forName(sc.getInitParameter("driver"));

			// MySQL 서버에 연결
			// - 연결 성공시 Connection 객체 반환
			conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"),
					sc.getInitParameter("password"));

			// SQL 문을 실행할 객체
			stmt = conn.createStatement();

			// SQL 문을 서버에 보냄
			rs = stmt.executeQuery("select mno, mname, email, cre_date from members order by mno asc");

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>회원목록</title><head>");
			out.println("<body><h1>회원목록</h1>");
			out.println("<p><a href='add'>신규 회원</a></p>");
			while (rs.next()) {
				out.println(
						rs.getInt("mno") + "," +
						"<a href='update?no=" + rs.getInt("mno") + "'>" +
						rs.getString("mname")
						+ "</a>," + rs.getString("email") + "," +
						rs.getDate("cre_date") + 
						"<a href='delete?no=" + rs.getInt("mno") + "'> [삭제] </a>" +
						" <br>");
			}
			out.println("</body></html>");
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
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {

			}
		}

	}

}
