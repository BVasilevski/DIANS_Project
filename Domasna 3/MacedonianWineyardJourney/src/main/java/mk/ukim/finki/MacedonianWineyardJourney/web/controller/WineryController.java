package mk.ukim.finki.MacedonianWineyardJourney.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.MacedonianWineyardJourney.model.Role;
import mk.ukim.finki.MacedonianWineyardJourney.model.User;
import mk.ukim.finki.MacedonianWineyardJourney.model.Winery;
import mk.ukim.finki.MacedonianWineyardJourney.service.UserService;
import mk.ukim.finki.MacedonianWineyardJourney.service.WineryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping(path = {"/home", "/"})
    private String getHomePage() {
        return "home";
    }

    @GetMapping("/wineries")
    private String getMainPage(Model model, HttpServletRequest request) {
        if (request.getParameter("wineryName") != null) {
            List<Winery> wineries = this.wineryService.findAllByNameContains(request.getParameter("wineryName"));
            model.addAttribute("wineries", wineries);
        } else {
            model.addAttribute("wineries", this.wineryService.findAll());
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user != null)
            model.addAttribute("recentlyVisited", this.userService.getRecentlyVisitedWineries(user.getUsername()));
        return "wineries";
    }

    @GetMapping("/about-us")
    public String getAboutUsPage() {
        return "about-us";
    }

    @GetMapping("wineries/show-map/{id}")
    public String showOnMap(@PathVariable Long id, Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Optional<Winery> winery = this.wineryService.findById(id);
        if (winery.isPresent()) {
            model.addAttribute("destinationAddress", winery.get().getMapLocation());
            model.addAttribute("winery", winery.get());
            if (user != null)
                this.userService.addToRecentlyVisited(user.getUsername(), winery.get().getId());
        }
        return "map";
    }

    @GetMapping("/wineries/add")
    public String getAddWineryPage(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || user.getRole().equals(Role.ROLE_USER)) {
            model.addAttribute("accessDenied", true);
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
        Winery winery = new Winery(name, location, workingHours, town, activity, coordinates);
        this.wineryService.save(winery);
        return "redirect:/wineries";
    }

    @PostMapping("/wineries/delete/{id}")
    public String deleteWinery(@PathVariable Long id) {
        this.wineryService.delete(id);
        return "redirect:/wineries";
    }
}
