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

import dev.leo.api_anime.domain.anime.Temporada;
import dev.leo.api_anime.dto.PageDTO;
import dev.leo.api_anime.dto.ResponseWithIdDTO;
import dev.leo.api_anime.dto.TemporadaDto;
import dev.leo.api_anime.service.TemporadaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/temporadas")
@RequiredArgsConstructor
public class TemporadaController {
    private final TemporadaService service;

    @GetMapping(path = "/")
    public ResponseEntity<PageDTO<TemporadaDto>> findAll(
            @RequestParam(name = "pageNum", defaultValue = "0", required = false) int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize
            ){
        PageDTO<TemporadaDto> page = service.findAll(pageNum, pageSize);
        return new ResponseEntity<PageDTO<TemporadaDto>>(page, HttpStatus.OK);
    }

    @GetMapping(path = "/temporada/{id}")
    public ResponseEntity<TemporadaDto> findById(@PathVariable Long id){
        Temporada temp = service.findById(id);
        TemporadaDto result = TemporadaDto.toDto(temp);
        return new ResponseEntity<TemporadaDto>(result, HttpStatus.OK);
    }

    @PostMapping(path = "/temporada")
    public ResponseEntity<ResponseWithIdDTO> save(@RequestBody TemporadaDto dto){
        Temporada temp = service.save(dto);
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
