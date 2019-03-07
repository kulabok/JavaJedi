package ua.com.javajedi.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import ua.com.javajedi.model.User;

public final class SecurityUtils {

	private SecurityUtils(){
		throw new RuntimeException("This class should not be instantiated.");
	}

	public static User getCurrentUser(){
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
