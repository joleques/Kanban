package br.com.app.infraestrutura.mysql;

public class ConexaoMysql extends Conexao {
	

	public ConexaoMysql(String base,String usuario, String senha) {
		super();
		super.usuario = usuario;
		super.senha = senha;
		super.base = base;
	}

	@Override
	protected String montarStringDeConexao() {
		return "jdbc:mysql://localhost:3306/"+super.base;
	}

	@Override
	protected void setarInterfaceDoDriver() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").getInterfaces();
	}

}
