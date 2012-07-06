package br.com.app.infraestrutura.mysql;

import java.sql.*;

import com.mysql.jdbc.Connection;

abstract class Conexao {

	private boolean status;
	private Connection conexao;
	protected String usuario;
	protected String senha;	
	protected String base;
	
	public Conexao() {
		status = false;
		conexao = null;
	}

	public boolean qualStatusDaConexao() {
		return status;
	}
	
	private void abrirConexao() throws ClassNotFoundException, SQLException {
		setarInterfaceDoDriver();
		conexao = (Connection) DriverManager.getConnection(montarStringDeConexao(),usuario,senha);
		status = true;
	}

	public Connection conectarAhBase() throws ClassNotFoundException, SQLException {
		abrirConexao();
		return conexao;
	}
	
	protected abstract String montarStringDeConexao();
	protected abstract void setarInterfaceDoDriver() throws ClassNotFoundException;

}
