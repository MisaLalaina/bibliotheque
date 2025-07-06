package itu.spring.bibliotheque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import itu.spring.bibliotheque.model.Utilisateur;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class HomeController {
    @GetMapping("/home")
    public String home(HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null || !"Librarian".equals(user.getRole().getName())) {
            return "redirect:/login";
        }
        return "home"; // This should return the name of the HTML template for the home page
    }    

    @GetMapping("/")
    public String index() {
        return "redirect:/home"; // Redirect to the home page
    }
}
