package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import member.dao.OrderDao;
import member.model.Order;
import util.DBManager;

public class OrderService {
	private static OrderService instance = new OrderService();
	public static OrderService getInstance() {
		return instance;
	}
	private OrderService() {
	}
	
	public List<Order> getOrderList(String id,String page) {
		OrderDao oDao = OrderDao.getInstance();
		Connection conn = null;
		
		conn = DBManager.getConnection();
		
		
		return oDao.select(conn,id,page);
		
	}
	
	public int getCount(String id) {
		OrderDao oDao = OrderDao.getInstance();
		Connection conn = null;
		
		conn = DBManager.getConnection();
		
		
		return oDao.count(conn, id);
	}
}
