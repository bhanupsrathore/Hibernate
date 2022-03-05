package com.hibernate.test;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.met.model.Account;

public class HibernateTest {

	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	/*static{
		
		Configuration configuration = new Configuration();
		Configuration configure = configuration.configure();
		SessionFactory sessionFactory = configure.buildSessionFactory();
		
		
	}*/
	
	private static void saveUsingHibernate(Account acc){
		
		Transaction tx = null;
		
		try(Session session = sessionFactory.openSession()){		//here we get db connection
			
			tx = session.beginTransaction();			// on db connection we are creating transaction			
			
			/*synchronized(session){
				session.save(acc);
			}*/
			
			Serializable value = session.save(acc);				// 10 -> int , wrapper class: Integer
						//it return primary key value which should be type serializable
			
			System.out.println("Value of serializable: " + value);
			
			tx.commit();
			
			
			//creates sql query			session.flush();
			//commit transaction
			
			//tx.commit() = session.flush() + commit;
			
			//session.flush();
			
					
			System.out.println("Account saved sucessfully with id " + acc.getId());
			
		}
		
		
	}
	
	public static void main(String[] args) {
		
		Account acc = new Account();
		acc.setId(12);
		acc.setName("Ravi");
		acc.setBalance(10000);
		
		
		saveUsingHibernate(acc);
		
		System.out.println("...............................................");
		
		//sessionFactory.close();
		
	}
	
}












