package itu.spring.bibliotheque.controllers.librarian;

import itu.spring.bibliotheque.models.Subscription;
import itu.spring.bibliotheque.services.AdherentService;
import itu.spring.bibliotheque.services.SubscriptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/librarian/subscriptions")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private AdherentService adherentService;

    @GetMapping("")
    public String listSubscriptions(Model model) {
        List<Subscription> subscriptions = subscriptionService.findAll();
        model.addAttribute("subscriptions", subscriptions);
        return "librarian/subscriptionList";
    }

    @GetMapping("/new")
    public String showSubscriptionForm(Model model) {
        model.addAttribute("adherents", adherentService.findAll());
        return "librarian/subscriptionForm";
    }

    @PostMapping("/save")
    public String saveSubscription(@RequestParam Integer adherentId, @RequestParam String fromDate, @RequestParam String toDate) {
        try {
            Subscription subscription = new Subscription();
            subscription.setAdherent(adherentService.findById(adherentId).orElse(null));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            subscription.setFromDate(sdf.parse(fromDate));
            subscription.setToDate(sdf.parse(toDate));
            subscriptionService.save(subscription);
        } catch (Exception e) {
            // handle error
        }
        return "redirect:/librarian/subscriptions";
    }
}
