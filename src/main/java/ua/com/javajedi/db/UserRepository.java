package ua.com.javajedi.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.javajedi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
	User findByEmail(String email);

	User findByUsernameAndPassword(String username, String password);

	User findByUsername(String username);
}
