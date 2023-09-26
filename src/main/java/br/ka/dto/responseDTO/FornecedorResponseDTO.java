package br.ka.dto.responseDTO;

import br.ka.model.Fornecedor;
import java.util.List;
import java.util.stream.Collectors;

public record FornecedorResponseDTO(
        Long id,
        String nome,
        String descricao,
        String cnpj,
        List<CategoriaResponseDTO> categorias) {

    public FornecedorResponseDTO(Fornecedor fornecedor) {
        this(fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getDescricao(),
                fornecedor.getCnpj(),
                fornecedor.getCategorias().stream()
                        .map(CategoriaResponseDTO::new)
                        .collect(Collectors.toList())
                 );
    }
}
