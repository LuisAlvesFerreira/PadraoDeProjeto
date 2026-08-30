package senhas.painel.services.factory;

import senhas.painel.model.Senha;
import senhas.painel.model.TipoSenha;
import java.time.LocalDateTime;

public class SenhaPrioritaria extends SenhaFactory {

    @Override
    public Senha criarSenha() {
        String codigo = "P" + gerarNumero();
        return new Senha(codigo, TipoSenha.PRIORITARIA, LocalDateTime.now());
    }
}