package dev.leo.api_anime.service;

import org.springframework.stereotype.Service;

import dev.leo.api_anime.domain.anime.Anime;
import dev.leo.api_anime.dto.AnimeDto;
import dev.leo.api_anime.exceptions.BadRequestException;
import dev.leo.api_anime.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository animeRepo;

    public Anime findById(Long id){
        return animeRepo.findById(id).orElseThrow(() -> new BadRequestException("Anime não localizado"));

    }

    public void save(AnimeDto animeDto){
        Anime anime = animeDto.toAnime();
        animeRepo.save(anime);
    }

}
