package mk.ukim.finki.wineyardjourney.web.controller;

import mk.ukim.finki.wineyardjourney.service.WineryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class WineryController {
    private final WineryService wineryService;

    public WineryController(WineryService wineryService) {
        this.wineryService = wineryService;
    }

    @GetMapping("/home")
    private String getHomePage() {
        return "home";
    }

    @GetMapping("/wineries")
    private String getMainPage(Model model) {
        model.addAttribute("wineries", this.wineryService.findAll());
        return "wineries";
    }

    @GetMapping("/about-us")
    public String getAboutUsPage() {
        return "about-us";
    }
}
