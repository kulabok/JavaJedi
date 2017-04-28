package ua.com.javajedi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.javajedi.model.User;

@Service
public class UserService implements UserDetailsService{
    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(UserService.class);
    private UserServiceDB userService;

    @Autowired
    public void setUserService(UserServiceDB userService){
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        User user = userService.findByUsername(name);

        if (user == null){
            log.info("User with name: " + name + "not found.");
            throw new UsernameNotFoundException("User with name: " + name + "not found.");
        }
        return userService.findByUsername(name);
    }
}
