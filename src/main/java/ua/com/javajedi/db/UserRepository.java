package ua.com.javajedi.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.javajedi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.email=:email")
    User existByEmail(@Param("email") String email);

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}