package ua.com.javajedi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.javajedi.model.User;
import ua.com.javajedi.service.UserServiceDB;

@Controller
public class UserController {
	private final UserServiceDB userServiceDB;

	@Autowired
	public UserController(UserServiceDB userServiceDB) {
		this.userServiceDB = userServiceDB;
	}

	@GetMapping(value = "/getRegPage")
	public ModelAndView getRegPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("register");
		return mav;
	}

	@PostMapping(value = "/register")
	public ModelAndView register(@RequestParam String username,
	                             @RequestParam String email,
	                             @RequestParam String password,
	                             @RequestParam String aboutMe) {
		ModelAndView mav = new ModelAndView();
		final User user = userServiceDB.add(new User(username, email, password, aboutMe));

		if (user != null) {
			mav.setViewName("index");
			return mav;
		}
		mav.addObject("message", "Some problem with your registration. Please try again.");
		mav.setViewName("error");
		return mav;
	}
}
