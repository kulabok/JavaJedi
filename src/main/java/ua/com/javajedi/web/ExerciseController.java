package ua.com.javajedi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.com.javajedi.model.Exercise;
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
public class ExerciseController {
	private final ExerciseService exerciseService;
	private final AnswerService answerService;
	private final StatisticsService statisticsService;

	@Autowired
	public ExerciseController(final ExerciseService exerciseService,
	                          final AnswerService answerService,
	                          final StatisticsService statisticsService) {
		this.exerciseService = exerciseService;
		this.answerService = answerService;
		this.statisticsService = statisticsService;
	}

	@GetMapping(value = "/exercises/all")
	@Secured({"USER", "ADMIN"})
	public ModelAndView findAllExercises(ModelAndView mav) {
		mav.addObject("exercises", exerciseService.findAll());
		if (getCurrentUser().getAuthorities().contains(Role.ADMIN)) {
			mav.addObject("stats", statisticsService.getAllForAdmin());
		}
		mav.setViewName("cabinet");
		statisticsService.save(Statistics.of(Page.CABINET, Action.GET_EXERCISES_ALL));
		return mav;
	}

	@GetMapping(value = "exercises/undone")
	@Secured({"USER", "ADMIN"})
	public ModelAndView findAllUndone(ModelAndView mav) {
		User user = getCurrentUser();
		mav.addObject("undone", exerciseService.findAllUndone(user.getId()));
		if (user.getAuthorities().contains(Role.ADMIN)) {
			mav.addObject("stats", statisticsService.getAllForAdmin());
		}
		mav.setViewName("cabinet");
		statisticsService.save(Statistics.of(Page.CABINET, Action.GET_EXERCISES_UNDONE));
		return mav;
	}

	@GetMapping(value = "/exercise/findByTitle")
	@Secured({"USER", "ADMIN"})
	public ModelAndView findByTitle(String title,
	                                ModelAndView mav) {
		Exercise exercise = exerciseService.findByTitle(title);
		mav.addObject("exercise", exercise);
		mav.addObject("answers", answerService.findAllByExerciseId(exercise.getId()));
		if (getCurrentUser().getAuthorities().contains(Role.ADMIN)) {
			mav.addObject("stats", statisticsService.getAllForAdmin());
		}
		mav.setViewName("cabinet");
		statisticsService.save(Statistics.of(Page.CABINET, Action.TRY_EXERCISE));
		return mav;
	}
}
