package br.ka.dto;

import java.util.List;

public record VendaUpdateDTO(
        Long id,
        Long idCliente,
        String observacao,
        List<Long> idItemProdutos
) {}
