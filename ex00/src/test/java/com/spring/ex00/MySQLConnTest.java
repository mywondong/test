package com.spring.ex00;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MySQLConnTest {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/test";
	private static final String USER = "root";
	private static final String PW = "1234";
	
	@Test
	public void testConn() throws Exception {
		
		Class.forName(DRIVER); //드라이버 로딩: DriverManager에 등록
		
		try {
			
			Connection con = DriverManager.getConnection(URL, USER, PW);
			System.out.println(con);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*여기는 테스트------ ㄹㄹ*/
}
