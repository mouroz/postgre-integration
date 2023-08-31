package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelDb.User;

public class DatabaseDAO extends DAO {
	public String dbName = " public.users "; //database name > !between spaces!
	private String keyCol = " codigo ";
	//code expects that the class contain the following atributes:
	//selectionQuery - sql column selection query for all
	//
	
	public DatabaseDAO(){
		super();
		conectar("db1","ti2cc","senha123");
		
	}
	
	public boolean insert(User user) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			// example string - INSERT INTO public.users VALUES (...);
			String sql = "INSERT INTO" + dbName + 
					"VALUES" + user.sqlQueryAll();
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
	
	public List<User> get(String col, String value) {
		List<User> users = new ArrayList<>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			// example string - SELECT * FROM public.users WHERE id = 1
			String sql = "SELECT * FROM" + dbName + "WHERE " + col + "=" + "'"+value+"'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        while(rs.next()){
        		users.add(new User(rs.getInt("codigo"), rs.getString("login"), 
        				rs.getString("senha"), rs.getString("nome"), rs.getString("profissao"),
        				rs.getInt("idade"),rs.getString("sexo").charAt(0)));
	        	
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return users;
	}
	
	public User getByKey(String value) {
		User db = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			// example string - SELECT * FROM public.users WHERE id = 1
			String sql = "SELECT * FROM" + dbName + "WHERE " + keyCol + "=" + value;
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
	
	public boolean deleteByKey(String value) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			// example string - DELETE FROM public.users WHERE id = 1
			String sql = "DELETE FROM" + dbName + "WHERE " + keyCol + " = " + value;
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
