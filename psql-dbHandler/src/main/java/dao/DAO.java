package dao;

import java.sql.*;
public class DAO {
	protected Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar(String mydatabase, String username, String password) { 
		///Localhost only
		///Default for code of current postgre: db1, ti2cc, senha123 
		
		//Values that will be always be the same for localhost
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		int porta = 5432;
		
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
}
