package dev.leo.api_anime.controllers.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.leo.api_anime.domain.anime.Anime;
import dev.leo.api_anime.dto.AnimeDto;
import dev.leo.api_anime.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(path = "/api/animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {
    private final AnimeService animeService;
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> getAnimeById(@PathVariable Long id){
        Anime anime = animeService.findById(id);
        return new ResponseEntity<Anime>(anime,HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/anime")
    public ResponseEntity<Void> saveAnime(@RequestBody AnimeDto animeDto){
        animeService.save(animeDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
