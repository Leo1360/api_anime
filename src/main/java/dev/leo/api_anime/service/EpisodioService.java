package dev.leo.api_anime.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.leo.api_anime.domain.anime.Episodio;
import dev.leo.api_anime.dto.PageDTO;
import dev.leo.api_anime.dto.episodio.EpisodioDto;
import dev.leo.api_anime.dto.episodio.EpisodioResponseDto;
import dev.leo.api_anime.exceptions.BadRequestException;
import dev.leo.api_anime.repository.EpisodioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EpisodioService {
    private final EpisodioRepository episodioRepository;

    public Episodio save(EpisodioDto dto, Long id){
        Episodio episodio = dto.toEpisodio();
        return episodioRepository.save(episodio);
    }

    public Episodio findById(Long id){
        return episodioRepository.findById(id).orElseThrow(() -> new BadRequestException("Epsodio não localizado"));

    }

    public PageDTO<EpisodioResponseDto> findAll(int pageNum, int pageSize){
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        Page<Episodio> page = episodioRepository.findAll(pageable);
        List<EpisodioResponseDto> dtoList = page.getContent().stream().map(EpisodioResponseDto::toDto).collect(Collectors.toList());
        return new PageDTO<EpisodioResponseDto>(
            dtoList,
            page.getNumber(),
            page.getSize(),
            page.getTotalPages(),
            page.getTotalElements(),
            page.isLast()
        );

    }

    public Episodio update(EpisodioDto dto, Long id){
        Episodio episodio = dto.toEpisodio();
        if(! episodioRepository.existsById(id)){
            throw new BadRequestException("Epsodio não localizado");
        }
        episodio.setId(id);
        return episodioRepository.save(episodio);
    }

    public void deleteById(Long id){
        if(! episodioRepository.existsById(id)){
            throw new BadRequestException("Epsodio não localizado");
        }
        episodioRepository.deleteById(id);
    }

}
