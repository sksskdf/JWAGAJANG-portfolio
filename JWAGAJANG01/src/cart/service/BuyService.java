package cart.service;

import java.sql.Connection;
import java.sql.SQLException;

import cart.dao.cartDao;
import member.model.Order;
import util.DBManager;

public class BuyService {
	private static BuyService instance = new BuyService();
	public static BuyService getInstance() {
		return instance;
	}
	private BuyService() {}
	
	public void buy(int md_code,String md_id,Order order) {
		cartDao cDao = cartDao.getInstance();
		Connection conn = null;
		
		conn = DBManager.getConnection();
		cDao.buy(conn,md_code,md_id,order);

	}
	
	public String get_mdname(int md_code) {
		cartDao cDao = cartDao.getInstance();
		Connection conn = null;
		
		conn = DBManager.getConnection();
		String mdname = cDao.get_mdname(conn,md_code);
		
		return mdname;
	}
	public Order get_orderInfo(String id) {
		cartDao cDao = cartDao.getInstance();
		Connection conn = null;
		
		conn = DBManager.getConnection();
		Order orderinfo = cDao.get_orderInfo(conn,id);
		return orderinfo;
	}
}
