package spms.servlets;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spms.controls.Controller;
import spms.controls.LogInController;
import spms.controls.LogOutController;
import spms.controls.MemberAddController;
import spms.controls.MemberDeleteController;
import spms.controls.MemberListController;
import spms.controls.MemberUpdateController;
import spms.vo.Member;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		String servletPath = request.getServletPath();
		System.out.println("servletPath: " + servletPath);
		try {
			ServletContext sc = this.getServletContext();

			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("memberDao", sc.getAttribute("memberDao"));
			model.put("session", request.getSession());

			Controller pageController = null;

			switch (servletPath) {
			case "/member/list.do":
				pageController = new MemberListController();
				break;
			case "/member/add.do":
				pageController = new MemberAddController();

				if (request.getParameter("email") != null) {
					model.put("member", new Member().setEmail(request.getParameter("email"))
							.setPwd(request.getParameter("password")).setMname(request.getParameter("name")));
				}
				break;
			case "/member/update.do":
				pageController = new MemberUpdateController();

				if (request.getParameter("email") != null) {
					model.put("member", new Member().setMno(Integer.parseInt(request.getParameter("no")))
							.setEmail(request.getParameter("email")).setMname(request.getParameter("name")));
				} else {
					model.put("no", Integer.parseInt(request.getParameter("no")));
				}
				break;
			case "/member/delete.do":
				pageController = new MemberDeleteController();

				model.put("no", Integer.parseInt(request.getParameter("no")));
				break;
			case "/auth/login.do":
				pageController = new LogInController();

				if (request.getParameter("email") != null && request.getParameter("password") != null) {
					model.put("info", new Member().setEmail(request.getParameter("email"))
							.setPwd(request.getParameter("password")));
				}
				break;
			case "/auth/logout.do":
				pageController = new LogOutController();
				break;
			}

			String viewUrl = pageController.execute(model);

			for (String key : model.keySet()) {
				System.out.println("key : " + key);
				request.setAttribute(key, model.get(key));
			}

			if (viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;

			} else {
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}
}
