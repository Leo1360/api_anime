package dev.leo.api_anime.domain.anime;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "temporadas")
public class Temporada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tmp_id")
    private Long id;

    @Column(name = "tmp_titulo", length = 100)
    private String titulo;

    @Column(name = "tmp_descricao", length = 1000)
    private String descricao;

    @Column(name = "tmp_estreia")
    private LocalDate lancamento;
    
    @Enumerated(EnumType.STRING)
    private StatusTemporada status;

    @OneToMany
    @JoinTable(joinColumns = @JoinColumn(name = "eps_id"), inverseJoinColumns = @JoinColumn(name = "tmp_id"))
    private List<Episodio> epsodios;
}
