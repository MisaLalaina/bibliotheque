package itu.spring.bibliotheque.controllers.adherent;

import itu.spring.bibliotheque.enums.HolidayDirection;
import itu.spring.bibliotheque.models.ExtensionRequest;
import itu.spring.bibliotheque.models.Loan;
import itu.spring.bibliotheque.models.Utilisateur;
import itu.spring.bibliotheque.service.ConfigService;
import itu.spring.bibliotheque.service.ExtensionRequestService;
import itu.spring.bibliotheque.services.LoanService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/adherent/extensions")
public class AdherentExtensionRequestController {
    @Autowired
    private ExtensionRequestService extensionRequestService;
    @Autowired
    private LoanService loanService;
    @Autowired
    private ConfigService configService;

    @GetMapping("/form")
    public String showExtensionForm(@RequestParam Integer loanId, Model model) {
        int maxExtension = configService.getAll().isEmpty() ? 30 : configService.getAll().get(0).getMaxExtension();
        model.addAttribute("loanId", loanId);
        model.addAttribute("maxExtension", maxExtension);
        model.addAttribute("holidayDirections", HolidayDirection.values());
        return "adherent/extensionRequestForm";
    }

    @PostMapping("/request")
    public String requestExtension(@RequestParam Integer loanId, @RequestParam Integer amount, @RequestParam HolidayDirection holidayDirection, HttpSession session, Model model) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        Loan loan = loanService.findById(loanId).orElse(null);
        if (loan == null) return "redirect:/adherent/books";
        int maxExtension = configService.getAll().isEmpty() ? 30 : configService.getAll().get(0).getMaxExtension();
        if (amount < 1 || amount > maxExtension) {
            model.addAttribute("error", "Extension amount must be between 1 and " + maxExtension);
            model.addAttribute("loanId", loanId);
            model.addAttribute("maxExtension", maxExtension);
            model.addAttribute("holidayDirections", HolidayDirection.values());
            return "adherent/extensionRequestForm";
        }
        ExtensionRequest req = new ExtensionRequest();
        req.setLoan(loan);
        req.setRequestDate(new java.sql.Date(System.currentTimeMillis()));
        req.setState(itu.spring.bibliotheque.enums.ExtensionRequestState.Pending);
        req.setReason("Requested " + amount + " days, direction: " + holidayDirection);
        extensionRequestService.save(req);
        return "redirect:/adherent/books";
    }
}
