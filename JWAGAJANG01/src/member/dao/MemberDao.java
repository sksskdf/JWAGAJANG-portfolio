package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.model.Member;
import util.DBManager;


public class MemberDao {
	//싱글톤패턴
	private static MemberDao instance = new MemberDao();
	private MemberDao() { }
	public static MemberDao getInstance() {
		return instance;
	}
	//회원정보 불러오기
	public Member getMember(Connection conn, String id, String password) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		try {
			String query = "select * from table_user where user_id=? and user_pw=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = makeMemberFromResultSet(rs);
			}
		} finally {
			DBManager.close(rs);
			DBManager.close(pstmt);
		}
		return member;
	}
	//ID 선택
	public Member selectById(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from table_user where user_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			Member item = makeMemberFromResultSet(rs);
			return item;
		} finally {
			DBManager.close(rs);
			DBManager.close(pstmt);
		}
	}
	
	//ResultSet에 저장된 DB정보를 membervo에 저장.
	public Member makeMemberFromResultSet(ResultSet rs) throws SQLException {
		Member member = new Member();
		member.setId(rs.getString("user_id"));
		member.setPwd(rs.getString("user_pw"));
		member.setMobile(rs.getString("user_phone"));
		member.setEmail(rs.getString("user_email"));
		member.setCreatedAt(rs.getTimestamp("user_regdate"));
		member.setModifiedAt(rs.getTimestamp("user_editdate"));
		member.setGrade(rs.getInt("user_grade"));
		return member;
	}
	
	public int insert(Connection conn, Member member) throws SQLException {
		PreparedStatement pstmt = null;
		//기본값은 -1로 초기화
		int result = -1;
		try {
			String query = "insert into table_user (user_id, user_pw, user_email, user_phone, user_address, user_adddetail"
					+ ") values(?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getMobile());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getAdddetail());
			result = pstmt.executeUpdate();
			
		} finally {
			DBManager.close(pstmt);
		}
		return result;
	}
	public void update(Connection conn, Member member) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE `jwagajang`.`table_user` \r\n"
					+ "SET \r\n"
					+ "	`user_pw` = ?, \r\n"
					+ "	`user_phone` = ?, \r\n"
					+ "	`user_address` = ?, \r\n"
					+ "	`user_adddetail` = ?, \r\n"
					+ "	`user_email` = ?\r\n"
					+ " WHERE (`user_id` = ?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getPwd());
			pstmt.setString(2, member.getMobile());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getAdddetail());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getId());
			
			pstmt.executeUpdate();
			
		} finally {
			DBManager.close(pstmt);
		}

	}
}
