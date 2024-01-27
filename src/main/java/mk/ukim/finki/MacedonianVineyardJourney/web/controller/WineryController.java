package mk.ukim.finki.MacedonianVineyardJourney.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.MacedonianVineyardJourney.model.Role;
import mk.ukim.finki.MacedonianVineyardJourney.model.User;
import mk.ukim.finki.MacedonianVineyardJourney.model.Winery;
import mk.ukim.finki.MacedonianVineyardJourney.service.UserService;
import mk.ukim.finki.MacedonianVineyardJourney.service.WineryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/")
public class WineryController {
    private final WineryService wineryService;
    private final UserService userService;

    public WineryController(WineryService wineryService, UserService userService) {
        this.wineryService = wineryService;
        this.userService = userService;
    }

    @GetMapping(path = {"/home", "/"})
    private String getHomePage() {
        return "home";
    }

    @GetMapping("/wineries")
    private String getMainPage(Model model, HttpServletRequest request) {
        String wineryName = request.getParameter("wineryName");

        if (wineryName != null) {
            List<Winery> wineries = this.wineryService.findAllByNameContains(wineryName);
            model.addAttribute("wineries", wineries);
        } else {
            model.addAttribute("wineries", this.wineryService.findAll());
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            model.addAttribute("recentlyVisited", this.userService.getRecentlyVisitedWineriesForUserWithUsername(user.getUsername()));
        }
        return "wineries";
    }

    @GetMapping("/about-us")
    public String getAboutUsPage() {
        return "about-us";
    }

    @GetMapping("wineries/show-map/{id}")
    public String showOnMap(@PathVariable Long id, Model model, HttpServletRequest request) {
        this.wineryService.showWineryOnMap(id, model, request);
        return "map";
    }

    @GetMapping("/wineries/add")
    public String getAddWineryPage(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || user.getRole().equals(Role.ROLE_USER)) {
            model.addAttribute("wineries", this.wineryService.findAll());
            return "wineries";
        }
        return "add-page";
    }

    @PostMapping("/wineries/add")
    public String addWinery(@RequestParam String name,
                            @RequestParam String location,
                            @RequestParam String workingHours,
                            @RequestParam String town,
                            @RequestParam String activity,
                            @RequestParam String coordinates) {
        this.wineryService.save(name, location, workingHours, town, activity, coordinates);
        return "redirect:/wineries";
    }

    @PostMapping("/wineries/delete/{id}")
    public String deleteWinery(@PathVariable Long id) {
        this.wineryService.delete(id);
        return "redirect:/wineries";
    }
}
