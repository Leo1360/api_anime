package dev.leo.api_anime.dto.anime;

import java.time.LocalDate;

import dev.leo.api_anime.domain.anime.Anime;

public record AnimeResponseDto(Long id, String titulo,String tituloOriginal, String descricao, LocalDate lancamento) {

    public static AnimeResponseDto toDto(Anime anime){
        return new AnimeResponseDto(
            anime.getId(),
            anime.getTitulo(),
            anime.getTituloOriginalRomanizado(),
            anime.getDescricao(),
            anime.getDataEstreia()
        );
    }

    public Anime toAnime(){
        return Anime.builder().titulo(titulo).descricao(descricao).dataEstreia(lancamento).tituloOriginalRomanizado(tituloOriginal).id(id).build();
    }

}
