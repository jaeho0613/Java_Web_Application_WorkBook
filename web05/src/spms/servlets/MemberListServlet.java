package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Member;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 컨텍스트 초기화 변수
			ServletContext sc = this.getServletContext();

			// AppInit Context에서 생성한 connection 객체
			conn = (Connection) sc.getAttribute("conn");

			// SQL 문을 실행할 객체
			stmt = conn.createStatement();

			// SQL 문을 서버에 보냄
			rs = stmt.executeQuery("select mno, mname, email, cre_date from members order by mno asc");

			response.setContentType("text/html; charset=UTF-8");
			ArrayList<Member> members = new ArrayList<Member>();

			// 데이터베이스에서 회원 정보를 가져와 Member에 담는다.
			// 그리고 Member객체를 ArrayList에 추가한다.
			while (rs.next()) {
				members.add(new Member(rs.getInt("mno"), rs.getString("email"), null, rs.getString("mname"),
						rs.getDate("cre_date"), null));
			}

			// request에 데이터 보관
			request.setAttribute("members", members);

			// JSP로 출력을 위임
			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberList.jsp");
			rd.include(request, response);

		} catch (Exception e) {
			// throw new ServletException(e);
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
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
