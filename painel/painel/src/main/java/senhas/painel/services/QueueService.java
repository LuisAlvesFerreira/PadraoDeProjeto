package senhas.painel.services;

import senhas.painel.model.Senha;
import senhas.painel.model.TipoSenha;
import senhas.painel.repository.SenhaRepository;
import senhas.painel.services.factory.SenhaFactory;
import senhas.painel.services.factory.SenhaNormal;
import senhas.painel.services.factory.SenhaPrioritaria;

import java.time.LocalDate;
import java.util.List;

public class QueueService {

    // 1. Atributo estático privado que guarda a única instância da classe
    private static QueueService uniqueInstance;

    private final SenhaRepository senhaRepository;

    // 2. Construtor privado
    private QueueService(SenhaRepository senhaRepository) {
        this.senhaRepository = senhaRepository;
    }

    // 3. Ponto de acesso único e thread-safe
    public static synchronized QueueService getInstance(SenhaRepository senhaRepository) {
        if (uniqueInstance == null) {
            uniqueInstance = new QueueService(senhaRepository);
        }
        return uniqueInstance;
    }

    // Gera a senha delegando para a fábrica correspondente ao tipo
    public Senha generateTicket(TipoSenha tipo) {
        SenhaFactory factory = (tipo == TipoSenha.PRIORITARIA)
                ? new SenhaPrioritaria()
                : new SenhaNormal();

        Senha senha = factory.criarSenha();
        return senhaRepository.save(senha);
    }

    // Só considera senhas de hoje — no dia seguinte, retorna null naturalmente
    public Senha getLastTicket() {
        return senhaRepository
                .findTopByHoraChamadaAfterOrderByHoraChamadaDesc(inicioDoDiaAtual())
                .orElse(null);
    }

    // Histórico também restrito ao dia atual — reinício automático diário
    public List<Senha> getHistory() {
        return senhaRepository.findByHoraChamadaAfterOrderByHoraChamadaDesc(inicioDoDiaAtual());
    }

    private java.time.LocalDateTime inicioDoDiaAtual() {
        return LocalDate.now().atStartOfDay();
    }
}