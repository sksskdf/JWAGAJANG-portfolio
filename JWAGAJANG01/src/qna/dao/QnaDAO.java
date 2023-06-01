package qna.dao;

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
import qna.dto.QnaVO;
import util.DBManager;

public class QnaDAO { // data access object. db랑 웹사이트에서 쓰는 내용을 연결시켜줌
	private QnaDAO() { }

	private static QnaDAO instance = new QnaDAO(); // 싱글톤

	public static QnaDAO getInstance() {
		return instance;
	}
	
	public List<QnaVO> selectAllBoards() { // 게시글 목록 출력, List안에 qnaVO를 저장하겠다고 약속 . <>안에는 특정한 값 넣을 수 있음
		String sql = "select * from table_qna order by qna_code desc";
		List<QnaVO> qnalist = new ArrayList<QnaVO>();
		try (Connection conn = DBManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				QnaVO bVo = getBoardVOFromResultSet(rs);
				qnalist.add(bVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qnalist;
	}

	private QnaVO getBoardVOFromResultSet(ResultSet rs) throws SQLException { // 상세내용 출력
		QnaVO qVo = new QnaVO();
		qVo.setQna_code(rs.getInt("qna_code"));
		qVo.setQna_label(rs.getString("qna_label"));
		qVo.setQna_title(rs.getString("qna_title"));
		qVo.setUser_id(rs.getNString("user_id"));
		qVo.setQna_regdate(rs.getTimestamp("qna_regdate"));
		qVo.setQna_editdate(rs.getTimestamp("qna_editdate"));
		qVo.setQna_content(rs.getString("qna_content"));
		qVo.setQna_count(rs.getInt("qna_count"));
		qVo.setQna_reply(rs.getString("qna_reply"));

		return qVo;
	}

	// 게시글 등록
	public void insertBoard(QnaVO qVo,String sessionid) {
		String sql = "insert into table_qna(qna_title, qna_content, user_id) " + "values(?, ?, ?)";
		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, qVo.getQna_title());
			pstmt.setString(2, qVo.getQna_content());
			pstmt.setString(3, sessionid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 게시글 조회 횟수 갱신
	public void updateReadCount(int qna_count) { 
		String sql = "update table_qna set qna_count=qna_count+1 where qna_code=?";
		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, qna_count);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 게시판 글 상세 내용 보기 :글번호로 찾아온다. : 실패 null,
	public QnaVO selectOneBoardByNum(int qna_code) {
		String sql = "select * from table_qna where qna_code = ?";
		QnaVO qVo = null;
		ResultSet rs = null;
		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, qna_code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				qVo = getBoardVOFromResultSet(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs);
		}
		return qVo;
	}

	// 게시글 수정
	public void updateBoard(QnaVO bVo) {
		String sql = "update table_qna set qna_title=?, qna_content=? where qna_code=?";
		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, bVo.getQna_title());
			pstmt.setString(2, bVo.getQna_content());
			pstmt.setInt(3, bVo.getQna_code());
			pstmt.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 게시글 삭제
	public void deleteBoard(int qna_code) {
		String sql = "delete from table_qna where qna_code=?";
		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, qna_code);
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
			rs = stmt.executeQuery("select count(*) from table_qna");
			rs.next();
			count = rs.getInt(1);
		} finally {
			DBManager.close(rs);
		}
		return count;
	}
	
	public int searchselectCount(String searchkeyword) throws SQLException { // 게시글 개수 반환
		ResultSet rs = null;
		int count = 0;
		try (Connection conn = DBManager.getConnection();
				Statement stmt = conn.createStatement()) {
			rs = stmt.executeQuery("select count(*) from table_qna where user_id like '%"+searchkeyword+"%'");
			rs.next();
			count = rs.getInt(1);
			
		} finally {
			DBManager.close(rs);
		}
		return count;
	}
	
	
	public List<QnaVO> select(int firstRow, int endRow)
			throws SQLException, NamingException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<QnaVO> result = null;
		try (Connection conn = DBManager.getConnection();){
				pstmt = conn.prepareStatement("select * from table_qna "
						+ "order by qna_code desc limit ?, ?");
			pstmt.setInt(1, firstRow - 1); // 데이터베이스에서는 0부터 시작이라서 -1 입력
			pstmt.setInt(2, endRow - firstRow + 1);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				result = Collections.emptyList();
			}
			else {
				List<QnaVO> boardList = new ArrayList<QnaVO>();
				do {
					QnaVO board = makeBoardFromResultSet(rs);	// false
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
	
	public QnaVO makeBoardFromResultSet(ResultSet rs)
			throws SQLException, NamingException {
		QnaVO qVo = new QnaVO();
		qVo.setQna_code(rs.getInt("qna_code"));
		qVo.setQna_label(rs.getString("qna_label"));
		qVo.setQna_title(rs.getString("qna_title"));
		qVo.setUser_id(rs.getString("user_id"));
		qVo.setQna_regdate(rs.getTimestamp("qna_regdate"));
		qVo.setQna_editdate(rs.getTimestamp("qna_editdate"));
		qVo.setQna_count(rs.getInt("qna_count"));
		qVo.setQna_content(rs.getString("qna_content"));
		qVo.setQna_content(rs.getString("qna_reply"));
		return qVo;
	}
	public List<QnaVO> search(String searchkeyword, int firstRow, int endRow) {
		List<QnaVO> list = new ArrayList<QnaVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from table_qna where user_id like '%"+searchkeyword+"%' order by qna_code desc limit ?, ? ";
		try (Connection conn = DBManager.getConnection();){
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, firstRow -1); //물음표에 해당되는 코드, 페이지의 시작열 순서
			pstmt.setInt(2, endRow - firstRow +1); //물음표에 해당되는 코드, 게시글의 숫자 
			rs = pstmt.executeQuery();

			while(rs.next()) {
				int qna_code = rs.getInt("qna_code");
				String qna_label = rs.getString("qna_label");
				String qna_title = rs.getString("qna_title");
				String user_id = rs.getNString("user_id");
				Timestamp qna_regdate = rs.getTimestamp("qna_regdate");
				int qna_count = rs.getInt("qna_count");
				QnaVO qVo = new QnaVO(qna_code, qna_label, qna_title, user_id,  qna_regdate, qna_count);
				list.add(qVo);
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
	

	// 리플 등록
	public void updateReply(QnaVO qVo) {
		String sql = "update table_qna set qna_label=?, qna_reply=? where qna_code=?";
		try (Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1,qVo.getQna_label());
			pstmt.setString(2, qVo.getQna_reply());
			pstmt.setInt(3, qVo.getQna_code());
			pstmt.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}