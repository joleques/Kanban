package br.com.app.infraestrutura.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class DAOMysql {

	protected Connection conexao;
	protected String base;
	protected String usuario;
	protected String senha;
	protected PreparedStatement comando = null;
	
	protected void prepararConexao() throws ClassNotFoundException, SQLException{
		ConexaoMysql mysql = new ConexaoMysql(base, usuario, senha);
		conexao = mysql.conectarAhBase();
	}
	
	protected void executarSqlFechandoConexao(String sql) throws SQLException{
		executarSqlSemFecharConexao(sql);
		fecharConexao();		
	}
	
	protected void fecharConexao() throws SQLException{
		comando.close();
	}
	
	protected void executarSqlSemFecharConexao(String sql) throws SQLException{
		comando = conexao.prepareStatement(sql);
		comando.execute();		
	}
	
}
