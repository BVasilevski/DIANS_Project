package mk.ukim.finki.wineyardjourney.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wineyardjourney.model.Role;
import mk.ukim.finki.wineyardjourney.model.User;
import mk.ukim.finki.wineyardjourney.service.UserService;
import org.springframework.stereotype.Controller;
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
                                @RequestParam String surname) {
        User user = new User(username, password, name, surname, Role.ROLE_USER);
        this.userService.save(user);
        return "redirect:/login";
    }

    @PostMapping("/login")
    private String loginUser(@RequestParam String username,
                             @RequestParam String password,
                             HttpServletRequest request) {
        if (this.userService.login(username, password)) {
            User user = this.userService.findByUsername(username);
            request.getSession().setAttribute("user", user);
        }
        return "redirect:/wineries";
    }
}
