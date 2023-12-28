package br.ka.dto;

import java.util.List;

public record ProdutoUpdateDTO(
        Long id,
        String nome,
        String descricao,
        Integer estoque,
        Double custo,
        Double valor,
        Long idFornecedor,
        Integer estoqueMinimo,
        Long idMarca,
        List<Long> idCategoria
) {}
