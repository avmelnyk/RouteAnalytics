package ua.pp.avmelnyk.routeanalytics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import ua.pp.avmelnyk.routeanalytics.model.Route;
import ua.pp.avmelnyk.routeanalytics.dao.RouteServiceImpl;

import java.util.List;

@Controller
public class RouteController {

    private RouteServiceImpl routeService;

    @Autowired
    public RouteController(RouteServiceImpl routeService) {
        this.routeService = routeService;
    }

    @RequestMapping(value = "/routes", method = RequestMethod.GET)
    public void getData(Model model) {
        List<Route> routeList = routeService.getAllRoutes();
        model.addAttribute(routeList);
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm() {
        return "register";
    }

    @RequestMapping(value = "/addroute", method = RequestMethod.GET)
    public String showAddRouteform(){
        return "addroute";
    }

    @RequestMapping(value = "/editroute", method = RequestMethod.GET)
    public String showEditRouteform(){
        return "editroute";
    }

    @RequestMapping(value = "/addroute", method = RequestMethod.POST)
    public String addRoute(@RequestParam("routenumber") String routeNumber, @RequestParam("routename") String routeName){

        routeService.addRoute(new Route(routeNumber, routeName));
        return "redirect:routes";
    }
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String removeRoute(@PathVariable("id") int id){
        routeService.removeRoute(id);
        return "redirect:/routes";
    }
    @RequestMapping(value = "/editroute/{id}", method = RequestMethod.GET)
    public String editRoute(@PathVariable("id") int id, Model model){
        model.addAttribute("route", routeService.getRouteById(id));
        return "editroute";
    }
    @RequestMapping(value="/editroute/{id}", method = RequestMethod.POST)
    public String editRoutePost(@PathVariable("id") int id, @RequestParam("routenumber") String routeNumber, @RequestParam("routename") String routeName){
        routeService.updateRoute(new Route(id, routeNumber,routeName));
        return "redirect:/routes";
    }


    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String showcontacts() {
        return "contacts";
    }
}
