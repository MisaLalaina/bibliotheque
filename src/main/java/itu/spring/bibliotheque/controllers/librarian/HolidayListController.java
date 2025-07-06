package itu.spring.bibliotheque.controllers.librarian;

import itu.spring.bibliotheque.models.HolidayList;
import itu.spring.bibliotheque.services.HolidayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/librarian/holidays")
public class HolidayListController {
    @Autowired
    private HolidayListService holidayListService;

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("holidays", holidayListService.getAll());
        return "librarian/holidayList";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("holiday", new HolidayList());
        return "librarian/holidayForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute HolidayList holiday, Model model) {
        try {
            holidayListService.save(holiday);
            return "redirect:/librarian/holidays";
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            model.addAttribute("holiday", holiday);
            return "librarian/holidayForm";
        }
    }
}
