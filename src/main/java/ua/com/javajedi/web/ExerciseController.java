package ua.com.javajedi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ua.com.javajedi.model.Exercise;
import ua.com.javajedi.model.Role;
import ua.com.javajedi.model.User;
import ua.com.javajedi.service.AnswerService;
import ua.com.javajedi.service.ExerciseService;

@Controller
public class ExerciseController {
    private ExerciseService exerciseService;
    private AnswerService answerService;

    @GetMapping(value = "/exercises/all")
    @Secured({"USER", "ADMIN"})
    public ModelAndView findAllExercises(ModelAndView mav){

        mav.addObject("exercises", exerciseService.findAll());

        if (getCurrentUser().getAuthorities().contains(Role.ADMIN)){
            mav.addObject("admin", "You are admin!");
        }

        mav.setViewName("cabinet");
        return mav;
    }

    @GetMapping(value = "exercises/undone")
    @Secured({"USER", "ADMIN"})
    public ModelAndView findAllUndone(ModelAndView mav){

        User user = getCurrentUser();

        mav.addObject("undone", exerciseService.findAllUndone(user.getUserId()));

        if (user.getAuthorities().contains(Role.ADMIN)){
            mav.addObject("admin", "You are admin!");
        }

        mav.setViewName("cabinet");
        return mav;
    }

    @GetMapping(value = "/exercise/findByTitle")
    @Secured({"USER", "ADMIN"})
    public ModelAndView findByTitle(String title,
                                    ModelAndView mav){
        Exercise exercise = exerciseService.findByTitle(title);

        mav.addObject("exercise", exercise);
        mav.addObject("answers", answerService.findAllByExerciseId(exercise.getExerciseId()));

        if (getCurrentUser().getAuthorities().contains(Role.ADMIN)){
            mav.addObject("admin", "You are admin!");
        }

        mav.setViewName("cabinet");
        return mav;
    }

    @Autowired
    public void setExerciseService(ExerciseService exerciseService){
        this.exerciseService = exerciseService;
    }

    @Autowired
    public  void setAnswerService(AnswerService answerService){
        this.answerService = answerService;
    }

    private User getCurrentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
