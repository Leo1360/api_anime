package dev.leo.api_anime.controllers.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.leo.api_anime.dto.PageDTO;
import dev.leo.api_anime.dto.episodio.EpisodioDto;
import dev.leo.api_anime.dto.episodio.EpisodioResponseDto;
import dev.leo.api_anime.service.EpisodioService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping(path = "/api/v1/episodios")
@RequiredArgsConstructor
public class EpisodioController {
    private final EpisodioService service;

    @GetMapping("/{id}")
    public ResponseEntity<EpisodioResponseDto> findById(@PathVariable Long id) {
        EpisodioResponseDto result = EpisodioResponseDto.toDto(service.findById(id));
        return new ResponseEntity<EpisodioResponseDto>(result,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<PageDTO<EpisodioResponseDto>> findAll(
            @RequestParam(name = "pageNum", defaultValue = "0", required = false) int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize
            ){
        PageDTO<EpisodioResponseDto> result = service.findAll(pageNum, pageSize);
        return new ResponseEntity<PageDTO<EpisodioResponseDto>>(result, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> update(@RequestBody EpisodioDto dto, @PathVariable Long id){
        service.update(dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
