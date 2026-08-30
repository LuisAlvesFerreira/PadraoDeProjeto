package senhas.painel;

import senhas.painel.repository.SenhaRepository;
import senhas.painel.services.QueueService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PainelApplication {

    public static void main(String[] args) {
        SpringApplication.run(PainelApplication.class, args);
    }

    @Bean
    public QueueService queueService(SenhaRepository senhaRepository) {
        return QueueService.getInstance(senhaRepository);
    }
}