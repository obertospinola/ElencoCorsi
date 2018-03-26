package it.polito.tdp.corsi.model;

import java.util.*;
import it.polito.tdp.corsi.db.CorsoDAO;

public class Model {
	
	List<Corso> corsi;
	
	public List<Corso> listaCorsiSemestre(int pd) {
		
		CorsoDAO dao = new CorsoDAO();
		
		// opzione 1: leggo e filtro tutto il file io
		this.corsi = dao.listAll();
		List<Corso> risultato = new ArrayList<Corso>();
		for (Corso c : this.corsi) {
			if (c.getPd() == pd) {
				risultato.add(c);
			}
		}
		return risultato;
		
		//opzione 2: faccio lavorare il database
		//List<Corso> risultato2 = dao.listByPd(pd);
		//return risultato2;
	}

}
