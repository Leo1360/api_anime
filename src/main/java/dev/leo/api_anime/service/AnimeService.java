package dev.leo.api_anime.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.leo.api_anime.domain.anime.Anime;
import dev.leo.api_anime.domain.anime.Categoria;
import dev.leo.api_anime.domain.anime.Temporada;
import dev.leo.api_anime.dto.PageDTO;
import dev.leo.api_anime.dto.anime.AnimeDto;
import dev.leo.api_anime.dto.anime.AnimeResponseDto;
import dev.leo.api_anime.dto.temporada.TemporadaDto;
import dev.leo.api_anime.dto.temporada.TemporadaResponseDto;
import dev.leo.api_anime.exceptions.BadRequestException;
import dev.leo.api_anime.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository animeRepo;
    private final TemporadaService temporadaService;
    private final CategoriaService categoriaService;

    public Anime findById(Long id){
        return animeRepo.findById(id).orElseThrow(() -> new BadRequestException("Anime não localizado"));

    }

    public Anime save(AnimeDto animeDto){
        Anime anime = animeDto.toAnime();
        return animeRepo.save(anime);
    }

    public PageDTO<AnimeResponseDto> findAll(int pageNum, int pageSize){
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Anime> animePage = animeRepo.findAll(pageable);
        List<AnimeResponseDto> animeList = animePage.getContent().stream().map(AnimeResponseDto::toDto).collect(Collectors.toList());

        return new PageDTO<AnimeResponseDto>(
            animeList,
            animePage.getNumber(),
            animePage.getSize(),
            animePage.getTotalPages(),
            animePage.getTotalElements(),
            animePage.isLast()
            );
    }

    public PageDTO<AnimeResponseDto> filterByCategory(int pageNum, int pageSize, String categoria){
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Anime> animePage = animeRepo.findByCategoriaNome(categoria,pageable);
        List<AnimeResponseDto> animeList = animePage.getContent().stream().map(AnimeResponseDto::toDto).collect(Collectors.toList());
        
        return new PageDTO<AnimeResponseDto>(
            animeList,
            animePage.getNumber(),
            animePage.getSize(),
            animePage.getTotalPages(),
            animePage.getTotalElements(),
            animePage.isLast()
            );
    }

    public Anime update(AnimeDto dto, Long id){
        if(! animeRepo.existsById(id)){
            throw new BadRequestException("Anime não localizado");
        }
        Anime anime = dto.toAnime();
        anime.setId(id);
        return animeRepo.save(anime);
    }


    public void delete(Long id){
        if(! animeRepo.existsById(id)){
            throw new BadRequestException("Anime não localizado");
        }
        animeRepo.deleteById(id);
    }

    public List<TemporadaResponseDto> listTemporadasAnime(Long id) {
        Anime anime = findById(id);
        return anime.getTemporadas().stream().map(TemporadaResponseDto::toDto).collect(Collectors.toList());
    }

    public Temporada addTemporada(TemporadaDto dto, Long id) {
        Anime anime = animeRepo.getReferenceById(id);

        Temporada temp = dto.toTemporada();
        temp = temporadaService.save(dto);

        anime.getTemporadas().add(temp);
        animeRepo.save(anime);
        return temp;
    }

    public void atribuirCategoria(Long animeId, Long catId) {
        Anime anime = animeRepo.getReferenceById(animeId);
        Categoria cat = categoriaService.findById(catId);

        anime.getCategoria().add(cat);

        animeRepo.save(anime);
    }

}
