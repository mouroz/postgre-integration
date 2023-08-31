package app;

import java.util.List;

import dao.DatabaseDAO;
import modelDb.User;

public class Main {
	public static void main(String[] args) throws Exception {
		DatabaseDAO databaseDAO = new DatabaseDAO();
		
		User user = new User(1, "john_doe", DatabaseDAO.toMD5("password"), 
				"John Doe", "Engineer", 30, 'M');
		
		System.out.println(user.sqlQueryAll());
		if (databaseDAO.insert(user) == true) {
			user.printAll();
		}
		
		List<User> users = databaseDAO.get("nome","John Doe");

		for (User i : users) {
			i.printAll();
		}
		System.out.println(users.size());
		user = databaseDAO.getByKey("1");
		user.printAll();
		
		System.out.println(databaseDAO.autenticar(user.getLogin(),"password"));
		System.out.println(databaseDAO.autenticar(user.getLogin(),DatabaseDAO.toMD5("password")));
		
		databaseDAO.deleteByKey("1");
		
	}
}
