package br.ka.dto;

import java.util.List;

public record ProdutoUpdateDTO(
        Long id,
        String nome,
        String descricao,
        int estoque,
        double valorCompra,
        double valorVenda,
        Long idFornecedor,
        int estoqueMinimo,
        Long idMarca,
        List<Long> idCategoria
) {}
