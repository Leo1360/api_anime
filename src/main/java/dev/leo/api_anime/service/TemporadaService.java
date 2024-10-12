package dev.leo.api_anime.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.leo.api_anime.domain.anime.Episodio;
import dev.leo.api_anime.domain.anime.Temporada;
import dev.leo.api_anime.dto.PageDTO;
import dev.leo.api_anime.dto.episodio.EpisodioDto;
import dev.leo.api_anime.dto.episodio.EpisodioResponseDto;
import dev.leo.api_anime.dto.temporada.TemporadaDto;
import dev.leo.api_anime.dto.temporada.TemporadaResponseDto;
import dev.leo.api_anime.exceptions.BadRequestException;
import dev.leo.api_anime.repository.TemporadaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TemporadaService {
    private final TemporadaRepository temporadaRepository;
    private final EpisodioService episodioService;

    public Temporada save(TemporadaDto dto){
        Temporada temporada = dto.toTemporada();
        return temporadaRepository.save(temporada);
    }

    public Temporada findById(Long id){
        return temporadaRepository.findById(id).orElseThrow(() -> new BadRequestException("Temporada não localizada"));
    }

    public PageDTO<TemporadaResponseDto> findAll(int pageNum, int pageSize){
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Temporada> page = temporadaRepository.findAll(pageable);
        List<TemporadaResponseDto> tempList = page.getContent().stream().map(TemporadaResponseDto::toDto).collect(Collectors.toList());
        return new PageDTO<TemporadaResponseDto>(
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

    public Episodio addEpisodio(EpisodioDto dto, Long id) {
        Episodio ep = dto.toEpisodio();
        ep = episodioService.save(dto, id);
        
        Temporada temp = temporadaRepository.getReferenceById(id);
        temp.getEpsodios().add(ep);
        temporadaRepository.save(temp);

        return ep;
    }

    public List<EpisodioResponseDto> getEpisodios(Long id) {
        Temporada temp = findById(id);
        return temp.getEpsodios().stream().map(EpisodioResponseDto::toDto).collect(Collectors.toList());
    }

}
