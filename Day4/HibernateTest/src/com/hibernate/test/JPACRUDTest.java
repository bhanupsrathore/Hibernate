package com.hibernate.test;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.met.model.Account;

public class JPACRUDTest {

	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	private static void saveUsingJPA(Account acc){
		
		Transaction tx = null;
		
		try(Session session = sessionFactory.openSession()){		//here we get db connection
			
			tx = session.beginTransaction();			// on db connection we are creating transaction			
			
			//Serializable save = session.save(acc);				//Hibernate
			
			session.persist(acc);									//JPA
			
			tx.commit();
										
			System.out.println("Account saved sucessfully with id " + acc.getId());
			
		}
		
		//acc		dettached
		
	}
	
	private static void mergeAccount(int acc_id){
		
		Transaction tx = null;
		
		try(Session session = sessionFactory.openSession()){
			
			tx = session.beginTransaction();
			
			Account account = session.get(Account.class, acc_id);		//select query
			//account.setCity("NewYork");
			
			account.setCity("London");
			
			session.merge(account);									//update query 
						
			tx.commit();
			
		}catch (HibernateException e) {
			if(tx != null){
				tx.rollback();
			}
		}
		
	}
	
	
	private static void mergeWithDettached(int accountId){
		
		Account acc = null;
		
		//session.evict(acc);
		
		try(Session session = sessionFactory.openSession()){
			
			acc = session.get(Account.class, accountId);			//persistent object 
						//select query is fired from here
			
			
			//send acc object details to UI
			
			//user goes away for 20 mins
			
			
			
		}
		
		//acc dettached
		
		acc.setBalance(800000);			//9
		acc.setCity("Delhi");
		acc.setActive(true);
		
		Transaction tx = null;
		
		try(Session session = sessionFactory.openSession()){
			
			tx = session.beginTransaction();
			
			//dettached
			
			
			//Account acc1 = session.get(Account.class, accountId);		//select query 
			
			System.out.println("Before merging");
			
			session.merge(acc);		//update would be fired
			
			System.out.println("After merging");
			
			//persistent state
			
			tx.commit();
		}catch (HibernateException e) {
			e.printStackTrace();
			if(tx != null) tx.rollback();
			
		}
		
		//acc -> dettached
		
		
	}
	
	private static void removeObject(int id){
		
		Transaction tx = null;
		
		try(Session session = sessionFactory.openSession()){
			
			tx = session.beginTransaction();
			
			Account account = session.get(Account.class, id);		//id = 10
			
			session.remove(account);
			//flush
			//commit
			
			tx.commit();
			
		}catch (HibernateException e) {
			if(tx != null){
				tx.rollback();
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		Account acc = new Account();
		acc.setName("Jane");
		acc.setBalance(90000);
		acc.setCity("Jersey");
		
		
		//saveUsingJPA(acc);
		//mergeAccount(8);
		
		//mergeWithDettached(9);
		
		removeObject(10);
		
	}
	
}
