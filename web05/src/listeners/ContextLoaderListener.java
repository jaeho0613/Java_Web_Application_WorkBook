package listeners;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.MemberDao;
import util.DBConnectionPool;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
	// Connection conn;
	DBConnectionPool connPool;

	@Override
	// 어플리케이션이 시작 될 때
	public void contextInitialized(ServletContextEvent sce) {
		try {
			ServletContext sc = sce.getServletContext();

			/*
			 * Class.forName(sc.getInitParameter("driver")); conn =
			 * DriverManager.getConnection(sc.getInitParameter("url"),
			 * sc.getInitParameter("username"), sc.getInitParameter("password"));
			 */
			
			connPool = new DBConnectionPool(sc.getInitParameter("driver"), sc.getInitParameter("url"), sc.getInitParameter("username"), sc.getInitParameter("password"));

			MemberDao memberDao = new MemberDao();
			// memberDao.setConnectioin(conn);
			memberDao.setDbConnectionPool(connPool);

			sc.setAttribute("memberDao", memberDao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		connPool.closeAll();
	}
}