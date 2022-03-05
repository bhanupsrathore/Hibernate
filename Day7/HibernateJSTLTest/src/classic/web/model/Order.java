package classic.web.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@NamedQueries({
	
	
	@NamedQuery(name="getOrdersByCustomerId", query="from classic.web.model.Order where custId=:customerId")
	
})

@Entity
@Table(name="orders")
public class Order {

	@Id
	@Column(name="ord_no")
	private int orderNo;
	
	@Column(name="ord_date")
	private Date orderDate;
	
	@Column(name="cust_id")
	private String custId;
	private int pno;
	private int qty;
	
		
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
	
}
