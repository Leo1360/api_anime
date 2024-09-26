package dev.leo.api_anime.dto;

import dev.leo.api_anime.domain.anime.Categoria;

public record CategoriaDTO(String nome, String descricao) {

    public Categoria toCategoria(){
        Categoria cat = new Categoria();
        cat.setNome(this.nome);
        cat.setDescricao(this.descricao);
        return cat;
    }

    public static CategoriaDTO toDto(Categoria categoria){
        return new CategoriaDTO(categoria.getNome(), categoria.getDescricao());
    }

}
