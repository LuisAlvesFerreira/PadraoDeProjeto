package senhas.painel.services.factory;

import senhas.painel.model.Senha;

public interface SenhaFactory {

    // Factory Method: cada implementação decide como construir a senha, da maior praticidade a execução 
    Senha criarSenha();

    // Metodo default: lógica compartilhada, sem precisar de classe abstrata
    default String gerarNumero() {
        long ticks = System.currentTimeMillis();
        String ticksStr = String.valueOf(ticks);
        return ticksStr.substring(ticksStr.length() - 4);
    }
}