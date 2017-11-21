package ua.com.javajedi.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	private final UserDetailsService userDetailsService;

	@Autowired
	public SpringSecurityConfig(final UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/","/getRegPage", "/register").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/").permitAll()
			.defaultSuccessUrl("/login")
			.and()
			.logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

	}

	//TODO To uncomment i need to add password encryption everywhere in DAO where it can be found.
	  /*@Bean
    public PasswordEncoder bcryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }*/

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
                /*.passwordEncoder(bcryptPasswordEncoder())*/;
	}
}
