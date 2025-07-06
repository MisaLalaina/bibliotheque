package itu.spring.bibliotheque.controllers.librarian;

import itu.spring.bibliotheque.service.ExtensionRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/librarian/extension-requests")
public class LibrarianExtensionRequestController {
    @Autowired
    private ExtensionRequestService extensionRequestService;

    @GetMapping("")
    public String listRequests(Model model) {
        model.addAttribute("requests", extensionRequestService.getAll());
        return "librarian/extensionRequestList";
    }

    @PostMapping("/validate")
    public String validateRequest(@RequestParam Integer requestId) {
        // Add validation logic here
        // extensionRequestService.validate(requestId);
        return "redirect:/librarian/extension-requests";
    }
}
