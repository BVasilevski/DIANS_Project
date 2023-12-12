package mk.ukim.finki.wineyardjourney.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wineyardjourney.model.Role;
import mk.ukim.finki.wineyardjourney.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class UserController {
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
                                Model model,
                                HttpServletRequest request) {
        User user = new User(username, password, name, surname, Role.ROLE_USER);
        request.getSession().setAttribute("user", user);
        return "redirect:/wineries";
    }

}
