package com.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.met.model.Account;

public class HibernateTest {

	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	static{
		
		Configuration configuration = new Configuration();
		Configuration configure = configuration.configure();
		SessionFactory sessionFactory = configure.buildSessionFactory();
		
		
	}
	
	private static void saveUsingHibernate(Account acc){
		
		Transaction tx = null;
		
		try(Session session = sessionFactory.openSession()){		//here we get db connection
			
			tx = session.beginTransaction();			// on db connection we are creating transaction			
			
			
			session.save(acc);
			
			tx.commit();
			
			System.out.println("Account saved sucessfully with id " + acc.getId());
			
		}
	}
	
	public static void main(String[] args) {
		
		Account acc = new Account();
		acc.setId(9);
		acc.setName("Pankaj");
		acc.setBalance(90000);
		
		
		saveUsingHibernate(acc);
		
		System.out.println("...............................................");
		
		sessionFactory.close();
		
	}
	
}












