package com.hibernate.test;

import java.io.Serializable;

import org.hibernate.HibernateException;
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
			
			
			Serializable save = session.save(acc);				//Hibernate
			
			//session.persist(acc);			JPA
			
			//acc -> persistence object
			
					
			tx.commit();
			//12 id would be inserted to database
										
			System.out.println("Account saved sucessfully with id " + acc.getId());
			
			//acc -> persistence object
			
		}
		
		//acc		dettached
		
	}
	
	
	private static Account getAccount(int id){
		
		Account acc = null;
		
		
		try(Session session = sessionFactory.openSession()){
			
			acc = session.get(Account.class, id);		//persistent object
			
				//session.get(class, ?);  ? is  primary key column value 
											//Serializable
			
			Account acc1 = session.get(Account.class, id);	
			
			if(acc == acc1){
				System.out.println("Accounts are identical");
			}else{
				System.out.println("Accounts are not identical");
			}
			
		}
		
		//acc -> dettached
		
		return acc;
	}
	
	private static Account getAccountNotIdentical(int id){	//id is 1
		
		Account acc = null;
		
		
		try(Session session = sessionFactory.openSession()){
			
			acc = session.get(Account.class, id);		//persistent object
														// select * from account_tbl where acc_id=1
				//session.get(class, ?);  ? is  primary key column value 
											//Serializable
			
			Account acc1 = session.get(Account.class, 4);		//select * from account_tbl where acc_id=4
			
			if(acc == acc1){
				System.out.println("Accounts are identical");
			}else{
				System.out.println("Accounts are not identical");
			}
			
		}
		
		//acc -> dettached
		
		return acc;
	}
	
	private static void updateAccount(int acc_id){
		
		Transaction tx = null;
		
		try(Session session = sessionFactory.openSession()){
			
			tx = session.beginTransaction();
			
			Account account = session.get(Account.class, acc_id);		//select query
			//account.setCity("NewYork");
			
			account.setCity("London");
			
			//session.update(account);									//update query 
			
			
			tx.commit();
			
		}catch (HibernateException e) {
			if(tx != null){
				tx.rollback();
			}
		}
		
	}
	
	
	private static void updateWithDettached(int accountId){
		
		Account acc = null;
		
		//session.evict(acc);
		
		try(Session session = sessionFactory.openSession()){
			
			acc = session.get(Account.class, accountId);			//persistent object 
						//select query is fired from here
			
			
			//send acc object details to UI
			
			//user goes away for 20 mins
			
			
			
		}
		
		//acc dettached
		
		acc.setBalance(100000);
		acc.setCity("Pune");
		acc.setActive(true);
		
		Transaction tx = null;
		
		try(Session session = sessionFactory.openSession()){
			
			tx = session.beginTransaction();
			
			//dettached
			
			
			//Account acc1 = session.get(Account.class, accountId);		//select query 
			
			session.update(acc);		//update would be fired
			
			//persistent state
			
			tx.commit();
		}catch (HibernateException e) {
			e.printStackTrace();
			if(tx != null) tx.rollback();
			
		}
		
		//acc -> dettached
		
		
	}
	
	private static void updateWithTransient(){
		Transaction tx = null;
		
		
		Account acc = new Account();
		acc.setId(20);
		acc.setName("Jack");
		acc.setBalance(80000);
		acc.setCity("Mumbai");
		
		try(Session session = sessionFactory.openSession()){
			
			tx = session.beginTransaction();
			
			
			session.update(acc);
			
			
			tx.commit();
		}
		
		
		//saveOrUpdate
		
	}
	
	
	private static void saveOrUpdateTest(){
		
		//saveOrUpdate behaviour/functionality changes as per identifier generation logic
		
		
		Transaction tx = null;
		
		
		Account acc = new Account();
		acc.setId(1);
		acc.setName("John");
		acc.setBalance(80000);
		acc.setCity("Mumbai");
		
		try(Session session = sessionFactory.openSession()){
			
			tx = session.beginTransaction();
			
			
			session.saveOrUpdate(acc);
			
			
			tx.commit();
		}
		
	}
	
	
	private static void deleteRecord(int id){
		Transaction tx = null;
		
		try(Session session = sessionFactory.openSession()){
			
			tx = session.beginTransaction();
			
			Account account = session.get(Account.class, id);		//id = 3
			
			session.delete(account);
			//flush
			//commit
			
			tx.commit();
			
		}catch (HibernateException e) {
			if(tx != null){
				tx.rollback();
			}
		}
	}
	
	
	private static void evictData(){
		
		try(Session session = sessionFactory.openSession()){
			
			Account account = session.get(Account.class, 1);		
			
			System.out.println("account exits in hibernate session: " + session.contains(account));
			
			
			Account account1 = session.get(Account.class, 2);		
			
			System.out.println("account1 exits in hibernate session: " + session.contains(account1));
			
			//session.evict(account);
			
			//session.detach(account);			//JPA
			
			session.clear();
			
			System.out.println("account exits in hibernate session: " + session.contains(account));
			System.out.println("account1 exits in hibernate session: " + session.contains(account1));
			
		}
		
		
	}
	
	private static void loadData(int id){
		
		try(Session session = sessionFactory.openSession()){
			
			Account acc = session.load(Account.class, id);
			System.out.println("Class Name: " + acc.getClass());
			
			
			System.out.println("Account Id: " + acc.getId());
			
			System.out.println("Getting account details...");
			
			System.out.println(acc.getBalance());
			System.out.println(acc.getName());
			
			
		}
		
	}
	
	
	public static void main(String[] args) {
		
		try{
			
			Account acc = new Account();			//transient
			//acc.setId(1);		//assigned identifier
			
								//generated identifier (Sequence  and identity)
			
			
			//acc.setId(10);
			
			acc.setName("Jin");
			acc.setBalance(70000);
			acc.setCity("Mumbai");
			
			//saveUsingHibernate(acc);
			
			
			/*Account account = getAccount(1);
			System.out.println(account);*/
			
			//getAccountNotIdentical(1);
			
			//updateAccount(1);
			//updateAccount(3);
			
			
			//updateWithDettached(10);
			//updateWithDettached(9);
			
			
			//updateWithTransient();
			
			
			//saveOrUpdateTest();
			
			//deleteRecord(3);
			
			//loadData(8);
			
			evictData();
			
		}finally{
			sessionFactory.close();
		}
		
	}
	
}
