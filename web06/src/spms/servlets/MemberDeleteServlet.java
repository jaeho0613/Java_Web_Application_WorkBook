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

@SuppressWarnings("serial")
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ServletContext sc = this.getServletContext();

			// AppInit Context에서 생성한 connection 객체
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
			memberDao.delete(Integer.parseInt(req.getParameter("no")));

			req.setAttribute("viewUrl", "redirect:list.do");

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
