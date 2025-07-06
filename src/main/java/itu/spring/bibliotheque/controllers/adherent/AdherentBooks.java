package itu.spring.bibliotheque.controllers.adherent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import itu.spring.bibliotheque.models.Book;
import itu.spring.bibliotheque.services.BookService;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/adherent/books")
public class AdherentBooks {

    @Autowired
    private BookService bookService;

    @GetMapping("")
    public String adherentBooks(HttpSession session, Model model, @RequestParam String adherentId) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
    return "adherent/bookList"; // This should return the view for the list of books for the adherent
    }
    
}
