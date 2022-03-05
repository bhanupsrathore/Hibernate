package com.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.met.model.Account;

public class JDBCTest {

	private static void saveUsingJDBC(Account account){
		
		
		try{
			Class.forName("oracle.jdbc.OracleDriver");		//intellij or eclipse IDE
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println(ex);
		}
		
		
		try(
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
						"system", "Dinesh@123");
				
				
				PreparedStatement pstmt = con.prepareStatement("insert into account(id, name, balance) "
						+ " values(?, ?, ?)");
				
				
				){
			
			pstmt.setInt(1, account.getId());
			pstmt.setString(2, account.getName());
			pstmt.setDouble(3, account.getBalance());
			
			System.out.println(con.getClass());
			
			/*pstmt.setDouble(147, account.getBalance());
			pstmt.setDouble(147, account.getBalance());
			pstmt.setDouble(149, account.getBalance());
			pstmt.setDouble(150, account.getBalance());*/
			
			pstmt.executeUpdate();
			
			
			//somrthing.save(account);			JPA
			
			
			System.out.println("Account saved success.");
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public static void main(String[] args) {
		Account acc = new Account();
		acc.setId(1);
		acc.setName("Ramesh");
		acc.setBalance(50000);
		
		saveUsingJDBC(acc);
	}
	
}



/*
try{
	
	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
			"system", "Dinesh@123");
	
	
	PreparedStatement pstmt = con.prepareStatement("insert into account(id, name, balance) "
			+ " values(?, ?, ?)");
	
	pstmt.setInt(1, account.getId());
	pstmt.setString(2, account.getName());
	pstmt.setDouble(3, account.getBalance());
	
	pstmt.executeUpdate();
	
	System.out.println("Account saved success.");
	
	
}catch (SQLException e) {
	e.printStackTrace();
}finally{
	
	
	if(pstmt!=null) pstmt.close();
	if(con !=null) con.close();
	
	
	
}*/



