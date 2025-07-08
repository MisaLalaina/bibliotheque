package itu.spring.bibliotheque.controllers.api;

import itu.spring.bibliotheque.models.BookCopy;
import itu.spring.bibliotheque.services.BookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/book-copies")
@CrossOrigin()
public class BookRestController {
    @Autowired
    private BookCopyService bookCopyService;

    @GetMapping("")
    public ResponseEntity<BookDataResponse> getAllBookCopies(@RequestParam(value = "bookId", required = false) Integer bookId) {
        List<BookCopy> copies = new ArrayList<>();
        List<BookCopyDTO> result ;
        if (bookId != null) {
            copies = bookCopyService.findByBookId(bookId);
        } else {
            copies = bookCopyService.findAll();
        }
        if (copies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        result = copies.stream()
            .map(BookCopyDTO::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok().body(new BookDataResponse(result));
    }
}
