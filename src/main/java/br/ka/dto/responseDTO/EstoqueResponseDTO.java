package br.ka.dto.responseDTO;

import br.ka.model.Produto;

import java.time.LocalDateTime;

public record EstoqueResponseDTO(
        Long id,
        String nome,
        LocalDateTime localDateTime,
        Integer estoque
) {
    public EstoqueResponseDTO(Produto p){
        this(p.getId(), p.getNome(), p.getDataInclusao(), p.getEstoque());
    }
}
