package itu.spring.bibliotheque.controller;

import itu.spring.bibliotheque.model.Adherent;
import itu.spring.bibliotheque.model.AdherentInfo;
import itu.spring.bibliotheque.model.Utilisateur;
import itu.spring.bibliotheque.service.AdherentInfoService;
import itu.spring.bibliotheque.service.AdherentService;
import itu.spring.bibliotheque.service.AdherentTypeService;
import itu.spring.bibliotheque.service.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/librarian/adherents")
public class AdherentController {
    @Autowired
    private AdherentService adherentService;
    @Autowired
    private AdherentTypeService adherentTypeService;
    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private AdherentInfoService adherentInfoService;

    @GetMapping("")
    public String listAdherents(Model model, HttpSession session) {
        // Only allow for role 1 (Librarian)
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null || !"Librarian".equals(user.getRole().getName())) {
            return "redirect:/login";
        }
        List<Adherent> adherents = adherentService.findAll();
        model.addAttribute("adherents", adherents);
        return "librarian/adherentList";
    }

    @GetMapping("/new")
    public String showAdherentForm(Model model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null || !"Librarian".equals(user.getRole().getName())) {
            return "redirect:/login";
        }
        model.addAttribute("adherent", new Adherent());
        model.addAttribute("users", utilisateurService.findAll());
        model.addAttribute("adherentTypes", adherentTypeService.findAll());
        return "librarian/adherentForm";
    }

    @PostMapping("/save")
    public String saveAdherent(@RequestParam Integer userId, @RequestParam Integer adherentTypeId, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null || !"Librarian".equals(user.getRole().getName())) {
            return "redirect:/login";
        }
        Adherent adherent = new Adherent();
        adherent.setUtilisateur(utilisateurService.findById(userId).orElse(null));
        adherent.setAdherentType(adherentTypeService.findById(adherentTypeId).orElse(null));
        adherentService.save(adherent);

        // Create AdherentInfo for the new adherent
        AdherentInfo info = adherentTypeService.createInfo(adherent);
        adherentInfoService.save(info);

        return "redirect:/librarian";
    }
}
