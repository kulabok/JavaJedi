package ua.com.javajedi.service;

import ua.com.javajedi.model.User;

import java.util.List;

public interface UserServiceDB {
	boolean exist(User user);

	boolean existByEmail(String email);

	User add(User user);

	User update(User user);

	User delete(User user);

	User findById(long userId);

	List<User> findAll();

	User findByUsernameAndPassword(String name, String password);

	User findByUsername(String name);
}
