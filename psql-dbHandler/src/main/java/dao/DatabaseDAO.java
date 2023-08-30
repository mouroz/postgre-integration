package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelDb.User;

public class DatabaseDAO extends DAO {
	public String dbName = " public.users "; //put spaces in between for the queries to work
	//represents the name that works with the query, attempt a random sql script to check it beforehand
	public DatabaseDAO(){
		super();
		conectar("db1","ti2cc","senha123");
		
	}
	
	public boolean insert(User db) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO" + dbName + User.selectionQuery + 
					"VALUES" + db.sqlQueryAll();
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException e) {  
			    System.err.println("SQL Exception: " + e.getMessage());
			    e.printStackTrace(); // Print the full stack trace for debugging
		}
		return status;
	}
	
	public User get(String col, String value) {
		User db = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM" + dbName + "WHERE " + col + "=" + value;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){
        		db = new User(rs.getInt("codigo"), rs.getString("login"), 
        				rs.getString("senha"), rs.getString("nome"), rs.getString("profissao"),
        				rs.getInt("idade"),rs.getString("sexo").charAt(0));
	        	
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return db;
	}
	
	public boolean delete(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM "+dbName+" WHERE codigo = " + codigo;
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean autenticar(String login, String senha) {
		boolean resp = false;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM "+dbName+" WHERE login LIKE '" + login + "' AND senha LIKE '" + senha  + "'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			resp = rs.next();
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return resp;
	}	
}
