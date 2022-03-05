package com.hibernate.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.met.model.Address;
import com.met.model.Employee;



public class OneToManyBidirectionTest {

	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	public static void main(String[] args) {
		
		Employee employee = new Employee();
		employee.setName("Ramesh");
		employee.setEmailId("ramesh@met.edu");
		
		
		Address address1 = new Address();
		address1.setCity("Mumbai");
		address1.setCountry("India");
		
		
		Address address2 = new Address();
		address2.setCity("NewYork");
		address2.setCountry("USA");
		
		
		
		
		
		address1.setEmployee(employee);					//address -> EMployee
		address2.setEmployee(employee);

		
		
		
		List<Address> listAddress = new LinkedList<>();
		listAddress.add(address1);
		listAddress.add(address2);
		
		
		employee.setListAddress(listAddress);				//Employee -> Adress
		
		
		Employee employee2 = new Employee();
		employee2.setName("Rahul");
		employee2.setEmailId("Rahul@met.edu");
		
		
		Address address3 = new Address();
		address3.setCity("London");
		address3.setCountry("UK");
		
		Address address4 = new Address();
		address4.setCity("Jaipur");
		address4.setCountry("INDIA");
		
		
		address3.setEmployee(employee2);
		address4.setEmployee(employee2);
		
		
		List<Address> listAddress2 = new ArrayList<>(2);
		listAddress2.add(address3);
		listAddress2.add(address4);
		
		
		employee2.setListAddress(listAddress2);
		
		
		
		Transaction tx = null;
		
		try(Session session = sessionFactory.openSession()){
			
			tx = session.beginTransaction();
			
			session.save(employee);
			session.save(employee2);
			
			tx.commit();
			
		}
		
	
		
		sessionFactory.close();
	}
}
