package com.hibernate.test;

import java.sql.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.met.model.CardPayment;
import com.met.model.ChequePayment;
import com.met.model.Payment;

public class InheritanceTest {

	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	public static void main(String[] args) {
		
		Payment payment = new Payment();
		payment.setAmount(10000);
		payment.setPayDate(new Date(System.currentTimeMillis()));
		
		
		ChequePayment chequePayment = new ChequePayment();
		chequePayment.setAmount(15000);
		chequePayment.setPayDate(new Date(System.currentTimeMillis()));
		chequePayment.setChequeNo("45678");
		chequePayment.setBankName("ICICI");
		
		
		CardPayment cardPayment = new CardPayment();
		cardPayment.setAmount(20000);
		
		cardPayment.setPayDate(new Date(System.currentTimeMillis()));
		cardPayment.setCardNo("1234 XXXXX");
		cardPayment.setCardType("AMEX");
		
		Transaction tx = null;
		
		try(Session session = sessionFactory.openSession()){
			
			tx = session.beginTransaction();
			
			session.save(payment);
			session.save(chequePayment);
			session.save(cardPayment);
			
			tx.commit();
			
		}catch (HibernateException e) {
			e.printStackTrace();
			if(tx != null) tx.rollback();
		}
		
		sessionFactory.close();
		
		
	}
	
}
