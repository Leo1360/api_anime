package dev.leo.api_anime.service;

import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.leo.api_anime.domain.anime.Anime;
import dev.leo.api_anime.dto.AnimeDto;
import dev.leo.api_anime.dto.AnimePageResponseDTO;
import dev.leo.api_anime.repository.AnimeRepository;

@ExtendWith(MockitoExtension.class)
public class AnimeServiceTest {

    @Mock
    private AnimeRepository animeRepository;

    @InjectMocks
    private AnimeService animeService;

    @Test
    public void AnimeService_save_returnsAnime(){
        AnimeDto dto = new AnimeDto("Teste anime","Teste anime","Anime para teste", LocalDate.now());
        Anime anime = dto.toAnime();

        when(animeRepository.save(Mockito.any(Anime.class))).thenReturn(anime); //When the repo.save is called passing an Anime mockito will forcefully return our anime variable

        Anime newAnime = animeService.save(dto);
      
        Assertions.assertThat(newAnime).isNotNull();
        
    }

    @Test
    public void AnimeService_findAll_returnsAnimePageResponseDTO(){
        Page<Anime> page = Mockito.mock(Page.class);

        when(animeRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);

        AnimePageResponseDTO newAnimePageDTO =  animeService.findAll(0, 10);

        Assertions.assertThat(newAnimePageDTO).isNotNull();

    }

    @Test
    public void AnimeService_filterByCategoria_returnsAnimePageResponseDTO(){
        Page<Anime> page = Mockito.mock(Page.class);
        when(animeRepository.findByCategoriaNome(Mockito.any(String.class), Mockito.any(Pageable.class))).thenReturn(page);
        
        AnimePageResponseDTO newAnimePageResponseDTO = animeService.filterByCategory(0, 10, "Isekai");
        
        Assertions.assertThat(newAnimePageResponseDTO).isNotNull();

    }

}
