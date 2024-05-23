package xyz.itwill.realization;

import java.util.Iterator;

public class JdbcApp {
	public static void main(String[] args) {
//		JdbcMysql mysql = new JdbcMysql();
//		
//		mysql.insert();
//		mysql.update();
//		mysql.delete();
//		mysql.select();
		
//		JdbcOracle oracle = new JdbcOracle();
//		
//		oracle.add();
//		oracle.modify();
//		oracle.remove();
//		oracle.search();
		
//		Jdbc jdbc = new JdbcMysql();
		Jdbc jdbc = new JdbcOracle();
		jdbc.insert();
		jdbc.update();
		jdbc.delete();
		jdbc.select();
		
		
		
		
	}

}
