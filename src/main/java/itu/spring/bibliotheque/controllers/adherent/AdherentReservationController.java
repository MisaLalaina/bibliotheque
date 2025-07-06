package itu.spring.bibliotheque.controllers.adherent;

import itu.spring.bibliotheque.models.Adherent;
import itu.spring.bibliotheque.models.Reservation;
import itu.spring.bibliotheque.models.Utilisateur;
import itu.spring.bibliotheque.services.AdherentService;
import itu.spring.bibliotheque.services.BookService;
import itu.spring.bibliotheque.services.ReservationService;
import jakarta.servlet.http.HttpSession;

import java.sql.Date;
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
    public String saveReservation(Model model, @RequestParam Integer bookId, @RequestParam String reservationDate, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Date refDate = Date.valueOf(reservationDate);
        Adherent adherent = adherentService.findByUserId(user.getId());
        if (adherent == null) {
            return "redirect:/login";
        }
        try {
            reservationService.createReservationWithConstraints(adherent, bookId, refDate);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("books", bookService.findAll());
            return "adherent/reservationForm";
        }
        return "redirect:/adherent/reservations";
    }
}
