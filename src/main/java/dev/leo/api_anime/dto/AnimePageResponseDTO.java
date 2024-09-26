package dev.leo.api_anime.dto;

import java.util.List;

public record AnimePageResponseDTO(
    List<AnimeDto> animes,
    int numPagina,
    int tamanhoPagina,
    int totalPaginas,
    Long totalElementos,
    boolean ultimaPagina

) {

}
