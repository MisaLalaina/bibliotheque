package itu.spring.bibliotheque.controller;

import itu.spring.bibliotheque.model.Role;
import itu.spring.bibliotheque.model.Utilisateur;
import itu.spring.bibliotheque.service.UtilisateurService;
import itu.spring.bibliotheque.repository.RoleRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/librarian")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/users/new")
    public String showUserForm(Model model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null || !"Librarian".equals(user.getRole().getName())) {
            return "redirect:/login";
        }
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        return "librarian/userForm";
    }

    @PostMapping("/users/save")
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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(birthDate);
            newUser.setBirthDate(date);
            utilisateurService.save(newUser);
        } catch (Exception e) {
            // handle error
        }
        return "redirect:/librarian/adherents";
    }
}
