package it.polito.tdp.corsi.model;

import java.util.*;
import it.polito.tdp.corsi.db.CorsoDAO;
import it.polito.tdp.corsi.db.StudenteDAO;

public class Model {
	
	List<Corso> corsi;
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	public Model() {
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	}

	public List<Corso> listaCorsiSemestre(int pd) {
		// opzione 1: leggo e filtro tutto il file io
		/*this.corsi = dao.listAll();
		List<Corso> risultato = new ArrayList<Corso>();
		for (Corso c : this.corsi) {
			if (c.getPd() == pd) {
				risultato.add(c);
			}
		}
		return risultato;
		*/
		//opzione 2: faccio lavorare il database	
		List<Corso> risultato2 = corsoDAO.listByPd(pd);
		return risultato2;
	}

	public String getNomeCognomeByMatricola(int matricola) {
		Studente studente = studenteDAO.getStudenteByMatricola(matricola);
		if (studente == null) {
			return "Nessuno studente associato alla matricola inserita";
		}
		return studente.getMatricola()+" "+studente.getNome()+" "+studente.getCognome();
	}

	public String getStatisticheFromCorso() {
		this.corsi = corsoDAO.listAll();
		StringBuilder sb = new StringBuilder();
		for (Corso c : this.corsi) {
			Statistiche stat = corsoDAO.getStatisticheByCodins(c.getCodIns());
			sb.append("codins: "+c.getCodIns()+"\n");
			for (String cds : stat.getMappaCDS().keySet()) {
				sb.append(" - "+cds+" "+stat.getMappaCDS().get(cds)+"\n");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
