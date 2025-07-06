package itu.spring.bibliotheque.controllers.librarian;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import itu.spring.bibliotheque.models.Utilisateur;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/librarian/loans")
public class LocationService {
    @GetMapping("")
    public String getMethodName(HttpSession session, Model model) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null || !"Librarian".equals(user.getRole().getName())) {
            return "redirect:/login";
        }
        // Here you would typically fetch the list of loans from the service layer
        return "librarian/loanList"; // Assuming you have a view named loanList.jsp
    }

    @PostMapping("/reservation")
    public String postMethodName(@RequestBody String reservationId, HttpSession session) {
        return "redirect:/librarian/loans";
    }    
}
