package br.com.teste.dominio;


import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.app.dominio.Analise;
import br.com.app.dominio.Espera;
import br.com.app.dominio.Estoria;
import br.com.app.dominio.Execucao;
import br.com.app.dominio.Pronto;
import br.com.app.dominio.Raia;
import br.com.app.dominio.Tarefa;
import br.com.app.dominio.Teste;
import br.com.app.dominio.Time;
import br.com.app.infraestrutura.dao.EstoriaDAO;


public class TimeTeste {
	
	Time time;
	Raia atual;
	Raia proxima; 

	@Before
	public void setUp() throws Exception {
		
	}
	
	private void SetUpDadosIniciais(Estoria estoria, Raia atual, Raia proxima){
		time = new Time();
		this.atual = atual;
		this.proxima = proxima;
		this.atual.setProxima(this.proxima);
		estoria.setRaia(this.atual);
		time.setEstoria(estoria);
		
	}

	@After
	public void tearDown() throws Exception {
		time = null;
	}
	
	@Test
	public void moverEstoriaDeEsperaParaAnaliseComSucesso(){
		EstoriaDAO estoriaDAOMocada = Mockito.mock(EstoriaDAO.class);
		Mockito.when(estoriaDAOMocada.buscarEstoriaDoTime(Mockito.any(Time.class))).thenReturn(null);
		
		SetUpDadosIniciais(new Estoria(), new Espera(estoriaDAOMocada), new Analise());
		Assert.assertEquals(time.getRaia(), atual);
		Assert.assertTrue(time.moverEstoriaParaProximaRaia());
		Assert.assertEquals(time.getRaia(), proxima);		
	}

	@Test
	public void moverEstoriaDeAnaliseParaExecucaoComSucesso(){		
		SetUpDadosIniciais(gerarEstoriaComTarefa(), new Analise(), new Execucao());
		Assert.assertEquals(time.getRaia(), atual);
		Assert.assertTrue(time.moverEstoriaParaProximaRaia());
		Assert.assertEquals(time.getRaia(), proxima);		
	}
	
	@Test
	public void moverEstoriaDeExecucaoParaTesteComSucesso(){		
		SetUpDadosIniciais(gerarEstoriaComTarefasComSituacaoProntaTrue(false), new Execucao(), new Teste());
		Assert.assertEquals(time.getRaia(), atual);
		Assert.assertTrue(time.moverEstoriaParaProximaRaia());
		Assert.assertEquals(time.getRaia(), proxima);		
	}

	@Test
	public void moverEstoriaDeTesteParaProntoComSucesso(){		
		SetUpDadosIniciais(gerarEstoriaComTarefasComSituacaoProntaTrue(true), new Teste(), new Pronto());
		Assert.assertEquals(time.getRaia(), atual);
		Assert.assertTrue(time.moverEstoriaParaProximaRaia());
		Assert.assertEquals(time.getRaia(), proxima);		
	}

	@Test
	public void moverEstoriaDeEsperaParaAnaliseComOTimeTendoUmaEstoriaAlocada(){
		EstoriaDAO estoriaDAOMocada = Mockito.mock(EstoriaDAO.class);
		Mockito.when(estoriaDAOMocada.buscarEstoriaDoTime(Mockito.any(Time.class))).thenReturn(new Estoria());
		
		SetUpDadosIniciais(new Estoria(), new Espera(estoriaDAOMocada), new Analise());
		Assert.assertEquals(time.getRaia(), atual);
		Assert.assertFalse(time.moverEstoriaParaProximaRaia());
		Assert.assertEquals(time.getRaia(), atual);		
	}

	@Test
	public void moverEstoriaDeProntoParaProxima(){		
		SetUpDadosIniciais(gerarEstoriaComTarefasComSituacaoProntaTrue(true), new Pronto(), null);
		Assert.assertEquals(time.getRaia(), atual);
		Assert.assertFalse(time.moverEstoriaParaProximaRaia());
		Assert.assertEquals(time.getRaia(), atual);		
	}

	@Test
	public void moverEstoriaDeTesteParaProntoSemOsTesteEstaremProntos(){		
		SetUpDadosIniciais(gerarEstoriaComTarefasComSituacaoProntaTrue(false), new Teste(), new Pronto());
		Assert.assertEquals(time.getRaia(), atual);
		Assert.assertFalse(time.moverEstoriaParaProximaRaia());
		Assert.assertEquals(time.getRaia(), atual);		
	}

	@Test
	public void moverEstoriaDeExecucaoParaTesteSemTarefasConcluidas(){		
		SetUpDadosIniciais(gerarEstoriaComTarefa(), new Execucao(), new Teste());
		Assert.assertEquals(time.getRaia(), atual);
		Assert.assertFalse(time.moverEstoriaParaProximaRaia());
		Assert.assertEquals(time.getRaia(), atual);		
	}
	

	@Test
	public void moverEstoriaDeExecucaoParaTesteSemTarefas(){		
		SetUpDadosIniciais(new Estoria(), new Execucao(), new Teste());
		Assert.assertEquals(time.getRaia(), atual);
		Assert.assertFalse(time.moverEstoriaParaProximaRaia());
		Assert.assertEquals(time.getRaia(), atual);		
	}

	@Test
	public void moverEstoriaDeAnaliseParaExecucaoSemTarefa(){		
		SetUpDadosIniciais(new Estoria(), new Analise(), new Execucao());
		Assert.assertEquals(time.getRaia(), atual);
		Assert.assertFalse(time.moverEstoriaParaProximaRaia());
		Assert.assertEquals(time.getRaia(), atual);		
	}

	@Test
	public void moverEstoriaDeEsperaParaExecucaoSemEstoria(){
		Time timeSemNada = new Time();
		Assert.assertNull(timeSemNada.getRaia());
		Assert.assertFalse(timeSemNada.moverEstoriaParaProximaRaia());
		Assert.assertEquals(timeSemNada.getRaia(), null);		
	}
	

	@Test
	public void moverEstoriaDeEsperaParaExecucaoMasEstoriaNaoEstaEmNenhumaRaia(){
		Time timeSemNada = new Time();
		Estoria formatarMaquina = new Estoria();
		timeSemNada.setEstoria(formatarMaquina);
		Assert.assertNull(timeSemNada.getRaia());
		Assert.assertFalse(timeSemNada.moverEstoriaParaProximaRaia());
		Assert.assertEquals(timeSemNada.getRaia(), null);		
	}
	
	private Estoria gerarEstoriaComTarefa(){
		Estoria manutencao = new Estoria();
		ArrayList<Tarefa> listaDeTarefas = new ArrayList<Tarefa>();
		listaDeTarefas.add(new Tarefa());
		manutencao.setTarefas(listaDeTarefas);
		return manutencao;
	}
	
	private Estoria gerarEstoriaComTarefasComSituacaoProntaTrue(boolean situcaoDoTeste){
		Estoria manutencao = gerarEstoriaComTarefa();
		ArrayList<Tarefa> listaDeTarefas = (ArrayList<Tarefa>) manutencao.geTarefas();
		for (Tarefa tarefa : listaDeTarefas) {
			tarefa.setSituacao(true);
		}
		manutencao.setSitucaoDoTesteOk(situcaoDoTeste);
		return manutencao;
	}
}
