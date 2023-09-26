package br.ka.dto;

import java.util.List;

public record FornecedorUpdateDTO(
        Long id,
        String nome,
        String descricao,
        String cnpj,
        List<Long> idCategoria
) {}
