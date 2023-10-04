package br.ka.dto;

import br.ka.model.ItemProduto;

public record ItemProdutoDTO(
        Long idProduto,
        Integer quantidade,
        Double preco) {

    public static ItemProduto criaItemProduto(ItemProdutoDTO itemProdutoDTO) {
        ItemProduto itemProduto = new ItemProduto();
        itemProduto.setQuantidade(itemProdutoDTO.quantidade());
        itemProduto.setPreco(itemProdutoDTO.preco());
        return itemProduto;
    }
}