package com.met.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

@Entity(name="Account_Tbl")

/*@Entity
@Table(name="Account_Tbl")*/
public class Account implements Serializable{

	@Id
	@Column(name="acc_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(length=30, nullable=false)
	@Size(min=3, message="Name should be minimum 3 characters ")
	private String name;
	
	private double balance;
	
	@Column(length=30)
	private String city;
	
	@Type(type="yes_no")		//true_false
	private boolean isActive;
	
	//getters and setters
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", balance=" + balance + ", city=" + city + ", isActive="
				+ isActive + "]";
	}
	
	
}
