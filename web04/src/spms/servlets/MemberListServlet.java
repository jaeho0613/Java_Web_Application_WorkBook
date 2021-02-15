package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/member/list")
public class MemberListServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 설정한 드라이버 구현체 등록
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());

			// MySQL 서버에 연결
			// - 연결 성공시 Connection 객체 반환
			conn = DriverManager.getConnection("jdbc:mysql://localhost/web04", "root", "362514");
			
			// SQL 문을 실행할 객체
			stmt = conn.createStatement();
			
			// SQL 문을 서버에 보냄
			rs = stmt.executeQuery("select mno, mname, email, cre_date from members order by mno asc");

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>회원목록</title><head>");
			out.println("<body><h1>회원목록</h1>");
			while (rs.next()) {
				out.println(rs.getInt("mno") + " ," + rs.getString("mname") + " ," + rs.getString("email") + " ,"
						+ rs.getDate("cre_date") + " <br>");
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
