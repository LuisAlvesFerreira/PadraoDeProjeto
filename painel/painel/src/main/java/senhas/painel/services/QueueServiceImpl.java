package senhas.painel.services;

import senhas.painel.model.Senha;
import senhas.painel.model.TipoSenha;
import senhas.painel.repository.SenhaRepository;
import senhas.painel.services.factory.SenhaFactory;
import senhas.painel.services.factory.SenhaNormal;
import senhas.painel.services.factory.SenhaPrioritaria;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class QueueServiceImpl implements IQueueService {

    // 1. Atributo estático privado que guarda a única instância da classe
    private static QueueServiceImpl uniqueInstance;

    private final SenhaRepository senhaRepository;

    // 2. Construtor privado
    private QueueServiceImpl(SenhaRepository senhaRepository) {
        this.senhaRepository = senhaRepository;
    }

    // 3. Ponto de acesso único e thread-safe
    public static synchronized QueueServiceImpl getInstance(SenhaRepository senhaRepository) {
        if (uniqueInstance == null) {
            uniqueInstance = new QueueServiceImpl(senhaRepository);
        }
        return uniqueInstance;
    }

    @Override
    public Senha generateTicket(TipoSenha tipo) {
        SenhaFactory factory = (tipo == TipoSenha.PRIORITARIA)
                ? new SenhaPrioritaria()
                : new SenhaNormal();

        Senha senha = factory.criarSenha();
        return senhaRepository.save(senha);
    }

    @Override
    public Senha getLastTicket() {
        return senhaRepository
                .findTopByHoraChamadaAfterOrderByHoraChamadaDesc(inicioDoDiaAtual())
                .orElse(null);
    }

    @Override
    public List<Senha> getHistory() {
        return senhaRepository.findByHoraChamadaAfterOrderByHoraChamadaDesc(inicioDoDiaAtual());
    }

    private LocalDateTime inicioDoDiaAtual() {
        return LocalDate.now().atStartOfDay();
    }
}