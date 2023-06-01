package goods.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import goods.dto.GoodsVO;
import util.DBManager;

public class GoodsDAO {
	
	private static GoodsDAO instance = new GoodsDAO();
	
	public static GoodsDAO getInstance() {
		return instance;
	}
	
	private GoodsDAO() { }
	
	// 상품 분류
	public List<GoodsVO> sortMd(String category_main, String category_sub, String order,
			int firstRow, int endRow, boolean readContnent) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<GoodsVO> mdCate = null;
		
		try {
			conn = DBManager.getConnection();
			
			String sqlAll = "select * from table_md";
			String sqlCate = "select * from table_md";
			sqlCate += " where category_main = ?";
			
			if(category_sub != null && !category_sub.isEmpty()) {
				sqlCate += " and category_sub = ?";
			}
			
			if(order.equals("1")) {
				sqlAll += " order by md_regdate desc";
				sqlCate += " order by md_regdate desc";
			} else if(order.equals("2")) {
				sqlAll += " order by md_ordercnt desc";
				sqlCate += " order by md_ordercnt desc";
			} else if(order.equals("3")) {
				sqlAll += " order by review_count desc";
				sqlCate += " order by review_count desc";
			} else if(order.equals("4")) {
				sqlAll += " order by floor(md_price-(md_price*(md_dc/100))) asc";
				sqlCate += " order by floor(md_price-(md_price*(md_dc/100))) asc";
			} else if(order.equals("5")) {
				sqlAll += " order by floor(md_price-(md_price*(md_dc/100))) desc";
				sqlCate += " order by floor(md_price-(md_price*(md_dc/100))) desc";
			}
			
			sqlAll += " limit ?, ?";
			sqlCate += " limit ?, ?";
			
			if(category_main.equals("All") || category_main.equals("")) {
				pstmt = conn.prepareStatement(sqlAll);
				pstmt.setInt(1, firstRow-1);
				pstmt.setInt(2, endRow-firstRow+1);
			} else {
				pstmt = conn.prepareStatement(sqlCate);
				pstmt.setString(1, category_main);
				
				if(category_sub != null && !category_sub.isEmpty()) {
					pstmt.setString(2, category_sub);
					pstmt.setInt(3, firstRow-1);
					pstmt.setInt(4, endRow-firstRow+1);
				} else {
					pstmt.setInt(2, firstRow-1);
					pstmt.setInt(3, endRow-firstRow+1);
				}
			}
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				mdCate = new ArrayList<GoodsVO>();
				do {
					GoodsVO md = new GoodsVO();
					md.setMd_code(rs.getInt("md_code"));
					md.setMd_name(rs.getString("md_name"));
					md.setMd_price(rs.getInt("md_price"));
					md.setMd_dc(rs.getInt("md_dc"));
					md.setImg_main(rs.getString("img_main"));
					md.setCategory_main(rs.getString("category_main"));
					md.setCategory_sub(rs.getString("category_sub"));
					md.setCategory_main_name(rs.getString("category_main_name"));
					mdCate.add(md);
				} while (rs.next());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
            if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
		}
		return mdCate;
	}
	
	
	// 상품 상세
	public GoodsVO getMd(int md_code) {
		String sql = "select * from table_md where md_code=?";
		GoodsVO md = null;
		ResultSet rs = null;
		
		try (Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql); ) {
			pstmt.setInt(1, md_code);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				md = new GoodsVO();
				md.setMd_code(rs.getInt("md_code"));
				md.setMd_name(rs.getString("md_name"));
				md.setMd_price(rs.getInt("md_price"));
				md.setMd_dc(rs.getInt("md_dc"));
				md.setMd_stock(rs.getInt("md_stock"));
				md.setImg_main(rs.getString("img_main"));
				md.setImg_detail(rs.getString("img_detail"));
				md.setCategory_main(rs.getNString("category_main"));
				md.setCategory_sub(rs.getNString("category_sub"));
				md.setCategory_main_name(rs.getString("category_main_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs);
		}
		return md;
	}
	
	
	// 전체 리뷰 수
	public int reviewCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("select count(*) from table_review");
			rs = pstmt.executeQuery();
			
			if (rs.next())
				x = rs.getInt(1);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
            if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
		}
		return x;
	}
	
	// 리뷰 수
	public int reviewCount(int md_code) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("select count(*) from table_review where md_code = ?");
			pstmt.setInt(1, md_code);
			rs = pstmt.executeQuery();
			
			if (rs.next())
				x = rs.getInt(1);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
            if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
		}
		return x;
	}
	

	// 리뷰
	public List<GoodsVO> getReviewList(int count, int md_code) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<GoodsVO> reviewList = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("select * from table_review where md_code = ?");
			pstmt.setInt(1, md_code);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				reviewList = new ArrayList<GoodsVO>(count);
				do {
					GoodsVO review = new GoodsVO();
					review.setMd_code(rs.getInt("md_code"));
					review.setReview_code(rs.getInt("review_code"));
					review.setUser_id(rs.getString("user_id"));
					review.setUser_name(rs.getString("user_name"));
					review.setReview_rate(rs.getInt("review_rate"));
					review.setReview_content(rs.getString("review_content"));
					review.setReview_regdate(rs.getTimestamp("review_regdate"));
					reviewList.add(review);
				} while (rs.next());
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
            if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
		}
		return reviewList;
	}
	
	// 리뷰 삭제
	public int deleteReview(int review_code) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("delete from table_review where review_code=?");
			pstmt.setInt(1, review_code);
			pstmt.executeUpdate();
			x = 1; 	// 글 삭제 성공
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
            if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
		}
		return x;
	}
	
	// 검색
	public List<GoodsVO> search(String searchkeyword, int firstRow, int endRow) {
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from table_md where md_name like '%"+searchkeyword+"%' order by md_code desc limit ?, ? ";
		try (Connection conn = DBManager.getConnection();){
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, firstRow -1); //물음표에 해당되는 코드, 페이지의 시작열 순서
			pstmt.setInt(2, endRow - firstRow +1); //물음표에 해당되는 코드, 게시글의 숫자 
			rs = pstmt.executeQuery();

			while(rs.next()) {
				int md_code = rs.getInt("md_code");
				String md_name = rs.getString("md_name");
				int md_price = rs.getInt("md_price");
				int md_dc = rs.getInt("md_dc");
				String img_main = rs.getString("img_main");
				String category_main = rs.getString("category_main");
				String category_sub = rs.getString("category_sub");
				String category_main_name = rs.getString("category_main_name");
				GoodsVO gVo = new GoodsVO(md_code, md_name, md_price, md_dc, img_main, category_main, category_sub, category_main_name);
				list.add(gVo);
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

	public int getSearchCount(String searchkeyword) {
		ResultSet rs = null;
	    int count = 0;
	    String sql = "select count(*) from table_md where md_name like '%"+searchkeyword+"%'";
	    try (Connection conn = DBManager.getConnection(); 
	    		Statement stmt = conn.createStatement();){
			
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
			count = rs.getInt(1);
			}
	    } catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs);	
		}		
		return count;
	}

	// 상품 갯수 (페이징에 필요)
	public int selectCount(String category_main, String category_sub) throws SQLException {
		ResultSet rs = null;
	    int count = 0;
	      
	    String sql = "select count(*) from table_md";
	    if (!category_main.equals("All") && !category_main.equals("")) {
	    	sql += " where category_main='" + category_main + "'";
	    } 
		if(category_sub != null && !category_sub.isEmpty()) {
			sql += " and category_sub='" + category_sub + "'";
		}

		try (Connection conn = DBManager.getConnection();
			Statement stmt = conn.createStatement();) {
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					count = rs.getInt(1); 
				}
			} finally {
				DBManager.close(rs);
			}
		return count;
	}

	// 리뷰 카운트 
	public void updateReviewCount(int md_code, int increment) throws SQLException {
	    String sql = "update table_md set review_count = review_count + " + increment + " where md_code = ?";

		try (Connection conn = DBManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
				pstmt.setInt(1, md_code);
				pstmt.executeUpdate();
		}
	}

}
