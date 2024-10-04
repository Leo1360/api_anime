package dev.leo.api_anime.controllers.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.leo.api_anime.dto.CategoriaDTO;
import dev.leo.api_anime.dto.PageDTO;
import dev.leo.api_anime.dto.ResponseWithIdDTO;
import dev.leo.api_anime.service.CategoriaService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(path = "/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService service;

    @GetMapping(path = "/")
    public ResponseEntity<PageDTO<CategoriaDTO>> findAll(
            @RequestParam(name = "pageNum", defaultValue = "0", required = false) int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize
            ){
        PageDTO<CategoriaDTO> page = service.findAll(pageNum, pageSize);
        return new ResponseEntity<PageDTO<CategoriaDTO>>(page,HttpStatus.OK);
    }

    @GetMapping(path = "/categoria/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id){
        CategoriaDTO cat = CategoriaDTO.toDto(service.findById(id));
        return new ResponseEntity<CategoriaDTO>(cat, HttpStatus.OK);
    }

    @PostMapping(path = "/categoria")
    public ResponseEntity<ResponseWithIdDTO> save(@RequestBody CategoriaDTO dto){
        ResponseWithIdDTO response = new ResponseWithIdDTO(service.save(dto).getId());
        return new ResponseEntity<ResponseWithIdDTO>(response, HttpStatus.CREATED);
    }

    @PutMapping(path = "/categoria/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CategoriaDTO dto){
        service.updateCategoria(dto, id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/categoria/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
