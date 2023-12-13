package mk.ukim.finki.MacedonianWineyardJourney.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.MacedonianWineyardJourney.model.User;
import mk.ukim.finki.MacedonianWineyardJourney.model.exception.InvalidUserCredentialsException;
import mk.ukim.finki.MacedonianWineyardJourney.service.UserService;
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
        if (!username.isEmpty() && !password.isEmpty() && !name.isEmpty() && !surname.isEmpty()) {
            User user = new User(username, password, name, surname);
            this.userService.save(user);
            return "redirect:/login";
        } else {
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
            model.addAttribute("error", e.getMessage());
            return "login";
        }
        request.getSession().setAttribute("user", user);
        return "redirect:/wineries";
    }
}

