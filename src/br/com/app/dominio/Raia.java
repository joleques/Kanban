package br.com.app.dominio;

public abstract class Raia {
	
	private Raia proxima;
	
		
	public void setProxima(Raia proxima) {
		this.proxima = proxima;
	}

	public Raia() {
		
	}

	public Raia proxima() {
		return proxima;
	}
	
	public abstract boolean condicaoDaRaiAtendida(Time time);
	
	

}
