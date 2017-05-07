package br.com.ebookapp.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class RequestConnection {
	private static Connection connection;
	private static String url = "jdbc:postgresql://ebookapp.postgresql.dbaas.com.br:5432/ebookapp";
	private static String username = "ebookapp";
	private static String password = "rootebook123";
	
	public static synchronized Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, username, password);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return connection;
	}
}
