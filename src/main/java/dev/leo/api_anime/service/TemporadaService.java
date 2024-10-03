package dev.leo.api_anime.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.leo.api_anime.domain.anime.Temporada;
import dev.leo.api_anime.dto.PageDTO;
import dev.leo.api_anime.dto.TemporadaDto;
import dev.leo.api_anime.exceptions.BadRequestException;
import dev.leo.api_anime.repository.TemporadaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TemporadaService {
    private final TemporadaRepository temporadaRepository;

    public Temporada save(TemporadaDto dto){
        Temporada temporada = dto.toTemporada();
        return temporadaRepository.save(temporada);
    }

    public Temporada findById(Long id){
        return temporadaRepository.findById(id).orElseThrow(() -> new BadRequestException("Temporada não localizada"));
    }

    public PageDTO<TemporadaDto> findAll(int pageNum, int pageSize){
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Temporada> page = temporadaRepository.findAll(pageable);
        List<TemporadaDto> tempList = page.getContent().stream().map(TemporadaDto::toDto).collect(Collectors.toList());
        return new PageDTO<TemporadaDto>(
            tempList,
            page.getSize(),
            page.getNumber(),
            page.getTotalPages(),
            page.getTotalElements(),
            page.isLast()
        );

    }

    public Temporada update(TemporadaDto dto, Long id){
        if(! temporadaRepository.existsById(id)){
            throw new BadRequestException("Temporada não localizada");
        }
        Temporada temporada = dto.toTemporada();
        temporada.setId(id);
        return temporadaRepository.save(temporada);

    }

    public void delete(Long id){
        if(! temporadaRepository.existsById(id)){
            throw new BadRequestException("Temporada não localizada");
        }
        temporadaRepository.deleteById(id);
    }

}
