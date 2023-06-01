package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import member.dao.MemberDao;
import member.model.Member;



public class MemberCheckHelper {
	public Member checkExists(Connection conn, String uid)
			throws SQLException, MemberNotFoundException {
		MemberDao memberDao = MemberDao.getInstance();
		Member member = memberDao.selectById(conn, uid);
		if (member == null) {
			throw new MemberNotFoundException(
					"없는 사용자 입니다. : " + uid);
		}
		return member;
	}
}
