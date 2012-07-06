package br.com.app.dominio;

import br.com.app.infraestrutura.dao.DAO;
import br.com.app.infraestrutura.dao.EstoriaDAO;


public class Espera extends Raia{
	
	private DAO dao;
	
	
	
	public Espera(DAO dao) {
		super();
		this.dao = dao;
	}



	@Override
	public boolean condicaoDaRaiAtendida(Time time) {
		if(((EstoriaDAO) this.dao).buscarEstoriaDoTime(time) == null){
			return true;
		}
		return false;
	}

}
