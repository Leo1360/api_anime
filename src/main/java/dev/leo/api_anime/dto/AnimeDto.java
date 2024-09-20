package dev.leo.api_anime.dto;

import java.time.LocalDate;

import dev.leo.api_anime.domain.anime.Anime;

public record AnimeDto(String titulo,String tituloOriginal, String descricao, LocalDate lancamento) {

    public Anime toAnime(){
        return Anime.builder().titulo(titulo).descricao(descricao).dataEstreia(lancamento).tituloOriginalRomanizado(tituloOriginal).build();
    }

}
