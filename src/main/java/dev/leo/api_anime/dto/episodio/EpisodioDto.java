package dev.leo.api_anime.dto.episodio;

import dev.leo.api_anime.domain.anime.Episodio;

public record EpisodioDto(String titulo, String descricao, long duracao, Integer numero) {

    public static EpisodioDto toDto(Episodio episodio){
        return new EpisodioDto(episodio.getTitulo(), episodio.getDescricao(),episodio.getDuracao(), episodio.getNumero());
    }

    public Episodio toEpisodio(){
        Episodio episodio = new Episodio();
        episodio.setDescricao(descricao);
        episodio.setDuracao(duracao);
        episodio.setTitulo(titulo);
        episodio.setNumero(numero);
        return episodio;
    }

}
