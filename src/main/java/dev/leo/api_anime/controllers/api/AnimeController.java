package dev.leo.api_anime.controllers.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.leo.api_anime.domain.anime.Anime;
import dev.leo.api_anime.domain.anime.Temporada;
import dev.leo.api_anime.dto.PageDTO;
import dev.leo.api_anime.dto.ResponseWithIdDTO;
import dev.leo.api_anime.dto.anime.AnimeDto;
import dev.leo.api_anime.dto.anime.AnimeResponseDto;
import dev.leo.api_anime.dto.temporada.TemporadaDto;
import dev.leo.api_anime.dto.temporada.TemporadaResponseDto;
import dev.leo.api_anime.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(path = "/api/v1/animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {
    private final AnimeService animeService;
    
    @GetMapping(path = "/")
    public ResponseEntity<PageDTO<AnimeResponseDto>> search(
            @RequestParam(name = "cat", defaultValue = "", required = false) String cat,
            @RequestParam(name = "pageNum", defaultValue = "0", required = false) int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize
            ){
        if(cat.isBlank()){
            return new ResponseEntity<>(animeService.findAll(pageNum, pageSize),HttpStatus.OK);
        }else{
            return findAnimesByCategoria(cat, pageNum, pageSize);
        }
            
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<AnimeResponseDto> getAnimeById(@PathVariable Long id){
        Anime anime = animeService.findById(id);
        AnimeResponseDto result = AnimeResponseDto.toDto(anime);
        return new ResponseEntity<AnimeResponseDto>(result,HttpStatus.ACCEPTED);
    }

    
    @PostMapping(path = "/")
    public ResponseEntity<ResponseWithIdDTO> saveAnime(@RequestBody AnimeDto animeDto){
        Anime anime = animeService.save(animeDto);
        ResponseWithIdDTO response = new ResponseWithIdDTO(anime.getId());
        return new ResponseEntity<ResponseWithIdDTO>(response,HttpStatus.CREATED);
    }

    @PostMapping(path = "/{animeId}/categoria")
    public ResponseEntity<Void> atribuirCategoria(@RequestBody Long catId, @PathVariable Long animeId){
        animeService.atribuirCategoria(animeId, catId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> updateAnime(@RequestBody AnimeDto dto,@PathVariable Long id){
        animeService.update(dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteAnime(@PathVariable Long id){
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/temporadas/{id}")
    public ResponseEntity<List<TemporadaResponseDto>> listTemporadasAnime(@PathVariable Long id){
        List<TemporadaResponseDto> result = animeService.listTemporadasAnime(id);
        return new ResponseEntity<List<TemporadaResponseDto>>(result, HttpStatus.OK);
    }
    
    @PostMapping(path = "/{id}/temporadas/")
    public ResponseEntity<ResponseWithIdDTO> save(@RequestBody TemporadaDto dto,@PathVariable Long id){
        Temporada temp = animeService.addTemporada(dto,id);
        return new ResponseEntity<ResponseWithIdDTO>(new ResponseWithIdDTO(temp.getId()),HttpStatus.CREATED);
    }
    
    
    private ResponseEntity<PageDTO<AnimeResponseDto>> findAnimesByCategoria(String cat, int pageNum, int pageSize){
        PageDTO<AnimeResponseDto> response = animeService.filterByCategory(pageNum, pageSize, cat);
        return new ResponseEntity<PageDTO<AnimeResponseDto>>(response,HttpStatus.OK);
    }
    
}
