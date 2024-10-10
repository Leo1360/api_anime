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

import dev.leo.api_anime.domain.anime.Temporada;
import dev.leo.api_anime.dto.PageDTO;
import dev.leo.api_anime.dto.ResponseWithIdDTO;
import dev.leo.api_anime.dto.temporada.TemporadaDto;
import dev.leo.api_anime.dto.temporada.TemporadaResponseDto;
import dev.leo.api_anime.service.TemporadaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/temporadas")
@RequiredArgsConstructor
public class TemporadaController {
    private final TemporadaService service;

    @GetMapping(path = "/")
    public ResponseEntity<PageDTO<TemporadaResponseDto>> findAll(
            @RequestParam(name = "pageNum", defaultValue = "0", required = false) int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize
            ){
        PageDTO<TemporadaResponseDto> page = service.findAll(pageNum, pageSize);
        return new ResponseEntity<PageDTO<TemporadaResponseDto>>(page, HttpStatus.OK);
    }

    @GetMapping(path = "/temporada/{id}")
    public ResponseEntity<TemporadaResponseDto> findById(@PathVariable Long id){
        Temporada temp = service.findById(id);
        TemporadaResponseDto result = TemporadaResponseDto.toDto(temp);
        return new ResponseEntity<TemporadaResponseDto>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/anime/{id}")
    public ResponseEntity<List<TemporadaResponseDto>> listByAnime(@PathVariable Long id){
        List<TemporadaResponseDto> result = service.findTemporadasByAnimeId(id);
        return new ResponseEntity<List<TemporadaResponseDto>>(result, HttpStatus.OK);
    }

    @PostMapping(path = "/temporada/anime/{id}")
    public ResponseEntity<ResponseWithIdDTO> save(@RequestBody TemporadaDto dto,@PathVariable Long id){
        Temporada temp = service.save(dto,id);
        return new ResponseEntity<ResponseWithIdDTO>(new ResponseWithIdDTO(temp.getId()),HttpStatus.CREATED);
    }

    @PutMapping(path = "/temporada/{id}")
    public ResponseEntity<Void> update(@RequestBody TemporadaDto dto, @PathVariable Long id){
        service.update(dto, id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/temporada/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    

}
