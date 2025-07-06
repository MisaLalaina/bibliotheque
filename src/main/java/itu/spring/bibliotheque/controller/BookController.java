package itu.spring.bibliotheque.controller;

import itu.spring.bibliotheque.model.Book;
import itu.spring.bibliotheque.service.BookService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String listBooks(Model model, HttpSession session) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "bookList";
    }

    @GetMapping("/books/new")
    public String showBookForm(Model model, HttpSession session) {
        model.addAttribute("book", new Book());
        return "bookForm";
    }

    @PostMapping("/books/save")
    public String saveBook(@RequestParam String title, @RequestParam String author, @RequestParam Integer ageMin, @RequestParam String state) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setAgeMin(ageMin);
        book.setState(state);
        bookService.save(book);
        return "redirect:/books";
    }
}
