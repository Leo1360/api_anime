package dev.leo.api_anime.domain.anime;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "animes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ani_id")
    private Long id;
    
    @Column(name = "ani_tituloOriginal", length = 200, unique = true)
    private String tituloOriginalRomanizado;

    @Column(name = "ani_titulo", length = 150)
    private String titulo;
    
    @Column(name = "ani_descricao", length = 1000)
    private String descricao;

    @Column(name = "ani_dt_estreia")
    private LocalDate dataEstreia;

    @OneToMany
    @JoinTable(joinColumns = @JoinColumn(name = "ani_id"), inverseJoinColumns = @JoinColumn(name = "tmp_id"))
    private List<Temporada> temporadas;
    
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(joinColumns = @JoinColumn(name = "ani_id"), inverseJoinColumns = @JoinColumn(name = "cat_id"))
    private List<Categoria> categoria;
    
}
