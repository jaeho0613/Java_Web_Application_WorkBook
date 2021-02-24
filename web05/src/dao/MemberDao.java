package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.vo.Member;

public class MemberDao {

	DataSource ds;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	// 회원 정보 전체 가져오기
	public List<Member> selectList() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
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
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {

			}
		}
	}

	// 등록하기
	public int insert(Member member) throws Exception {
		/* 회원등록 */
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(
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
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {

			}
		}
	}

	// no로 선택하기
	public Member selectOne(int no) throws Exception {
		/* 회원 상세 정보 조회 */
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
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
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {

			}
		}
	}

	// 정보 수정
	public int update(Member member) throws Exception {
		/* 회원 정보 변경 */
		PreparedStatement stmt = null;
		Connection conn = null;

		try {
			// conn = connPool.getConnection();
			conn = ds.getConnection();
			stmt = conn.prepareStatement("update members set email=?, mname=?, mod_date=now() where mno=?");
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
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {

			}
		}
	}

	// 회원 삭제
	public int delete(int no) throws Exception {
		/* 회원삭제 */
		PreparedStatement stmt = null;
		Connection conn = null;

		try {
			// conn = connPool.getConnection();
			conn = ds.getConnection();
			stmt = conn.prepareStatement("delete from members where mno = ?");
			stmt.setInt(1, no);
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
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {

			}
		}
	}

	// 로그인 체크
	public Member exist(String email, String password) throws Exception {
		/* 있으면 Member 객체 리턴, 없으면 null 리턴 */
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// conn = connPool.getConnection();
			conn = ds.getConnection();
			stmt = conn.prepareStatement("select mname, email from members where email = ? and pwd = ?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return new Member().setEmail(rs.getString("email")).setMname(rs.getString("mname"));
			} else {
				return null;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e2) {
			}
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {

			}
		}
	}

}
