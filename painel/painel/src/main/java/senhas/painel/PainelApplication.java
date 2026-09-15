package senhas.painel;

import senhas.painel.repository.SenhaRepository;
import senhas.painel.services.IQueueService;
import senhas.painel.services.QueueServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PainelApplication {

    public static void main(String[] args) {
        SpringApplication.run(PainelApplication.class, args);
        
    }

    // O Bean é declarado com o tipo da interface — é assim que o Spring
    // sabe injetar IQueueService em qualquer classe que a solicite,
    // sem precisar conhecer QueueServiceImpl.
    @Bean
    public IQueueService queueService(SenhaRepository senhaRepository) {
        return QueueServiceImpl.getInstance(senhaRepository);
        
    }
}
