package notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;

import notice.dto.NoticeVO;
import util.DBManager;

public class NoticeDAO { // data access object. db랑 웹사이트에서 쓰는 내용을 연결시켜줌
	private NoticeDAO() { }

	private static NoticeDAO instance = new NoticeDAO(); // 싱글톤

	public static NoticeDAO getInstance() {
		return instance;
	}
	
	public List<NoticeVO> selectAllBoards() { // 게시글 목록 출력, List안에 NoticeVO를 저장하겠다고 약속 . <>안에는 특정한 값 넣을 수 있음
		String sql = "select * from table_notice order by notice_code desc";
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		try (Connection conn = DBManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				NoticeVO bVo = getBoardVOFromResultSet(rs);
				list.add(bVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private NoticeVO getBoardVOFromResultSet(ResultSet rs) throws SQLException { // 상세내용 출력
		NoticeVO bVo = new NoticeVO();
		bVo.setNotice_code(rs.getInt("notice_code"));
		bVo.setNotice_label(rs.getString("notice_label"));
		bVo.setNotice_title(rs.getString("notice_title"));
		bVo.setNotice_regdate(rs.getTimestamp("notice_regdate"));
		bVo.setNotice_editdate(rs.getTimestamp("notice_editdate"));
		bVo.setNotice_content(rs.getString("notice_content"));
		bVo.setNotice_count(rs.getInt("notice_count"));
		return bVo;
	}

	// 게시글 등록
	public void insertBoard(NoticeVO bVo) {
		String sql = "insert into table_notice(notice_label, notice_title, notice_content) " + "values(?, ?, ?)";
		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, bVo.getNotice_label());
			pstmt.setString(2, bVo.getNotice_title());
			pstmt.setString(3, bVo.getNotice_content());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 게시글 조회 횟수 갱신
	public void updateReadCount(int notice_count) { 
		String sql = "update table_notice set notice_count=notice_count+1 where notice_code=?";
		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, notice_count);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 게시판 글 상세 내용 보기 :글번호로 찾아온다. : 실패 null,
	public NoticeVO selectOneBoardByNum(int notice_code) {
		String sql = "select * from table_notice where notice_code = ?";
		NoticeVO bVo = null;
		ResultSet rs = null;
		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, notice_code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bVo = getBoardVOFromResultSet(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs);
		}
		return bVo;
	}

	// 게시글 수정
	public void updateBoard(NoticeVO bVo) {
		String sql = "update table_notice set notice_label=?, notice_title=?, notice_content=? where notice_code=?";
		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, bVo.getNotice_label());
			pstmt.setString(2, bVo.getNotice_title());
			pstmt.setString(3, bVo.getNotice_content());
			pstmt.setInt(4, bVo.getNotice_code());
			pstmt.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 게시글 삭제
	public void deleteBoard(int notice_code) {
		String sql = "delete from table_notice where notice_code=?";
		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, notice_code);
			pstmt.executeUpdate(); /* 변경쿼리 executeUpdate */
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}
	
	public int selectCount() throws SQLException { // 게시글 개수 반환
		ResultSet rs = null;
		int count = 0;
		try (Connection conn = DBManager.getConnection();
				Statement stmt = conn.createStatement()) {
			rs = stmt.executeQuery("select count(*) from table_notice");
			rs.next();
			count = rs.getInt(1);
		} finally {
			DBManager.close(rs);
		}
		return count;
	}
	
	public int searchselectCount(String searchoption, String searchkeyword) throws SQLException { // 게시글 개수 반환
		ResultSet rs = null;
		int count = 0;
		try (Connection conn = DBManager.getConnection();
				Statement stmt = conn.createStatement()) {
			rs = stmt.executeQuery("select count(*) from table_notice where "+searchoption+" like '%"+searchkeyword+"%'");
			rs.next();
			count = rs.getInt(1);
			
		} finally {
			DBManager.close(rs);
		}
		return count;
	}
	
	
	public List<NoticeVO> select(int firstRow, int endRow)
			throws SQLException, NamingException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<NoticeVO> result = null;
		try (Connection conn = DBManager.getConnection();){
				pstmt = conn.prepareStatement("select * from table_notice "
						+ "order by notice_code desc limit ?, ?");
			pstmt.setInt(1, firstRow - 1); // 데이터베이스에서는 0부터 시작이라서 -1 입력
			pstmt.setInt(2, endRow - firstRow + 1);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				result = Collections.emptyList();
			}
			else {
				List<NoticeVO> boardList = new ArrayList<NoticeVO>();
				do {
					NoticeVO board = makeBoardFromResultSet(rs);	// false
					boardList.add(board);
				} while (rs.next());
				result = boardList;
			}
		} finally {
			DBManager.close(rs);
			DBManager.close(pstmt);
		}
		return result;
	}
	
	public NoticeVO makeBoardFromResultSet(ResultSet rs)
			throws SQLException, NamingException {
		NoticeVO bVo = new NoticeVO();
		bVo.setNotice_code(rs.getInt("notice_code"));
		bVo.setNotice_label(rs.getString("notice_label"));
		bVo.setNotice_title(rs.getString("notice_title"));
		bVo.setNotice_regdate(rs.getTimestamp("notice_regdate"));
		bVo.setNotice_editdate(rs.getTimestamp("notice_editdate"));
		bVo.setNotice_count(rs.getInt("notice_count"));
		bVo.setNotice_content(rs.getString("notice_content"));
		return bVo;
	}
	public List<NoticeVO> search(String searchoption, String searchkeyword, int firstRow, int endRow) {
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from table_notice where "+searchoption+" like '%"+searchkeyword+"%' order by notice_code desc limit ?, ? ";
		try (Connection conn = DBManager.getConnection();){
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, firstRow -1); //물음표에 해당되는 코드, 페이지의 시작열 순서
			pstmt.setInt(2, endRow - firstRow +1); //물음표에 해당되는 코드, 게시글의 숫자 
			rs = pstmt.executeQuery();

			
			while(rs.next()) {
				int notice_code = rs.getInt("notice_code");
				String notice_label = rs.getString("notice_label");
				String notice_title = rs.getString("notice_title");
				Timestamp notice_regdate = rs.getTimestamp("notice_regdate");
				int notice_count = rs.getInt("notice_count");
				NoticeVO nVo = new NoticeVO(notice_code, notice_label, notice_title, notice_regdate, notice_count );
				list.add(nVo);

			}
			DBManager.close(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs);
			DBManager.close(pstmt);
			
		}
		
		return list;
	}
	
}