package dev.leo.api_anime.dto;

import java.time.LocalDate;

import dev.leo.api_anime.domain.anime.StatusTemporada;
import dev.leo.api_anime.domain.anime.Temporada;

public record TemporadaDto(String titulo, String descricao, LocalDate lancamento, StatusTemporada status) {

    public static TemporadaDto toDto(Temporada temporada){
        return new TemporadaDto(temporada.getTitulo(), temporada.getDescricao(), temporada.getLancamento(), temporada.getStatus());
    }

    public Temporada toTemporada(){
        Temporada temporada = new Temporada();
        temporada.setDescricao(descricao);
        temporada.setTitulo(titulo);
        temporada.setLancamento(lancamento);
        temporada.setStatus(status);
        return temporada;
    }

}
