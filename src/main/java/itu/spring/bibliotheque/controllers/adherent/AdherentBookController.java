package itu.spring.bibliotheque.controllers.adherent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import itu.spring.bibliotheque.models.Adherent;
import itu.spring.bibliotheque.models.Book;
import itu.spring.bibliotheque.models.Utilisateur;
import itu.spring.bibliotheque.models.dto.BookLoan;
import itu.spring.bibliotheque.services.AdherentService;
import itu.spring.bibliotheque.services.BookService;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/adherent/books")
public class AdherentBookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private AdherentService adherentService;

    @GetMapping("")
    public String adherentBooks(HttpSession session, Model model) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null || !"Adherent".equals(user.getRole().getName())){
            return "redirect:/login"; // Redirect to login if user is not logged in or not an adherent
        }
        Adherent adherent = adherentService.findByUserId(user.getId());
        List<BookLoan> books = bookService.findLoanedBooksByAdherentId(adherent.getId());
        model.addAttribute("books", books);
    return "adherent/bookList"; // This should return the view for the list of books for the adherent
    }
    
}
