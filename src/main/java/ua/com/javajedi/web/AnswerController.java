package ua.com.javajedi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.javajedi.model.Role;
import ua.com.javajedi.model.User;
import ua.com.javajedi.model.statistics.Action;
import ua.com.javajedi.model.statistics.Page;
import ua.com.javajedi.model.statistics.Statistics;
import ua.com.javajedi.service.AnswerService;
import ua.com.javajedi.service.ExerciseService;
import ua.com.javajedi.service.StatisticsService;

import static ua.com.javajedi.utils.SecurityUtils.getCurrentUser;

@Controller
public class AnswerController {
	private final ExerciseService exerciseService;
	private final AnswerService answerService;
	private final StatisticsService statisticsService;

	@Autowired
	public AnswerController(final ExerciseService exerciseService,
	                        final AnswerService answerService,
	                        final StatisticsService statisticsService) {
		this.exerciseService = exerciseService;
		this.answerService = answerService;
		this.statisticsService = statisticsService;
	}

	@PostMapping(value = "/resolveAnswer")
	@Secured({"USER", "ADMIN"})
	public ModelAndView resolve(String answer,
	                            String exerciseId,
	                            ModelAndView mav) {

		User user = getCurrentUser();
		String message = answerService.resolveAnswer(exerciseId, answer, user);

		mav.addObject("user", user);
		mav.addObject("exercises", exerciseService.findAll());
		mav.addObject("message", message);
		if (user.getAuthorities().contains(Role.ADMIN)) {
			mav.addObject("stats", statisticsService.getAllForAdmin());
		}

		mav.setViewName("cabinet");
		statisticsService.save(Statistics.of(Page.CABINET, Action.TRY_EXERCISE));
		return mav;
	}

}
