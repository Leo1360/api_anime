package dev.leo.api_anime.domain.anime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "epsodios")
public class Episodio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eps_id")
    private Long id;

    @Column(name = "eps_descricao", length = 1000)
    private String descricao;

    @Column(name = "eps_titulo", length = 100)
    private String titulo;

    @Column(name = "eps_duracao")
    private long duracao;

}
