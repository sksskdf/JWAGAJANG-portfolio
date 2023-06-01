package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import member.dao.MemberDao;
import member.model.Member;
import util.DBManager;



public class LoginService {
	private static LoginService instance = new LoginService();
	public static LoginService getInstance() {
		return instance;
	}
	private LoginService() { }
	
	public Member login(String id, String password) throws NamingException {
		Member member = null;
		MemberDao mDao = MemberDao.getInstance();
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			member = mDao.getMember(conn, id, password);
		} catch (SQLException e) {
			throw new RuntimeException("DB 처리 오류 :"+e.getMessage(), e);
		} finally {
			DBManager.close(conn);
		}
		return member;
	}
}
