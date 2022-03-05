package com.hibernate.test;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.met.model.Address;
import com.met.model.Employee;

public class OneToManyTest {

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
		
		
		List<Address> listAddress = new LinkedList<>();
		listAddress.add(address1);
		listAddress.add(address2);
		
		
		employee.setListAddress(listAddress);
		
		
		
		
		Transaction tx = null;
		
		try(Session session = sessionFactory.openSession()){	
			tx = session.beginTransaction();
			
			session.save(employee);
			
			tx.commit();
			
		}catch (HibernateException e) {
			e.printStackTrace();
			if(tx != null) tx.rollback();
		}
	
		
		sessionFactory.close();
	}
}
