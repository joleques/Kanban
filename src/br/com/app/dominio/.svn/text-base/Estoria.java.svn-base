package br.com.app.dominio;

import java.util.ArrayList;
import java.util.List;

public class Estoria {

	private Raia raia;
	private List<Tarefa> tarefas;
	private boolean situcaoDosTestes;

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
		this.situcaoDosTestes = false;
	}

	public void setRaia(Raia raia) {
		this.raia = raia;
	}

	public boolean condicaoDaRaiaAtendida(Time time) {
		if (raia != null) {
			return raia.condicaoDaRaiAtendida(time);
		}
		return false;
	}

	public Raia proximaRaia() {
		return raia.proxima();
	}

	public Raia getRaia() {
		return raia;
	}

	public boolean temTarefas() {
		if (tarefas != null) {
			return (tarefas.size() > 0);
		}
		return false;
	}

	public List geTarefas() {
		return tarefas;
	}

	public boolean existeAlgumaEstoriaPorFazer() {
		if (this.tarefas != null) {
			for (Tarefa tarefas : this.tarefas) {
				if (!tarefas.getSitucao()) {
					return tarefas.getSitucao();
				}
			}
			return true;
		}
		return false;
	}

	public boolean testesEstaoOK() {
		return this.situcaoDosTestes;
	}

	public void setSitucaoDoTesteOk(boolean situacao) {
		this.situcaoDosTestes = situacao;
	}

}
