package br.ka.dto;


import java.util.List;

public record VendaDTO(
        Long idCliente,
        String observacao,
        List<Long> idItemProdutos) {
}
