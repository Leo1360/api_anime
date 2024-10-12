package dev.leo.api_anime.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.leo.api_anime.domain.anime.Anime;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long>{
    Optional<Anime> findAnimeByTitulo(String titulo);
    Page<Anime> findByCategoriaNomeContaining(String categoria, Pageable pageable);
}
