package com.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.met.model.Address;
import com.met.model.Employee;

public class ManyToOneUnidirectionalTest {

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
		
		address1.setEmployee(employee);
		address2.setEmployee(employee);

		Transaction tx = null;
		
		try(Session session = sessionFactory.openSession()){
			
			tx = session.beginTransaction();
			
			session.save(address1);
			session.save(address2);
			
			
			tx.commit();
			
		}
		
	
		
		sessionFactory.close();
	}
}
