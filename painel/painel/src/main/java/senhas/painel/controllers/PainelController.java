package senhas.painel.controllers;

import senhas.painel.model.TipoSenha;
import senhas.painel.services.QueueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PainelController {

    private final QueueService queueService;

    public PainelController(QueueService queueService) {
        this.queueService = queueService;
    }

    @GetMapping("/atendente")
    public String index(Model model) {
        model.addAttribute("ultimaSenha", queueService.getLastTicket());
        model.addAttribute("historico", queueService.getHistory());
        return "pages/painel";
    }

    @PostMapping("/atendente/gerar-normal")
    public String gerarNormal() {
        queueService.generateTicket(TipoSenha.NORMAL);
        return "redirect:/atendente";
    }

    @PostMapping("/atendente/gerar-prioridade")
    public String gerarPrioridade() {
        queueService.generateTicket(TipoSenha.PRIORITARIA);
        return "redirect:/atendente";
    }
}
