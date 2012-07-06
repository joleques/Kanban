package br.com.app.dominio;

public class Time {

	private Estoria estoria;

	public void setEstoria(Estoria estoria) {
		this.estoria = estoria;
	}

	public Time() {

	}

	public boolean moverEstoriaParaProximaRaia() {
		boolean resultadoDaMovimentacao = false;
		if (estoria != null) {
			resultadoDaMovimentacao = passarEstoriaParaProximaRaiaSe(estoria.condicaoDaRaiaAtendida(this));
		}
		return resultadoDaMovimentacao;
	}

	private boolean passarEstoriaParaProximaRaiaSe(boolean condicaoDaRaiaAtendida) {
		if (condicaoDaRaiaAtendida) {
			estoria.setRaia(estoria.proximaRaia());
		}
		return condicaoDaRaiaAtendida;
	}

	public Raia getRaia() {
		if (estoria != null) {
			return estoria.getRaia();
		}
		return null;
	}

	public boolean estoriaAlocadaPossuiTarefas() {
		return estoria.temTarefas();
	}

	public boolean existeAlgumaTarefaPorFazer() {
		return estoria.existeAlgumaEstoriaPorFazer();
	}

	public boolean testesDaEstoriaEstaoOk() {
		return estoria.testesEstaoOK();
	}

}
