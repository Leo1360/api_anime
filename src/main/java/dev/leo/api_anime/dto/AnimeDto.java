package dev.leo.api_anime.dto;

import java.time.LocalDate;

public record AnimeDto(String titulo, String descricao, LocalDate lancamento) {

}
