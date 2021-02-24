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

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("viewUrl", "/member/MemberAdd.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			ServletContext sc = this.getServletContext();

			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");

			Member member = (Member) req.getAttribute("member");
			memberDao.insert(member);

			req.setAttribute("viewUrl", "redirect:list.do");

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
