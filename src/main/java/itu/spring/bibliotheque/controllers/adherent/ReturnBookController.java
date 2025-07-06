package itu.spring.bibliotheque.controllers.adherent;

import itu.spring.bibliotheque.enums.ReturnBookState;
import itu.spring.bibliotheque.models.ReturnBook;
import itu.spring.bibliotheque.models.Utilisateur;
import itu.spring.bibliotheque.models.Loan;
import itu.spring.bibliotheque.models.Adherent;
import itu.spring.bibliotheque.services.ReturnBookService;
import itu.spring.bibliotheque.services.LoanService;
import itu.spring.bibliotheque.services.AdherentService;
import itu.spring.bibliotheque.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;

@Controller
@RequestMapping("/adherent/returns")
public class ReturnBookController {
    @Autowired
    private ReturnBookService returnBookService;
    @Autowired
    private LoanService loanService;
    @Autowired
    private BookService bookService;
    @Autowired
    private AdherentService adherentService;

    @PostMapping("/create")
    public String requestReturn(@RequestParam Integer loanId, @RequestParam String returnDate, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null || !"Adherent".equals(user.getRole().getName())) {
            return "redirect:/login"; // Redirect to login if not authenticated
        }
        Adherent adherent = adherentService.findByUserId(user.getId());
        Loan loan = loanService.findById(loanId).orElse(null);
        if (adherent == null || loan == null) {
            return "redirect:/adherent/books";
        }
        ReturnBook rb = new ReturnBook();
        rb.setLoan(loan);
        rb.setAdherent(adherent);
        rb.setBook(loan.getBook());
        rb.setReturnDate(Date.valueOf(returnDate));
        rb.setState(ReturnBookState.Validated);
        returnBookService.save(rb);
        // Update status
        loan = loanService.finish(loan, rb.getReturnDate());
        loan.setBook(bookService.free(loan.getBook()));

        return "redirect:/adherent/books";
    }

    @org.springframework.web.bind.annotation.GetMapping("/form")
    public String showReturnForm(@RequestParam Integer loanId, org.springframework.ui.Model model) {
        model.addAttribute("loanId", loanId);
        model.addAttribute("today", new java.sql.Date(System.currentTimeMillis()));
        return "adherent/returnForm";
    }
}
