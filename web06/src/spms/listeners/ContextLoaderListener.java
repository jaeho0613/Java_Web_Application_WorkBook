package spms.listeners;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import spms.controls.LogInController;
import spms.controls.LogOutController;
import spms.controls.MemberAddController;
import spms.controls.MemberDeleteController;
import spms.controls.MemberListController;
import spms.controls.MemberUpdateController;
import spms.dao.MySqlMemberDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

	@Override
	// 어플리케이션이 시작 될 때
	public void contextInitialized(ServletContextEvent sce) {
		try {

			// 최초 초기화 데이터를 저장할 ServletContext
			ServletContext sc = sce.getServletContext();

			// 웹 어플리케이션이 처음으로 배치될 때 설정되고 모든 설정된 엔트리 자원은
			// JNDI namespace의 java:comp/env 부분에 놓이게 된다.
			InitialContext initialContext = new InitialContext();
			
			// DB source 를 가져온다.
			DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/web06");

			// MySQL에 dataSource 주입 (DI)
			MySqlMemberDao memberDao = new MySqlMemberDao();
			memberDao.setDataSource(ds);

			// ServletContext 저장소에 각 Url로 Controller 데이터 초기화, 저장
			sc.setAttribute("/auth/login.do", new LogInController().setMemberDao(memberDao));
			sc.setAttribute("/auth/logout.do", new LogOutController());
			sc.setAttribute("/member/list.do", new MemberListController().setMemberDao(memberDao));
			sc.setAttribute("/member/add.do", new MemberAddController().setMemberDao(memberDao));
			sc.setAttribute("/member/update.do", new MemberUpdateController().setMemberDao(memberDao));
			sc.setAttribute("/member/delete.do", new MemberDeleteController().setMemberDao(memberDao));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}
