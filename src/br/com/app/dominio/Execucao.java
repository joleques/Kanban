package br.com.app.dominio;

import java.util.ArrayList;

public class Execucao extends Raia {

	public Execucao() {
		
	}

	@Override
	public boolean condicaoDaRaiAtendida(Time time) {
		return time.existeAlgumaTarefaPorFazer();
	}

}
