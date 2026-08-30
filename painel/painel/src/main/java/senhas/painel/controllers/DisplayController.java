package senhas.painel.controllers;

import senhas.painel.services.QueueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DisplayController {

    private final QueueService queueService;

    public DisplayController(QueueService queueService) {
        this.queueService = queueService;
    }

    @GetMapping("/display")
    public String display(Model model) {
        model.addAttribute("ultimaChamada", queueService.getLastTicket());
        return "pages/display";
    }
}