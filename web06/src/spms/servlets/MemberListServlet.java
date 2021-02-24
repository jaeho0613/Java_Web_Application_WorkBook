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

import dao.MemberDao;
import spms.vo.Member;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 컨텍스트 초기화 변수
			ServletContext sc = this.getServletContext();

			// Attribute에서 가져오기
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");

			// request에 데이터 보관
			request.setAttribute("members", memberDao.selectList());
			request.setAttribute("viewUrl", "/member/MemberList.jsp");
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
