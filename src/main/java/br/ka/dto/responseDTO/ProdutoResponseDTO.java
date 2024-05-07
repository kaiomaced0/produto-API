package br.ka.dto.responseDTO;

import br.ka.model.Produto;

import java.util.List;
import java.util.stream.Collectors;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        String descricao,
        Integer estoque,
        Double valor,
        Long idFornecedor,
        Long idMarca) {

    public ProdutoResponseDTO(Produto produto) {
        this(produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getEstoque(),
                produto.getValor(),
                produto.getFornecedor().getId(),
                produto.getMarca().getId()
                // A implementação abaixo assume que você tem um método ou uma maneira de obter uma lista de CategoriaResponseDTO a partir de uma lista de Categoria.
                //produto.getCategorias().stream().map(categoria -> new CategoriaResponseDTO(categoria)).collect(Collectors.toList())
        );
    }
}
