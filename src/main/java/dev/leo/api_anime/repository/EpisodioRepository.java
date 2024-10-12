package dev.leo.api_anime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.leo.api_anime.domain.anime.Episodio;

@Repository
public interface EpisodioRepository extends JpaRepository<Episodio,Long>{

}
