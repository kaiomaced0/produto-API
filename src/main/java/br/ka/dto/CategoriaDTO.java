package br.ka.dto;
import br.ka.model.Categoria;

public record CategoriaDTO(
        String nome) {

    public static Categoria criaCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaDTO.nome());
        return categoria;
    }
}
