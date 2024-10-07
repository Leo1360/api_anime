package dev.leo.api_anime.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.leo.api_anime.domain.anime.Categoria;
import dev.leo.api_anime.dto.PageDTO;
import dev.leo.api_anime.dto.categoria.CategoriaDTO;
import dev.leo.api_anime.repository.CategoriaRepository;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {
    @Mock
    CategoriaRepository categoriaRepository;
    
    @InjectMocks
    CategoriaService service;
    

    @Test
    void testFindAll() {
        Page<Categoria> page = Mockito.mock(Page.class);
        when(categoriaRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);

        PageDTO<CategoriaDTO> response = service.findAll(0, 10);

        Assertions.assertThat(response).isNotNull();
    }

    @Test
    void testFindById() {
        Categoria categoria = new Categoria();
        categoria.setDescricao("Descrição teste");
        categoria.setNome("Test");
        when(categoriaRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(categoria));

        Categoria newCat = service.findById(1l);

        Assertions.assertThat(newCat).isNotNull();
        Assertions.assertThat(newCat.getNome()).isNotBlank();

    }

}
