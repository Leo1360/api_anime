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

import dev.leo.api_anime.domain.anime.Temporada;
import dev.leo.api_anime.dto.PageDTO;
import dev.leo.api_anime.dto.temporada.TemporadaResponseDto;
import dev.leo.api_anime.repository.TemporadaRepository;

@ExtendWith(MockitoExtension.class)
public class TemporadaServiceTest {

    @Mock
    TemporadaRepository temporadaRepository;

    @InjectMocks
    TemporadaService service;

    @Test
    void TemporadaService_testFindAll_returnsPageDtoTemporadaDto() {
        Page<Temporada> page = Mockito.mock(Page.class);
        when(temporadaRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);

        PageDTO<TemporadaResponseDto> temporadas = service.findAll(0, 10);

        Assertions.assertThat(temporadas).isNotNull();
    }

    @Test
    void TemporadaService_testFindById_returnsTemporada() {
        Temporada temporada = Mockito.mock(Temporada.class);
        when(temporadaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(temporada));

        Temporada newTemporada = service.findById(0l);

        Assertions.assertThat(newTemporada).isNotNull();

    }
}
