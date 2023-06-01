package member.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import member.model.Order;
import util.DBManager;

public class OrderDao {
	private static OrderDao instance = new OrderDao();
	public static OrderDao getInstance() {
		return instance;
	}
	
	public List<Order> select(Connection conn,String id,String page) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Order order = null;
		List<Order> orderlist = new ArrayList<>();
		int page_ = Integer.parseInt(page);
		
		try {
			String query = "SELECT *\r\n"
					+ "FROM\r\n"
					+ "(\r\n"
					+ "  SELECT \r\n"
					+ "      @rownum:=@rownum+1  rnum, \r\n"
					+ "      jwagajang.table_order.* \r\n"
					+ "  FROM \r\n"
					+ "      jwagajang.table_order, \r\n"
					+ "      (SELECT @ROWNUM := 0) R\r\n"
					+ "  WHERE \r\n"
					+ "      1=1\r\n"
					+ ") list\r\n"
					+ "WHERE rnum >= ? AND rnum <= ? AND mdid = ?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, 1+(page_-1)*5);// 등차수열 1 + (page_ - 1) * 5
			pstmt.setInt(2, page_*5);
			pstmt.setString(3, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String mdpic = rs.getString("mdpic");
				String mdname = rs.getString("mdname");
				Date mdorderdate = rs.getDate("mdorderdate");
				String mdprice = rs.getString("mdprice");
				
				order = new Order(
						mdpic,mdname,mdorderdate,mdprice
						);

				orderlist.add(order);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(pstmt);
			DBManager.close(rs);
			DBManager.close(conn);
		}
		
		
		return orderlist;
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
					+ "      jwagajang.table_order.* \r\n"
					+ "  FROM \r\n"
					+ "      jwagajang.table_order, \r\n"
					+ "      (SELECT @ROWNUM := 0) R\r\n"
					+ "  WHERE \r\n"
					+ "      1=1\r\n"
					+ ") list\r\n"
					+ "WHERE mdid = ?;";
			
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
	
	
}
