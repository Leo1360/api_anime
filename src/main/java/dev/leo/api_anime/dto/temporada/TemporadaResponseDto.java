package dev.leo.api_anime.dto.temporada;

import java.time.LocalDate;

import dev.leo.api_anime.domain.anime.StatusTemporada;
import dev.leo.api_anime.domain.anime.Temporada;

public record TemporadaResponseDto(Long id,String titulo, String descricao, LocalDate lancamento, StatusTemporada status) {

    public static TemporadaResponseDto toDto(Temporada temporada){
        return new TemporadaResponseDto(temporada.getId(),temporada.getTitulo(), temporada.getDescricao(), temporada.getLancamento(), temporada.getStatus());
    }

    public Temporada toTemporada(){
        Temporada temporada = new Temporada();
        temporada.setId(id);
        temporada.setDescricao(descricao);
        temporada.setTitulo(titulo);
        temporada.setLancamento(lancamento);
        temporada.setStatus(status);
        return temporada;
    }

}
