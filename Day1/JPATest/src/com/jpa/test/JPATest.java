package com.jpa.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.met.model.Account;

public class JPATest {

	private static EntityManagerFactory entityManagerFactory = 
										Persistence.createEntityManagerFactory("jpaTest");
	
	
	/*private static EntityManagerFactory entityManagerFactory1 = 
			Persistence.createEntityManagerFactory("jpaTest1");*/
	
	private static void saveUsingJPA(Account account){
		
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		
		try{
			entityManager = entityManagerFactory.createEntityManager();			//we will have connection to db
			
			System.out.println(entityManager.getClass());
			
			entityTransaction = entityManager.getTransaction();
			
			System.out.println(entityTransaction.getClass());
			
			entityTransaction.begin();
			
			entityManager.persist(account);
			
			
			entityTransaction.commit();
		}finally{
			if(entityManager != null)
				entityManager.close();
		}
		
	}
	
	
	public static void main(String[] args) {
		
		Account acc = new Account();
		acc.setId(4);
		acc.setName("Sham");
		acc.setBalance(80000);
		
		saveUsingJPA(acc);
		
	}
	
}
