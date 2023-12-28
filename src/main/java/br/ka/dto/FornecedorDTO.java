package br.ka.dto;

import br.ka.model.Fornecedor;

import java.util.HashSet;
import java.util.List;

public record FornecedorDTO(
        String nome,
        String descricao,
        String cnpj,
        List<Long> idCategoria) {

    public static Fornecedor criaFornecedor(FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(fornecedorDTO.nome());
        fornecedor.setDescricao(fornecedorDTO.descricao());
        fornecedor.setCnpj(fornecedorDTO.cnpj());
        fornecedor.setCategorias(new HashSet<>());

        return fornecedor;
    }
}
