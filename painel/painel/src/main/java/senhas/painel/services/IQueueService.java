package senhas.painel.services;

import senhas.painel.model.Senha;
import senhas.painel.model.TipoSenha;
import java.util.List;

public interface IQueueService {
    Senha generateTicket(TipoSenha tipo);
    Senha getLastTicket();
    List<Senha> getHistory();
}