package itu.spring.bibliotheque.controller.librarian;

import itu.spring.bibliotheque.model.Reservation;
import itu.spring.bibliotheque.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/librarian/reservations")
public class LibrarianReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("")
    public String listReservations(Model model, HttpSession session) {
        List<Reservation> reservations = reservationService.findAll();
        model.addAttribute("reservations", reservations);
        return "librarian/reservationList";
    }

    @PostMapping("/validate")
    public String validateReservation(@RequestParam Integer reservationId) {
        // Add logic to validate reservation (set state, set validatedBy, etc.)
        // ...
        return "redirect:/librarian/reservations";
    }
}
