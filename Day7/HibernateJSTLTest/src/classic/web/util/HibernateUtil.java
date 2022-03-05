package classic.web.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory = null;
	
	
	//static design pattern
	public static SessionFactory getSessionfactory(){
		
		if(sessionFactory == null){
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		
		
		return sessionFactory;
	}
	
}
