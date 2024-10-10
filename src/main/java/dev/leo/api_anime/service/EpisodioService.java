package dev.leo.api_anime.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.leo.api_anime.domain.anime.Episodio;
import dev.leo.api_anime.domain.anime.Temporada;
import dev.leo.api_anime.dto.PageDTO;
import dev.leo.api_anime.dto.episodio.EpisodioDto;
import dev.leo.api_anime.dto.episodio.EpisodioResponseDto;
import dev.leo.api_anime.exceptions.BadRequestException;
import dev.leo.api_anime.repository.EpisodioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EpisodioService {
    private final EpisodioRepository episodioRepository;
    private final TemporadaService temporadaService;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Episodio save(EpisodioDto dto, Long id){
        Episodio episodio = dto.toEpisodio();
        episodio = episodioRepository.save(episodio);
        
        Temporada temp = entityManager.getReference(Temporada.class, id);
        temp.getEpsodios().add(episodio);
        entityManager.persist(temp);

        return episodio;
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

    public List<EpisodioResponseDto> listEpisodioByTemporada(Long id) {
        Temporada temp = temporadaService.findById(id);
        return temp.getEpsodios().stream().map(EpisodioResponseDto::toDto).collect(Collectors.toList());
    }

}
