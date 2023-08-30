package app;

import dao.DatabaseDAO;
import modelDb.User;

public class Main {
	public static void main(String[] args) throws Exception {
		DatabaseDAO databaseDAO = new DatabaseDAO();
		
		User user = new User(1, "john_doe", "password", 
				"John Doe", "Engineer", 30, 'M');
		
		System.out.println(user.sqlQueryAll());
		if (databaseDAO.insert(user) == true) {
			user.printAll();
		}
		
		User user2 = databaseDAO.get("codigo","1");
		if (user2!=null) user2.printAll();
		
		databaseDAO.autenticar(user.getLogin(),user.getSenha());
		databaseDAO.autenticar(user.getLogin(),"123");
		
		databaseDAO.delete(1);
		
	}
}
