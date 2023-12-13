package mk.ukim.finki.MacedonianWineyardJourney.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.MacedonianWineyardJourney.model.User;
import mk.ukim.finki.MacedonianWineyardJourney.model.Winery;
import mk.ukim.finki.MacedonianWineyardJourney.service.UserService;
import mk.ukim.finki.MacedonianWineyardJourney.service.WineryService;
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
    private final UserService userService;

    public WineryController(WineryService wineryService, UserService userService) {
        this.wineryService = wineryService;
        this.userService = userService;
    }

    @GetMapping("/home")
    private String getHomePage(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "home";
    }

    @GetMapping("/wineries")
    private String getMainPage(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/login";
        }
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("wineries", this.wineryService.findAll());
        model.addAttribute("recentlyVisited", this.userService.getRecentlyVisitedWineries(user.getUsername()));
        return "wineries";
    }

    @GetMapping("/about-us")
    public String getAboutUsPage(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "about-us";
    }

    @GetMapping("wineries/show-map/{id}")
    public String showOnMap(@PathVariable Long id, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/login";
        }
        User user = (User) request.getSession().getAttribute("user");
        Optional<Winery> winery = this.wineryService.findById(id);
        if (winery.isPresent()) {
            model.addAttribute("destinationAddress", winery.get().getMapLocation());
            model.addAttribute("winery", winery.get());
            this.userService.addToRecentlyVisited(user.getUsername(), winery.get().getId());
        }
        return "map";
    }
}
