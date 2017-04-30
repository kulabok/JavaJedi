package ua.com.javajedi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.javajedi.model.Role;
import ua.com.javajedi.model.User;
import ua.com.javajedi.service.AnswerService;
import ua.com.javajedi.service.ExerciseService;

@Controller
public class AnswerController {
    private ExerciseService exerciseService;
    private AnswerService answerService;

    @PostMapping(value = "/resolveAnswer")
    @Secured({"USER", "ADMIN"})
    public ModelAndView resolve(String answer,
                                String exerciseId,
                                ModelAndView mav){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String message = answerService.resolveAnswer(exerciseId, answer, user);

        mav.addObject("user", user);
        mav.addObject("exercises", exerciseService.findAll());
        mav.addObject("message", message);

        mav.setViewName("cabinet");
        return mav;
    }

    @Autowired
    public void setExerciseService(ExerciseService exerciseService){
        this.exerciseService = exerciseService;
    }

    @Autowired
    public void setAnswerService(AnswerService answerService){
        this.answerService = answerService;
    }
}
