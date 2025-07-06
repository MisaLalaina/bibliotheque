package itu.spring.bibliotheque.controllers.librarian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import itu.spring.bibliotheque.enums.ExtensionRequestState;
import itu.spring.bibliotheque.enums.HolidayDirection;
import itu.spring.bibliotheque.models.ExtensionRequest;
import itu.spring.bibliotheque.models.Loan;
import itu.spring.bibliotheque.models.Utilisateur;
import itu.spring.bibliotheque.services.ExtensionRequestService;
import itu.spring.bibliotheque.services.LoanService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/librarian/extension-requests")
public class LibrarianExtensionRequestController {
    @Autowired
    private ExtensionRequestService extensionRequestService;
    @Autowired
    private LoanService loanService;

    @GetMapping("")
    public String listRequests(Model model) {
        model.addAttribute("requests", extensionRequestService.getAll());
        return "librarian/extensionRequestList";
    }

    @PostMapping("/validate")
    public String validateRequest(@RequestParam Integer requestId, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null || !"Librarian".equals(user.getRole().getName())) {
            return "redirect:/login";
        }
        ExtensionRequest request = extensionRequestService.getById(requestId).orElse(null);
        if (request == null) {
            return "redirect:/librarian/extension-requests?error=Request not found";
        }
        request.setState(ExtensionRequestState.Validated);
        request.setValidatedBy(user);
        Loan loan = request.getLoan();
        loan = loanService.extendToDate(loan, request.getAmount().intValue(), HolidayDirection.valueOf(request.getDirection()));
        loanService.save(loan);
        extensionRequestService.save(request);
        return "redirect:/librarian/extension-requests";
    }
}
