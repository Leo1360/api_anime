package dev.leo.api_anime.dto.episodio;

import dev.leo.api_anime.domain.anime.Episodio;

public record EpisodioResponseDto(Long id,String titulo, String descricao, long duracao, Integer numero) {

    public static EpisodioResponseDto toDto(Episodio episodio){
        return new EpisodioResponseDto(episodio.getId(),episodio.getTitulo(), episodio.getDescricao(),episodio.getDuracao(),episodio.getNumero());
    }

    public Episodio toEpisodio(){
        Episodio episodio = new Episodio();
        episodio.setId(id);
        episodio.setDescricao(descricao);
        episodio.setDuracao(duracao);
        episodio.setTitulo(titulo);
        episodio.setNumero(numero);
        return episodio;
    }

}
