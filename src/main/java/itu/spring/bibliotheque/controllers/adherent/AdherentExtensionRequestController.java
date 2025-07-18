package itu.spring.bibliotheque.controllers.adherent;

import itu.spring.bibliotheque.enums.ExtensionRequestState;
import itu.spring.bibliotheque.enums.HolidayDirection;
import itu.spring.bibliotheque.models.Adherent;
import itu.spring.bibliotheque.models.AdherentInfo;
import itu.spring.bibliotheque.models.ExtensionRequest;
import itu.spring.bibliotheque.models.Loan;
import itu.spring.bibliotheque.models.Utilisateur;
import itu.spring.bibliotheque.services.AdherentInfoService;
import itu.spring.bibliotheque.services.AdherentService;
import itu.spring.bibliotheque.services.ExtensionRequestService;
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
    private AdherentService adherentService;
    @Autowired
    private AdherentInfoService infoService;
    @Autowired
    private LoanService loanService;

    @GetMapping("")
    public String listExtensionRequests(Model model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        // Assuming ExtensionRequestService has a method to get requests by user/adherent
        var requests = extensionRequestService.findByUserId(user.getId());
        model.addAttribute("extensionRequests", requests);
        return "adherent/extensionRequestList";
    }

    @GetMapping("/form")
    public String showExtensionForm(@RequestParam Integer loanId, Model model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        Adherent adherent = adherentService.findByUserId(user.getId());
        AdherentInfo info = infoService.findByAdherentId(adherent.getId());
        int maxExtension = info.getAvailableExtension();
        model.addAttribute("loanId", loanId);
        model.addAttribute("maxExtension", maxExtension);
        model.addAttribute("holidayDirections", HolidayDirection.values());
        return "adherent/extensionRequestForm";
    }

    @PostMapping("/request")
    public String requestExtension(@RequestParam Integer loanId, HttpSession session, Model model) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        Loan loan = loanService.findById(loanId).orElse(null);
        if (loan == null) return "redirect:/adherent/books";
        
        ExtensionRequest req = new ExtensionRequest();
        req.setLoan(loan);
        req.setRequestDate(new java.sql.Date(System.currentTimeMillis()));
        req.setState(ExtensionRequestState.Pending);
        req.setDirection(HolidayDirection.After.name());
        req.setAmount(2);
        req.setReason("Requested " + 2 + " days, direction: " + req.getDirection());
        extensionRequestService.save(req);
        return "redirect:/adherent/books";
    }
}
