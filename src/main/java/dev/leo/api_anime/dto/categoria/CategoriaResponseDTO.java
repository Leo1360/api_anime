package dev.leo.api_anime.dto.categoria;

import dev.leo.api_anime.domain.anime.Categoria;

public record CategoriaResponseDTO(Long id,String nome, String descricao) {

    public Categoria toCategoria(){
        Categoria cat = new Categoria();
        cat.setId(id);
        cat.setNome(this.nome);
        cat.setDescricao(this.descricao);
        return cat;
    }

    public static CategoriaResponseDTO toDto(Categoria categoria){
        return new CategoriaResponseDTO(categoria.getId(),categoria.getNome(), categoria.getDescricao());
    }

}
