package it.polito.tdp.corsi.db;

import java.sql.*;

public class ConnectDB {
	
	private static final String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?user=root&password=root";
	private static Connection conn;
	
	public static Connection getConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection(jdbcURL);

			}
		} catch (SQLException e) {
			System.err.println("Errore di connesione al DB");
			throw new RuntimeException(e);
		}
		return conn;
	}

}
