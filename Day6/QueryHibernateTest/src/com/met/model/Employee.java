package com.met.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Employee {									//Source Entity

	@Id
	@GeneratedValue(generator="onetomanygenerator")
	private int emp_id;
	
	@Column(length=15)
	private String name;
	
	@Column(length=20)
	private String emailId;
	
	@OneToMany(cascade=CascadeType.ALL)		//, fetch=FetchType.EAGER
	@JoinColumn(name="fk_emp_id")
	private List<Address> listAddress;						//target Entity
	
	public List<Address> getListAddress() {
		return listAddress;
	}
	public void setListAddress(List<Address> listAddress) {
		this.listAddress = listAddress;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@Override
	/*public String toString() {
		return "Employee [emp_id=" + emp_id + ", name=" + name + ", emailId=" + emailId + ", listAddress=" + listAddress
				+ "]";
	}*/
	
	public String toString() {
		return "Employee [emp_id=" + emp_id + ", name=" + name + ", emailId=" + emailId + "]";
	}
	
	
	
}
