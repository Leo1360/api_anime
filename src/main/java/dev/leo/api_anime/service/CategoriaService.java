package dev.leo.api_anime.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.leo.api_anime.domain.anime.Categoria;
import dev.leo.api_anime.dto.CategoriaDTO;
import dev.leo.api_anime.dto.PageDTO;
import dev.leo.api_anime.exceptions.BadRequestException;
import dev.leo.api_anime.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public Categoria save(CategoriaDTO dto){
        Categoria cat = dto.toCategoria();
        return categoriaRepository.save(cat);
    }


    public Categoria findById(Long id){
        return categoriaRepository.findById(id).orElseThrow(() -> new BadRequestException("Categoria não encontrada"));
    }

    public PageDTO<CategoriaDTO> findAll(int pageNum, int pageSize){
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Categoria> page = categoriaRepository.findAll(pageable);
        List<CategoriaDTO> catList = page.getContent().stream().map(CategoriaDTO::toDto).collect(Collectors.toList());
        return new PageDTO<CategoriaDTO>(
            catList,
            page.getNumber(),
            page.getSize(),
            page.getTotalPages(),
            page.getTotalElements(),
            page.isLast());
    }

    public Categoria updateCategoria(CategoriaDTO dto, Long id){
        Categoria cat = dto.toCategoria();
        if(! categoriaRepository.existsById(id)){
            throw new BadRequestException("Categoria não encontrada");
        }
        cat.setId(id);
        return categoriaRepository.save(cat);
    }

    public void delete(Long id){
        if(categoriaRepository.existsById(id)){
            categoriaRepository.deleteById(id);
            return;
        }
        throw new BadRequestException("Categoria não encontrada");
    }

}
