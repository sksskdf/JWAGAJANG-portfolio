package member.service;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import member.dao.MemberDao;
import member.model.Member;
import util.DBManager;

public class MemberServiceImpl implements MemberService {
	private static MemberServiceImpl instance = new MemberServiceImpl();
	public static MemberServiceImpl getInstance() {
		return instance;
	}
	private MemberServiceImpl() { }
	
	@Override
	public void add(Member member) {
		MemberDao mDao = MemberDao.getInstance();
		Connection conn = null;

		try {
			conn = DBManager.getConnection();
			mDao.insert(conn, member);
		} catch (SQLException e) {
			DBManager.rollback(conn);
			throw new RuntimeException("DB 처리 오류:"+e.getMessage(), e);
		} finally {
			DBManager.close(conn);
		}
	
	}
	
	@Override
	public void update(Member member) {
		MemberDao mDao = MemberDao.getInstance();
		Connection conn = null;

		try {
			conn = DBManager.getConnection();
			mDao.update(conn,member);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		DBManager.close(conn);
		}
	}
	
	@Override
	public String idCheck(String id) {
		Connection conn = null;
		Member member = null;
		try {
			conn = DBManager.getConnection();
			MemberCheckHelper mch = new MemberCheckHelper();
			member = mch.checkExists(conn, id);
		} catch (SQLException e) {
			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
		} catch (MemberNotFoundException e) {  // 아무 처리도 하지 않음
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn);
		}
		if(member == null) {
			return "not using";
		} else {
			return "using";
		}
	}
}
