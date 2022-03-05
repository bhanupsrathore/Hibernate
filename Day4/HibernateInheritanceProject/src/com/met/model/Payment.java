package com.met.model;

import java.sql.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
//1 st way of inheritance
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="PaymentType")

@DiscriminatorValue(value="CASH")



//2nd  way of inheritance
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)

//3rd  way of inheritance
//@Inheritance(strategy=InheritanceType.JOINED)

public class Payment {

	@Id
	@GeneratedValue(generator="paymentGenerator")
	private int pay_id;
	
	private double amount;
	private Date payDate;			//instead of using java.util.Date     Temporal annotation
	
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public int getPay_id() {
		return pay_id;
	}
						
	
	
	
}
