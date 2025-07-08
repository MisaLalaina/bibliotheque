package itu.spring.bibliotheque.controllers.librarian;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

@Controller
@RequestMapping("/librarian/extension-requests")
public class LibrarianExtensionRequestController {
    @Autowired
    private ExtensionRequestService extensionRequestService;
    @Autowired
    private LoanService loanService;
    @Autowired
    private AdherentInfoService adherentInfoService;
    @Autowired
    private AdherentService adherentService;

    @GetMapping("")
    public String listRequests(Model model) {
        model.addAttribute("requests", extensionRequestService.getNonValidated());
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
        Loan loan = loanService.findById(request.getLoan().getId()).orElse(null);
        Adherent ad = loan.getAdherent();
        AdherentInfo info = adherentInfoService.findByAdherentId(ad.getId());
        List<ExtensionRequest> requests = extensionRequestService.getAll();
        int count = 0;
        for (ExtensionRequest extensionRequest : requests) {
            if (extensionRequest.getState().equals(ExtensionRequestState.Validated) 
                && 
                extensionRequest.getLoan().getAdherent().getId() == ad.getId()
            ) {
                count += 1;
            }
        }        

        if (count >= info.getAvailableExtension()) {
            request.setState(ExtensionRequestState.Refused);
            extensionRequestService.save(request);

            return "redirect:/librarian/extension-requests";
        }

        request.setState(ExtensionRequestState.Validated);
        request.setValidatedBy(user);
        loan = loanService.extendToDate(loan, request.getAmount().intValue(), HolidayDirection.valueOf(request.getDirection()));
        loanService.save(loan);
        extensionRequestService.save(request);
        return "redirect:/librarian/extension-requests";
    }
}
