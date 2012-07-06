package br.com.app.dominio;

public class Analise extends Raia {

	@Override
	public boolean condicaoDaRaiAtendida(Time time) {
		return time.estoriaAlocadaPossuiTarefas();
	}

}
