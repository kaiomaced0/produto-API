package br.ka.dto;

import br.ka.model.ItemProduto;

public record ItemProdutoDTO(
        Long idProduto,
        Integer quantidade) {

    public static ItemProduto criaItemProduto(ItemProdutoDTO itemProdutoDTO) {
        ItemProduto itemProduto = new ItemProduto();
        itemProduto.setQuantidade(itemProdutoDTO.quantidade());
        return itemProduto;
    }
}