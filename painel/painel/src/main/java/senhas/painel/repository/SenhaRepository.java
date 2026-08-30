package senhas.painel.repository;

import senhas.painel.model.Senha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SenhaRepository extends JpaRepository<Senha, Long> {

    // Última senha chamada a partir de um horário (usado para "hoje")
    Optional<Senha> findTopByHoraChamadaAfterOrderByHoraChamadaDesc(LocalDateTime inicio);

    // Histórico apenas do dia atual
    List<Senha> findByHoraChamadaAfterOrderByHoraChamadaDesc(LocalDateTime inicio);
}