package member.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import member.model.Fav;
import member.model.Order;
import util.DBManager;

public class FavDao {
	private static FavDao instance = new FavDao();
	public static FavDao getInstance() {
		return instance;
	}
	
	public List<Order> select(Connection conn,String id,String page) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Order order = null;
		List<Order> favlist = new ArrayList<>();
		int page_ = Integer.parseInt(page);
		
		try {
			String query = "SELECT *\r\n" + 
					"FROM\r\n" + 
					"(\r\n" + 
					"  SELECT \r\n" + 
					"      @rownum:=@rownum+1  rnum, \r\n" + 
					"      table_fav.*,table_md.img_main,table_md.md_name,table_md.md_regdate,table_md.md_price\r\n" + 
					"  FROM \r\n" + 
					"      table_fav,table_md,\r\n" + 
					"      (SELECT @ROWNUM := 0) R\r\n" + 
					"  WHERE \r\n" + 
					"      1=1 and table_fav.md_code = table_md.md_code and user_id= ? \r\n" + 
					") list\r\n" + 
					"WHERE rnum >= ? AND rnum <= ?; ";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(2, 1+(page_-1)*5);// 등차수열 1 + (page_ - 1) * 5
			pstmt.setInt(3, page_*5);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String mdpic = rs.getString("img_main");
				String mdname = rs.getString("md_name");
				Date mdorderdate = rs.getDate("md_regdate");
				String mdprice = rs.getString("md_price");
				int mdcode = rs.getInt("md_code");
				
				order = new Order(
						mdpic,mdname,mdorderdate,mdprice,mdcode
						);
				favlist.add(order);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(pstmt);
			DBManager.close(rs);
			DBManager.close(conn);
		}
		
		
		return favlist;
	}
	
	public int count(Connection conn,String id) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT count(*)\r\n"
					+ "FROM\r\n"
					+ "(\r\n"
					+ "  SELECT \r\n"
					+ "      @rownum:=@rownum+1  rnum, \r\n"
					+ "      jwagajang.table_fav.* \r\n"
					+ "  FROM \r\n"
					+ "      jwagajang.table_fav, \r\n"
					+ "      (SELECT @ROWNUM := 0) R\r\n"
					+ "  WHERE \r\n"
					+ "      1=1\r\n"
					+ ") list\r\n"
					+ "WHERE user_id = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBManager.close(pstmt);
			DBManager.close(rs);
			DBManager.close(conn);
		}
		
		return count;
	}
	
	public void delete(Connection conn,String id,int mdcode) {
		PreparedStatement pstmt = null;
		try {
			String query = "DELETE FROM `jwagajang`.`table_fav` WHERE (`md_code` = ?  and `user_id` = ?)";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mdcode);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(pstmt);
			DBManager.close(conn);
		}
	}
	
	public void insert(Connection conn,Fav fav) {
		PreparedStatement pstmt = null;
		try {
			String query = "insert into jwagajang.table_fav values (?,?)";
			pstmt = conn.prepareStatement(query);
			int mdcode = fav.getMdcode();
			String id = fav.getUserid();
			pstmt.setInt(1, mdcode);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn);
			DBManager.close(pstmt);
		}
	}
	
	public Integer checkFavDup(Connection conn,String user_id,int md_code) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Order order = new Order();
		int mdcode = 0;
		try {
			String query = "select * from table_fav where user_id = ? and md_code = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, md_code);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			mdcode = rs.getInt("md_code");
			order.setMdcode(mdcode);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn);
			DBManager.close(pstmt);
			DBManager.close(rs);
		}
		return order.getMdcode();
	}
}
