package br.ka.dto.responseDTO;

import br.ka.model.Cidade;
import br.ka.model.Estado;

public record CidadeResponseDTO(
        Long id,
        Estado estado,
        String nome
) {
    public CidadeResponseDTO(Cidade cidade) {
        this(cidade.getId(), cidade.getEstado(), cidade.getNome());
    }
}
