package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import spms.vo.Member;

public class MemberDao {
	Connection connection;

	// 의존성 주입
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

	public int insert(Member member) throws Exception {
		/* 회원등록 */
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(
					"insert into members(email, pwd, mname, cre_date, mod_date) values(?,?,?,now(),now())");

			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPwd());
			stmt.setString(3, member.getMname());
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e2) {

			}
		}
	}

//	public int delete(int no) throws Exception {
//		/* 회원삭제 */
//	}
//
	public Member selectOne(int no) throws Exception {
		/* 회원 상세 정보 조회 */
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select mno, email, mname, cre_date from members where mno=" + no);
			rs.next();

			Member member = new Member().setMno(rs.getInt("mno")).setMname(rs.getString("mname"))
					.setEmail(rs.getString("email")).setCre_date(rs.getDate("cre_date"));

			return member;

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e2) {
			}
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
			}
		}
	}

	public int update(Member member) throws Exception {
		/* 회원 정보 변경 */
		PreparedStatement stmt = null;

		try {
			System.out.println("update!");
			stmt = connection.prepareStatement("update members set email=?, mname=?, mod_date=now() where mno=?");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getMname());
			stmt.setInt(3, member.getMno());

			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e2) {
			}
		}
	}
//
//	public Member exist(String email, String password) throws Exception {
//		/* 있으면 Member 객체 리턴, 없으면 null 리턴 */
//	}
}
