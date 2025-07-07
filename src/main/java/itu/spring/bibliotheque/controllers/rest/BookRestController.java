package itu.spring.bibliotheque.controllers.rest;

import itu.spring.bibliotheque.models.BookCopy;
import itu.spring.bibliotheque.services.BookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/book-copies")
@CrossOrigin()
public class BookRestController {
    @Autowired
    private BookCopyService bookCopyService;

    @GetMapping("")
    public List<BookCopy> getAllBookCopies(@RequestParam(value = "bookId", required = false) Integer bookId) {
        if (bookId != null) {
            return bookCopyService.findByBookId(bookId);
        } else {
            return bookCopyService.findAll();
        }
    }
}
