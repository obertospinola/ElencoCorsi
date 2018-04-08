package it.polito.tdp.corsi.db;

import java.util.*;
import java.sql.*;
import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Statistiche;

public class CorsoDAO {
	
	
	public List<Corso> listAll() {
		String sql = "SELECT codins, crediti, nome, pd " +
					"FROM corso";
		List<Corso> result = new ArrayList<Corso>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while(res.next()) {
				String codins = res.getString("codins");
				int crediti = res.getInt("crediti");
				String nome = res.getString("nome");
				int pd = res.getInt("pd");
				Corso c = new Corso(codins, crediti, nome, pd);
				result.add(c);
				System.out.println(codins+" "+crediti+" "+nome+" "+pd);
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Corso> listByPd(int pd) {
		String sql = "SELECT codins, crediti, nome, pd " +
				"FROM corso WHERE pd = ?";
		List<Corso> result = new ArrayList<Corso>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, pd);
			ResultSet res = st.executeQuery();
			while(res.next()) {
				Corso c = new Corso(res.getString("codins"), res.getInt("crediti"), res.getString("nome"), res.getInt("pd"));
				result.add(c);
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Statistiche getStatisticheByCodins(String codIns) {
		String sql = "SELECT CDS, COUNT(CDS) as count "+"FROM studente AS s, iscrizione AS i "+"WHERE s.matricola = i.matricola AND i.codins = ?  AND CDS <> \"\" "+"GROUP BY CDS";
		Statistiche stat = new Statistiche();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codIns);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				stat.getMappaCDS().put(res.getString("CDS"), res.getInt("count"));
			}
			conn.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return stat;
	}

	

}
