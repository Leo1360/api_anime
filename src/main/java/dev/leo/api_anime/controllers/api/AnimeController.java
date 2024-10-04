package dev.leo.api_anime.controllers.api;

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
import dev.leo.api_anime.dto.AnimeDto;
import dev.leo.api_anime.dto.AnimePageResponseDTO;
import dev.leo.api_anime.dto.ResponseWithIdDTO;
import dev.leo.api_anime.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(path = "/api/animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {
    private final AnimeService animeService;
    
    @GetMapping(path = "/")
    public ResponseEntity<AnimePageResponseDTO> findAll(
            @RequestParam(name = "pageNum", defaultValue = "0", required = false) int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize
            ){
        return new ResponseEntity<>(animeService.findAll(pageNum, pageSize),HttpStatus.OK);
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> getAnimeById(@PathVariable Long id){
        Anime anime = animeService.findById(id);
        return new ResponseEntity<Anime>(anime,HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/anime")
    public ResponseEntity<ResponseWithIdDTO> saveAnime(@RequestBody AnimeDto animeDto){
        Anime anime = animeService.save(animeDto);
        ResponseWithIdDTO response = new ResponseWithIdDTO(anime.getId());
        return new ResponseEntity<ResponseWithIdDTO>(response,HttpStatus.CREATED);
    }

    @PutMapping(path = "/anime/{id}")
    public ResponseEntity<Void> updateAnime(@RequestBody AnimeDto dto,@PathVariable Long id){
        animeService.update(dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/anime/{id}")
    public ResponseEntity<Void> deleteAnime(@PathVariable Long id){
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
