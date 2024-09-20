package dev.leo.api_anime.repository;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import dev.leo.api_anime.domain.anime.Anime;

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

}
