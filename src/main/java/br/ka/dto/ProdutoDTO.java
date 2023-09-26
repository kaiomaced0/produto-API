package br.ka.dto;

import br.ka.model.Produto;

import java.util.List;

public record ProdutoDTO(
        String nome,
        String descricao,
        Integer estoque,
        double valorCompra,
        double valorVenda,
        Long idFornecedor,
        Integer estoqueMinimo,
        Long idMarca,
        List<Long> idCategoria) {

    public static Produto criaProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.nome());
        produto.setDescricao(produtoDTO.descricao());
        produto.setEstoque(produtoDTO.estoque());
        produto.setValorCompra(produtoDTO.valorCompra());
        produto.setValorVenda(produtoDTO.valorVenda());
        produto.setEstoqueMinimo(produtoDTO.estoqueMinimo());
        // A implementação para setar a lista de categorias pode variar de acordo com sua implementação de backend.
        return produto;
    }
}
