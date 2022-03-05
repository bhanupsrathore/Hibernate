package com.hibernate.test;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.met.model.Account;

public class CRUDTest {

	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	private static void saveUsingHibernate(Account acc){
		
		Transaction tx = null;
		
		try(Session session = sessionFactory.openSession()){		//here we get db connection
			
			tx = session.beginTransaction();			// on db connection we are creating transaction			
								
			//acc  -> transient  object
			
			
			session.save(acc);
			
			//acc -> persistence object
			
			tx.commit();
			//12 id would be inserted to database
										
			System.out.println("Account saved sucessfully with id " + acc.getId());
			
			//acc -> persistence object
			
		}
		
		//acc		dettached
		
	}
	
	
	public static void main(String[] args) {
		
		try{
			
			Account acc = new Account();			//transient
			acc.setId(12);
			acc.setName("John");
			acc.setBalance(100000);
			
			saveUsingHibernate(acc);
			
		}finally{
			sessionFactory.close();
		}
		
	}
	
}
