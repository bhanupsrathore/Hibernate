package com.met.hql.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.met.model.Account;

public class HQLTest {

	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	public static void main(String[] args) {
		
		try(Session session = sessionFactory.openSession()){
			
			
			System.out.println("Get All records from Account_tbl");
			
			
			Query<Account> createQuery = session.createQuery("from com.met.model.Account order by id asc", Account.class);
			//System.out.println(createQuery.list());
			
			List<Account> list = createQuery.list();
			/*for(Account acc : list){
				System.out.println(acc);
			}
			*/
			
			/*Iterator<Account> iterator = list.iterator();
			while(iterator.hasNext()){
				System.out.println(iterator.next());
				
			}*/
			
			list.forEach(x -> System.out.println(x));
			
			Query<Account> createQuery2 = session.createQuery("from com.met.model.Account ", Account.class);
			createQuery2 = createQuery2.setMaxResults(2);
			List<Account> list2 = createQuery2.list();
			
			for(Account acc : list2){
				System.out.println(acc);
			}
			
			
			System.out.println();
			System.out.println("Filter condition using where clause");
			System.out.println();
			
			Query<Account> createQuery3 = session.createQuery("from com.met.model.Account where city='Delhi'", Account.class);
			//createQuery3.settim
			//createQuery3.setMaxResults(1);
			Account uniqueResult = createQuery3.getSingleResult();
			System.out.println(uniqueResult);
			
			System.out.println();
			System.out.println("Aggregation data");
			System.out.println();
			
			Query<Double> createQuery4 = session.createQuery("select sum(balance) from com.met.model.Account", Double.class);
			Double aggSum = createQuery4.uniqueResult();
			
			System.out.println(aggSum);
			
			
			
		}
		
		
	}
	
	
}
