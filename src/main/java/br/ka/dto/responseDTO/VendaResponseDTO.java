package br.ka.dto.responseDTO;

import br.ka.model.Venda;
import java.util.List;
import java.util.stream.Collectors;

public record VendaResponseDTO(
        Long id,
        Long idCliente,
        String observacao,
        Double valorTotal,
        List<ItemProdutoResponseDTO> idItemProdutos) {

    public VendaResponseDTO(Venda venda) {
        this(venda.getId(), venda.getCliente().getId(), venda.getObservacao(), venda.getValorTotal(), venda.getItemProdutos().stream().map(ItemProdutoResponseDTO::new).collect(Collectors.toList()));
    }
}
