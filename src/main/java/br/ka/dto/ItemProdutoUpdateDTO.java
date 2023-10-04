package br.ka.dto;


public record ItemProdutoUpdateDTO(
        Long id,
        Long  idproduto,
        Integer quantidade,
        Double preco) {}
