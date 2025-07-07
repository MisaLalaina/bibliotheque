package itu.spring.bibliotheque.controllers.librarian;

import itu.spring.bibliotheque.enums.BookState;
import itu.spring.bibliotheque.models.BookCopy;
import itu.spring.bibliotheque.models.form.CopyForm;
import itu.spring.bibliotheque.services.BookCopyService;
import itu.spring.bibliotheque.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/librarian/book-copies")
public class BookCopyController {
    @Autowired
    private BookCopyService bookCopyService;
    @Autowired
    private BookService bookService;

    @GetMapping("")
    public String listBookCopies(Model model) {
        List<BookCopy> bookCopies = bookCopyService.findAll();
        model.addAttribute("bookCopies", bookCopies);
        return "librarian/bookCopyList";
    }

    @GetMapping("/new")
    public String showBookCopyForm(Model model) {
        model.addAttribute("bookCopy", new BookCopy());
        model.addAttribute("books", bookService.findAll());
        return "librarian/bookCopyForm";
    }

    @PostMapping("/save")
    public String saveBookCopy(@ModelAttribute CopyForm bookCopy) {
        BookCopy copy = new BookCopy();
        copy.setAcquisitionDate(bookCopy.getAcquisitionDate());
        copy.setBook(bookCopy.getBook());
        copy.setCopyNumber(bookCopy.getCopyNumber());
        copy.setState(BookState.Available.name());
        copy.setCopyCondition("New");
        bookCopyService.save(copy);
        return "redirect:/librarian/book-copies";
    }

    @GetMapping("/edit")
    public String editBookCopy(@RequestParam Integer id, Model model) {
        BookCopy bookCopy = bookCopyService.findById(id).orElse(null);
        model.addAttribute("bookCopy", bookCopy);
        model.addAttribute("books", bookService.findAll());
        return "librarian/bookCopyForm";
    }

    @PostMapping("/delete")
    public String deleteBookCopy(@RequestParam Integer id) {
        bookCopyService.deleteById(id);
        return "redirect:/librarian/book-copies";
    }
}
