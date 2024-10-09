package dev.leo.api_anime.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.leo.api_anime.domain.anime.Episodio;
import dev.leo.api_anime.dto.PageDTO;
import dev.leo.api_anime.dto.episodio.EpisodioResponseDto;
import dev.leo.api_anime.repository.EpisodioRepository;

@ExtendWith(MockitoExtension.class)
public class EpisodioServiceTest {
    @Mock
    EpisodioRepository episodioRepository;

    @InjectMocks
    EpisodioService episodioService;

    @Test
    void EpisodioService_findAll_returnPageDtoEpisodioDto(){
        Page<Episodio> page = Mockito.mock(Page.class);
        when(episodioRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);

        PageDTO<EpisodioResponseDto> result = episodioService.findAll(0, 10);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.itens()).isNotNull();
    }

    @Test
    void EpisodioService_findById_returnsEpisodioDto(){
        Episodio ep = Mockito.mock(Episodio.class);
        when(episodioRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(ep));

        Episodio newEpisodio = episodioService.findById(0l);

        Assertions.assertThat(newEpisodio).isNotNull();
    }

}
