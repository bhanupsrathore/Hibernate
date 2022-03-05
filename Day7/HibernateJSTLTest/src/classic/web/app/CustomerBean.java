package classic.web.app;

import java.util.*;
import java.sql.*;
import javax.sql.*;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import classic.web.model.Customer;
import classic.web.model.Order;
import classic.web.util.HibernateUtil;

import javax.naming.*;

public class CustomerBean implements java.io.Serializable {

	private String id;
	private String password;

	public final String getId() { return id; }
	public final void setId(String value) { id = value; }

	public final String getPassword() { return password; }
	public final void setPassword(String value) { password = value; }
	
	@SuppressWarnings("deprecation")
	public boolean authenticate() throws SQLException, NamingException {
		/*Context naming = new InitialContext();
		DataSource pool = (DataSource)naming.lookup("jdbc/SalesDB");
		try(Connection con = pool.getConnection()){
			PreparedStatement pstmt = con.prepareStatement("select count(cust_id) from customers where cust_id=? and pwd=?");
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			rs.close();
			pstmt.close();
			if(count == 1)
				return true;
			id = password = null;
			return false;
		}*/
		
		try(Session session = HibernateUtil.getSessionfactory().openSession()){
			
			Criteria createCriteria = session.createCriteria(Customer.class);
			createCriteria.add(Restrictions.eq("userId", id));
			createCriteria.add(Restrictions.eq("pwd", password));
			createCriteria.setProjection(Projections.count("userId"));
			
			long count = (long) createCriteria.uniqueResult();
			
			if(count == 1){
				return true;
			}
			id = password = null;
			return false;
			
		}
		
	}

	public List<InvoiceEntry> getInvoice() throws SQLException, NamingException {
		List<InvoiceEntry> invoice = new ArrayList<>();
		/*Context naming = new InitialContext();
		DataSource pool = (DataSource)naming.lookup("jdbc/SalesDB");
		try(Connection con = pool.getConnection()){
			PreparedStatement pstmt = con.prepareStatement("select pno, qty, ord_date from orders where cust_id=?");
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				invoice.add(new InvoiceEntry(rs));
			rs.close();
			pstmt.close();
			return invoice;
		}*/
		
		//getOrdersByCustomerId
		
		try(Session session = HibernateUtil.getSessionfactory().openSession()){
			
			Query<Order> createNamedQuery = session.createNamedQuery("getOrdersByCustomerId", Order.class);
			createNamedQuery.setParameter("customerId", id);
			
			List<Order> list = createNamedQuery.list();
			
			for(Order ord : list){
				
				invoice.add(new InvoiceEntry(ord));
				
			}
			
		}
		
		
		
		return invoice;
	}

	public static class InvoiceEntry {

		private int productNo;
		private int quantity;
		private java.sql.Date date;

		/*InvoiceEntry(ResultSet rs) throws SQLException {
			productNo = rs.getInt("pno");
			quantity = rs.getInt("qty");
			date = rs.getDate("ord_date");
		}*/
		
		
		public InvoiceEntry(Order order) {
			productNo = order.getPno();
			quantity = order.getQty();
			date = order.getOrderDate();
		}

		public final int getProductNo() { return productNo; }

		public final int getQuantity() { return quantity; }

		public final java.sql.Date getDate() { return date; }

	}

}

