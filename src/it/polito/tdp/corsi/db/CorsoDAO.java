package it.polito.tdp.corsi.db;

import java.util.*;
import java.sql.*;
import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO {
	
	private final String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?user=root&password=root";
	
	public List<Corso> listAll() {
		
		String sql = "SELECT codins, crediti, nome, pd " +
					"FROM corso";
		
		List<Corso> result = new ArrayList<Corso>();
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while(res.next()) {
				Corso c = new Corso(res.getString("codins"), res.getInt("crediti"), res.getString("nome"), res.getInt("pd"));
				result.add(c);
			}
			conn.close();
		} catch (SQLException e) {
			return null;
		}
		return result;
	}
	
	public List<Corso> listByPd(int pd) {
		return null;
	}

}
