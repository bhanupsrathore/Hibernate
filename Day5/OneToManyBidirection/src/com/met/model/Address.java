package com.met.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Address {										//Source

	@Id
	@GeneratedValue(generator="onetomanygeneratorAddress")
	private int add_id;
	@Column(length=15)
	private String city;
	@Column(length=15)
	private String country;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="fk_emp_id")				//relationship owner			
	private Employee employee;								//Target
	
	
	
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public int getAdd_id() {
		return add_id;
	}
	public void setAdd_id(int add_id) {
		this.add_id = add_id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
}
