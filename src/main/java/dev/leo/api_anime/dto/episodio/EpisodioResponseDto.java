package dev.leo.api_anime.dto.episodio;

import dev.leo.api_anime.domain.anime.Episodio;

public record EpisodioResponseDto(Long id,String titulo, String descricao, long duracao) {

    public static EpisodioResponseDto toDto(Episodio episodio){
        return new EpisodioResponseDto(episodio.getId(),episodio.getTitulo(), episodio.getDescricao(),episodio.getDuracao());
    }

    public Episodio toEpisodio(){
        Episodio episodio = new Episodio();
        episodio.setId(id);
        episodio.setDescricao(descricao);
        episodio.setDuracao(duracao);
        episodio.setTitulo(titulo);
        return episodio;
    }

}
