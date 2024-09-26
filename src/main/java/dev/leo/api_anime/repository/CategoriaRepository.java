package dev.leo.api_anime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.leo.api_anime.domain.anime.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long>{

}
