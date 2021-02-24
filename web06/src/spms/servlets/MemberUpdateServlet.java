package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import spms.vo.Member;

@SuppressWarnings("serial")
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int no = Integer.parseInt(req.getParameter("no"));
			ServletContext sc = this.getServletContext();

			// AppInit Context에서 생성한 connection 객체
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");

			Member member = memberDao.selectOne(no);

			req.setAttribute("member", member);

			resp.setContentType("text/html; charset=utf-8");

			RequestDispatcher rd = req.getRequestDispatcher("/member/MemberUpdate.jsp");
			rd.include(req, resp);

		} catch (Exception e) {
			// System.out.println("MemberUpdateServlet : doGet오류");
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ServletContext sc = this.getServletContext();

			// AppInit Context에서 생성한 connection 객체
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
			memberDao.update(new Member().setEmail(req.getParameter("email")).setMname(req.getParameter("name"))
					.setMno(Integer.parseInt(req.getParameter("no"))));

			resp.sendRedirect("list");

		} catch (Exception e) {
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
		}
	}
}
