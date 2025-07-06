package itu.spring.bibliotheque.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import itu.spring.bibliotheque.models.Utilisateur;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class HomeController {
    @GetMapping("/home")
    public String home(HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        if ("Librarian".equals(user.getRole().getName())) {
            return "librarian/home";
        } else {
            return "adherent/home";
        }
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/home"; // Redirect to the home page
    }
}
