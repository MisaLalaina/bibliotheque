package itu.spring.bibliotheque.controller.adherent;

import itu.spring.bibliotheque.service.ReservationService;
import itu.spring.bibliotheque.model.Adherent;
import itu.spring.bibliotheque.model.Reservation;
import itu.spring.bibliotheque.model.Utilisateur;
import itu.spring.bibliotheque.service.AdherentService;
import itu.spring.bibliotheque.service.BookService;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/adherent/reservations")
public class AdherentReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private BookService bookService;

    @Autowired
    private AdherentService adherentService;

    @GetMapping("")
    public String listReservations(Model model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Adherent adherent = adherentService.findByUserId(user.getId());
        if (adherent == null) {
            model.addAttribute("reservations", List.of());
            return "adherent/reservationList";
        }
        List<Reservation> reservations = reservationService.findByAdherentId(adherent.getId());
        model.addAttribute("reservations", reservations);
        return "adherent/reservationList";
    }

    @GetMapping("/new")
    public String showReservationForm(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "adherent/reservationForm";
    }

    @PostMapping("/save")
    public String saveReservation(@RequestParam Integer bookId, HttpSession session) {
        
        return "redirect:/adherent/reservations";
    }
}
