package dev.leo.api_anime.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.leo.api_anime.domain.anime.Anime;
import dev.leo.api_anime.dto.AnimeDto;
import dev.leo.api_anime.dto.AnimePageResponseDTO;
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

    public Anime save(AnimeDto animeDto){
        Anime anime = animeDto.toAnime();
        return animeRepo.save(anime);
    }

    public AnimePageResponseDTO findAll(int pageNum, int pageSize){
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Anime> animePage = animeRepo.findAll(pageable);
        List<AnimeDto> animeList = animePage.getContent().stream().map(AnimeDto::toDto).collect(Collectors.toList());

        return new AnimePageResponseDTO(
            animeList,
            animePage.getNumber(),
            animePage.getSize(),
            animePage.getTotalPages(),
            animePage.getTotalElements(),
            animePage.isLast()
            );
    }

    public AnimePageResponseDTO filterByCategory(int pageNum, int pageSize, String categoria){
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Anime> animePage = animeRepo.findByCategoriaNome(categoria,pageable);
        List<AnimeDto> animeList = animePage.getContent().stream().map(AnimeDto::toDto).collect(Collectors.toList());
        
        return new AnimePageResponseDTO(
            animeList,
            animePage.getNumber(),
            animePage.getSize(),
            animePage.getTotalPages(),
            animePage.getTotalElements(),
            animePage.isLast()
            );
    }
}
