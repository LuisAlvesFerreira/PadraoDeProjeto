package senhas.painel.services.factory;

import senhas.painel.model.Senha;
import senhas.painel.model.TipoSenha;

import java.time.LocalDateTime;

public class SenhaNormal extends SenhaFactory {

    @Override
    public Senha criarSenha() {
        String codigo = "N" + gerarNumero();
        return new Senha(codigo, TipoSenha.NORMAL, LocalDateTime.now());
    }
}