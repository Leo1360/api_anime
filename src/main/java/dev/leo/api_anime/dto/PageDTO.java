package dev.leo.api_anime.dto;

import java.util.List;

public record PageDTO<T>(
    List<T> itens,
    int numPagina,
    int tamanhoPagina,
    int totalPaginas,
    Long totalElementos,
    boolean ultimaPagina
    ) {

}