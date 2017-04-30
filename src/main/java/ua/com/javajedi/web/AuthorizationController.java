package ua.com.javajedi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.javajedi.model.Role;
import ua.com.javajedi.model.User;
import ua.com.javajedi.service.IntroService;
import ua.com.javajedi.service.UserServiceDB;

@Controller
public class AuthorizationController {
    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(AuthorizationController.class);
    private IntroService introService;
    private UserServiceDB userService;

    @Autowired
    public void setIntroService(IntroService introService){
        this.introService = introService;
    }

    @Autowired
    public void setUserService(UserServiceDB userService){
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public ModelAndView getIndex(){
        ModelAndView mav = new ModelAndView();

        mav.addObject("articlesCount", introService.findArticlesCount());
        mav.addObject("exercisesCount", introService.findExercisesCount());
        mav.addObject("user", new User());
        mav.setViewName("index");
        return mav;
    }

    @GetMapping(value = "/login")
    @Secured({"USER", "ADMIN"})
    public ModelAndView login(@RequestParam(value = "logout", required = false) String logout,
                              @RequestParam(value = "error", required = false) String error){
        ModelAndView mav = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());

        mav.addObject("logout", logout!=null);
        mav.addObject("error", error!=null);
        mav.addObject("user", user);
        mav.addObject("message", "Hello, " + user.getUsername() + "!");

        log.info("User with username: " + user.getUsername() + " had been successfully authorized.");

        if (user.getAuthorities().contains(Role.ADMIN)){
            mav.addObject("admin", "You are admin - be proud of it!");
        }

        mav.setViewName("cabinet");
        return mav;
    }
}
