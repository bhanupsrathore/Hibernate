package com.met.criteria.test;

import java.util.List;

import javax.persistence.FetchType;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.met.model.Account;
import com.met.model.Employee;

public class CriteriaTest {

	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		try(Session session = sessionFactory.openSession()){
	
			/*System.out.println();
			System.out.println("Get All records from Account_tbl");
			System.out.println();
			
			Criteria createCriteria = session.createCriteria(Account.class);
			List<Account> list = createCriteria.list();
			list.forEach(x -> System.out.println(x));
			
			
			System.out.println();
			System.out.println("Limit records from Account_tbl");
			System.out.println();
			
			
			Criteria createCriteria2 = session.createCriteria(Account.class);
			createCriteria2.setMaxResults(2);
			//createCriteria2.
			List list2 = createCriteria2.list();
			list2.forEach(x -> System.out.println(x));
			
			
			System.out.println();
			System.out.println("where clause for Account_tbl");
			System.out.println();
			
			Criteria createCriteria3 = session.createCriteria(Account.class);
			createCriteria3.add(Restrictions.eq("city", "Mumbai"));
			List<Account> list3 = createCriteria3.list();
			list3.forEach(x -> System.out.println(x));
			
			
			System.out.println();
			System.out.println("Aggregation data");
			System.out.println();
			
			Criteria createCriteria4 = session.createCriteria(Account.class);
			createCriteria4.setProjection(Projections.sum("balance"));
			System.out.println(createCriteria4.uniqueResult());*/
			
			/*Criteria createCriteria5 = session.createCriteria(Employee.class);
			List<Employee> list4 = createCriteria5.list();
			
			for(Employee emp : list4){
			
				System.out.println("Looping through employees");
				System.out.println(emp.getListAddress());		//lazy loading
				
				System.out.println(emp);
				
				System.out.println();
			}*/
			
			
			Criteria createCriteria5 = session.createCriteria(Employee.class);
			createCriteria5.setFetchMode("listAddress", FetchMode.EAGER);
			List<Employee> list4 = createCriteria5.list();
			
			for(Employee emp : list4){
			
				System.out.println("Looping through employees");
				System.out.println(emp.getListAddress());		//lazy loading
				
				System.out.println(emp);
				
				System.out.println();
			}
			
		
		}
	}
			
}
