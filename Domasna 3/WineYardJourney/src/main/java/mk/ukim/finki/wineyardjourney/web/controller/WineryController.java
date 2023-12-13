package mk.ukim.finki.wineyardjourney.web.controller;

import mk.ukim.finki.wineyardjourney.model.Winery;
import mk.ukim.finki.wineyardjourney.service.WineryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


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

    @GetMapping("wineries/show-map/{id}")
    public String showOnMap(@PathVariable Long id, Model model) {
        Optional<Winery> winery = this.wineryService.findById(id);
        winery.ifPresent(value -> model.addAttribute("destinationAddress", value.getMapLocation()));
        return "map";
    }
}
