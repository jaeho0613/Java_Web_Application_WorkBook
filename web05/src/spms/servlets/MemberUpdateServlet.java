package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Member;

@SuppressWarnings("serial")
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			ServletContext sc = this.getServletContext();

			// AppInit Context에서 생성한 connection 객체
			conn = (Connection) sc.getAttribute("conn");

			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"select mno, email, mname, cre_date from members where mno=" + req.getParameter("no"));
			rs.next();

			resp.setContentType("text/html; charset=utf-8");
			Member member = new Member().setMno(rs.getInt("mno")).setMname(rs.getString("mname"))
					.setEmail(rs.getString("email")).setCre_date(rs.getDate("cre_date"));
			
			req.setAttribute("member", member);
			
			RequestDispatcher rd = req.getRequestDispatcher("/member/MemberUpdate.jsp");
			rd.include(req, resp);
			
		} catch (Exception e) {
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
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
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (Exception e) {
//			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			ServletContext sc = this.getServletContext();

			// AppInit Context에서 생성한 connection 객체
			conn = (Connection) sc.getAttribute("conn");

			stmt = conn.prepareStatement("update members set email=?, mname=?, mod_date=now() where mno=?");
			stmt.setString(1, req.getParameter("email"));
			stmt.setString(2, req.getParameter("name"));
			stmt.setInt(3, Integer.parseInt(req.getParameter("no")));

			stmt.executeUpdate();

			resp.sendRedirect("list");

		} catch (Exception e) {
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (Exception e) {
//			}
		}
	}

}
