package itu.spring.bibliotheque.controllers.rest;

import itu.spring.bibliotheque.models.BookCopy;
import itu.spring.bibliotheque.services.BookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/book-copies")
@CrossOrigin()
public class BookRestController {
    @Autowired
    private BookCopyService bookCopyService;

    @GetMapping("")
    public ResponseEntity<BookDataResponse> getAllBookCopies(@RequestParam(value = "bookId", required = false) Integer bookId) {
        List<BookCopy> copies = new ArrayList<>();
        if (bookId != null) {
            copies = bookCopyService.findByBookId(bookId);
        } else {
            copies = bookCopyService.findAll();
        }
        return ResponseEntity.ok().body(new BookDataResponse(copies));
    }
}
