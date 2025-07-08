package itu.spring.bibliotheque.controllers.api;

import itu.spring.bibliotheque.models.Adherent;
import itu.spring.bibliotheque.models.BookCopy;
import itu.spring.bibliotheque.models.dto.BookLoan;
import itu.spring.bibliotheque.services.AdherentService;
import itu.spring.bibliotheque.services.BookCopyService;
import itu.spring.bibliotheque.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin()
public class BookRestController {
    @Autowired
    private BookCopyService bookCopyService;
    @Autowired
    private BookService bookService;
    @Autowired
    private AdherentService adherentService;

    @GetMapping("/api/book-copies")
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

    @GetMapping("/api/emprunts")
    public ResponseEntity<BookLoanResponse> getEmprunts(@RequestParam(value = "adherentId", required = false) Integer adherentId) {
        List<BookLoan> loans = new ArrayList<>();
        List<BookLoanDTO> result ;
        Adherent ad = null;
        if (adherentId != null) {
            loans = bookService.findLoanedBooksByAdherentId(adherentId);
        } else {
            loans = bookService.findLoanedBooks();
        }
        if (loans.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        result = loans.stream()
            .map(BookLoanDTO::new)
            .collect(Collectors.toList());

        for (BookLoanDTO bookLoanDTO : result) {
            ad = adherentService.findById(bookLoanDTO.getAdherentId());
            bookLoanDTO.setAdherent(ad.getUtilisateur().getUsername());
        }
        return ResponseEntity.ok().body(new BookLoanResponse(result));
    }
}
