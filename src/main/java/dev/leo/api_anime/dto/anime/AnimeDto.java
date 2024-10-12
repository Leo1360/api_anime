package dev.leo.api_anime.dto.anime;

import java.time.LocalDate;

import dev.leo.api_anime.domain.anime.Anime;

public record AnimeDto(String titulo,String tituloOriginal, String descricao, LocalDate lancamento) {

    public static AnimeDto toDto(Anime anime){
        return new AnimeDto(
            anime.getTitulo(),
            anime.getTituloOriginalRomanizado(),
            anime.getDescricao(),
            anime.getDataEstreia()  
        );
    }

    public Anime toAnime(){
        return Anime.builder().titulo(titulo).descricao(descricao).dataEstreia(lancamento).tituloOriginalRomanizado(tituloOriginal).build();
    }

}
