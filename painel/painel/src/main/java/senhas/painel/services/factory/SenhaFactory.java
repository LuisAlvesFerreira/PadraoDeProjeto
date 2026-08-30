package senhas.painel.services.factory;

import senhas.painel.model.Senha;

public abstract class SenhaFactory {

    public abstract Senha criarSenha();

    protected String gerarNumero() {
        long ticks = System.currentTimeMillis();
        String ticksStr = String.valueOf(ticks);
        return ticksStr.substring(ticksStr.length() - 4);
    }
}