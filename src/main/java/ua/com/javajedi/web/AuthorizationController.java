package ua.com.javajedi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.javajedi.model.*;
import ua.com.javajedi.model.statistics.Action;
import ua.com.javajedi.model.statistics.Page;
import ua.com.javajedi.model.statistics.Statistics;
import ua.com.javajedi.service.IntroService;
import ua.com.javajedi.service.StatisticsService;

@Controller
public class AuthorizationController {
	private static final org.slf4j.Logger log =
		org.slf4j.LoggerFactory.getLogger(AuthorizationController.class);
	private final IntroService introService;
	private final StatisticsService statisticsService;

	@Autowired
	public AuthorizationController(final IntroService introService,
	                               final StatisticsService statisticsService) {
		this.introService = introService;
		this.statisticsService = statisticsService;
	}

	@GetMapping(value = "/")
	public ModelAndView getIndex() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("articlesCount", introService.findArticlesCount());
		mav.addObject("exercisesCount", introService.findExercisesCount());
		mav.setViewName("index");
		statisticsService.save(Statistics.of(Page.INDEX, Action.GET_INDEX));
		return mav;
	}

	@GetMapping(value = "/login")
	@Secured({"USER", "ADMIN"})
	public ModelAndView login(@RequestParam(value = "logout", required = false) String logout,
	                          @RequestParam(value = "error", required = false) String error) {
		ModelAndView mav = new ModelAndView();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mav.addObject("logout", logout != null);
		mav.addObject("error", error != null);
		mav.addObject("user", user);
		mav.addObject("message", "Hello, " + user.getUsername() + "!");
		log.info("User with username: " + user.getUsername() + " had been successfully authorized.");
		if (user.getAuthorities().contains(Role.ADMIN)) {
			mav.addObject("stats", statisticsService.getAllForAdmin());
		}
		mav.setViewName("cabinet");
		statisticsService.save(Statistics.of(Page.CABINET, Action.LOGIN));
		return mav;
	}

	@GetMapping(value = "/logout")
	@Secured({"USER", "ADMIN"})
	public ModelAndView logout(final ModelAndView mav){
		mav.setViewName("index");
		return mav;
	}
}
