package itu.spring.bibliotheque.controllers.librarian;

import itu.spring.bibliotheque.models.Role;
import itu.spring.bibliotheque.models.Utilisateur;
import itu.spring.bibliotheque.repositories.RoleRepository;
import itu.spring.bibliotheque.services.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/librarian/users")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/new")
    public String showUserForm(Model model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null || !"Librarian".equals(user.getRole().getName())) {
            return "redirect:/login";
        }
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        return "librarian/userForm";
    }

    @PostMapping("/save")
    public String saveUser(@RequestParam String username, @RequestParam String password, @RequestParam String birthDate, @RequestParam Integer roleId, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null || !"Librarian".equals(user.getRole().getName())) {
            return "redirect:/login";
        }
        try {
            Utilisateur newUser = new Utilisateur();
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setRole(roleRepository.findById(roleId).orElse(null));
            Date date = Date.valueOf(birthDate);
            newUser.setBirthDate(date);
            utilisateurService.save(newUser);
        } catch (Exception e) {
            // handle error
        }
        return "redirect:/librarian/adherents";
    }
}
