package br.com.app.dominio;

public class Pronto extends Raia {

	@Override
	public boolean condicaoDaRaiAtendida(Time time) {
		return false;
	}

}
