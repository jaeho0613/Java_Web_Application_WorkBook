package spms.servlets;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

@SuppressWarnings("serial")
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int no = Integer.parseInt(req.getParameter("no"));
			ServletContext sc = this.getServletContext();

			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");

			Member member = memberDao.selectOne(no);

			req.setAttribute("member", member);
			req.setAttribute("viewUrl", "/member/MemberUpdate.jsp");

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ServletContext sc = this.getServletContext();

			// AppInit Context에서 생성한 connection 객체
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
			Member member = (Member) req.getAttribute("member");

			memberDao.update(member);

			req.setAttribute("viewUrl", "redirect:list.do");

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
