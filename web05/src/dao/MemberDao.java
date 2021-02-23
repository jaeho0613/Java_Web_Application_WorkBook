package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import spms.vo.Member;

public class MemberDao {
	Connection connection;

	public void setConnectioin(Connection connection) {
		this.connection = connection;
	}

	public List<Member> selectList() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select mno, mname, email, cre_date from members order by mno asc");

			ArrayList<Member> members = new ArrayList<Member>();

			while (rs.next()) {
				members.add(new Member().setMno(rs.getInt("mno")).setMname(rs.getString("mname"))
						.setEmail(rs.getString("email")).setCre_date(rs.getDate("cre_date")));
			}
			return members;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {

			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e2) {

			}
		}
	}
}
