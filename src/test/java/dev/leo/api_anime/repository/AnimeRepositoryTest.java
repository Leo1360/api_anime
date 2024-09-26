package dev.leo.api_anime.repository;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import dev.leo.api_anime.domain.anime.Anime;
import dev.leo.api_anime.domain.anime.Categoria;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@IndicativeSentencesGeneration(separator = " -> ", generator = ReplaceUnderscores.class)
public class AnimeRepositoryTest {
    @Autowired
    AnimeRepository animeRepository;

    @Test
    public void AnimeRepository_save_ReturnsSavedAnime(){
        //Arrange
        Anime anime = new Anime();
        anime.setTitulo("Test anime");
        anime.setDataEstreia(LocalDate.now());
        anime.setDescricao("Anime para teste");

        //Act
        Anime savedAnime = animeRepository.save(anime);

        //Assert
        Assertions.assertThat(savedAnime).isNotNull();
        Assertions.assertThat(savedAnime.getId()).isGreaterThan(0);
        
    }

    @Test
    public void AnimeRepository_findAnimeByTitulo_ReturnsAnime(){
        Anime anime = new Anime();
        anime.setTitulo("Test");
        anime.setDataEstreia(LocalDate.now());
        anime.setDescricao("Anime para teste");
        anime = animeRepository.save(anime);

        Anime foundAnime = animeRepository.findAnimeByTitulo("Test").orElse(null);

        Assertions.assertThat(foundAnime).isNotNull();
        Assertions.assertThat(foundAnime.getId()).isEqualTo(anime.getId()); //Should return the same anime     

    }

    @Test
    public void AnimeRepository_findAnimeByCategoria_returnsAnimePage(){
        Categoria cat = new Categoria();
        cat.setNome("Isekai");
        cat.setDescricao("O personagem reencarna em outro mundo");

        Anime anime = new Anime();
        anime.setCategoria(List.of(cat));
        anime.setTitulo("Test");
        anime.setDataEstreia(LocalDate.now());
        anime.setDescricao("Anime para teste");
        
        Anime newAnime = animeRepository.save(anime);
        
        Pageable pageable = PageRequest.of(0, 10);
        Page<Anime> page = animeRepository.findByCategoriaNome("Isekai", pageable);

        Assertions.assertThat(page).isNotNull();

        List<Anime> animeList = page.getContent().stream().toList();

        Assertions.assertThat(animeList.size()).isGreaterThan(0);

    }

}
