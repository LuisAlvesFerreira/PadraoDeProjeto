package senhas.painel.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "senhas")
public class Senha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codigo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoSenha tipo;

    @Column(nullable = false)
    private LocalDateTime horaChamada;

    protected Senha() {
    }

    public Senha(String codigo, TipoSenha tipo, LocalDateTime horaChamada) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.horaChamada = horaChamada;
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public TipoSenha getTipo() {
        return tipo;
    }

    public LocalDateTime getHoraChamada() {
        return horaChamada;
    }
}