package ua.com.javajedi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.javajedi.db.UserRepository;
import ua.com.javajedi.model.User;
import ua.com.javajedi.service.UserServiceDB;

import java.util.List;

@Service
public class UserServiceDBImpl implements UserServiceDB {
	private final UserRepository userRepository;

	@Autowired
	public UserServiceDBImpl(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean exist(User user) {
		return userRepository.existsById(user.getId());
	}

	@Override
	public boolean existByEmail(String email) {
		if (userRepository.findByEmail(email) != null) {
			return true;
		}
		return false;
	}

	@Override
	public User add(User user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	public User update(User user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	public User delete(User user) {
		userRepository.delete(user);
		return user;
	}

	@Override
	public User findById(long userId) {
		return userRepository.findById(userId).get();
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByUsernameAndPassword(String name, String password) {
		return userRepository.findByUsernameAndPassword(name, password);
	}

	@Override
	public User findByUsername(String name) {
		return userRepository.findByUsername(name);
	}
}
