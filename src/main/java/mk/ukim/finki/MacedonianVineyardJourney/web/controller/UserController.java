package mk.ukim.finki.MacedonianVineyardJourney.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.MacedonianVineyardJourney.model.User;
import mk.ukim.finki.MacedonianVineyardJourney.model.exception.InvalidUserCredentialsException;
import mk.ukim.finki.MacedonianVineyardJourney.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    private String getRegisterPage() {
        return "register";
    }

    @GetMapping("/login")
    private String getLoginPage() {
        return "login";
    }

    @PostMapping("/register")
    private String registerUser(@RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String name,
                                @RequestParam String surname,
                                Model model) {
        try {
            this.userService.save(username, password, name, surname);
            return "redirect:/login";
        } catch (InvalidUserCredentialsException ex) {
            model.addAttribute("hasError", true);
            return "register";
        }
    }

    @PostMapping("/login")
    private String loginUser(@RequestParam String username,
                             @RequestParam String password,
                             HttpServletRequest request,
                             Model model) {
        User user;
        try {
            user = userService.login(username, password);
        } catch (InvalidUserCredentialsException e) {
            model.addAttribute("hasError", true);
            return "login";
        }
        request.getSession().setAttribute("user", user);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    private String logoutUser(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "redirect:/home";
    }
}

