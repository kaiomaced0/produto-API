package br.ka.dto;

import br.ka.model.Marca;
import java.util.ArrayList;

public record MarcaDTO(String nome) {

    public static Marca criaMarca(MarcaDTO marcaDTO) {
        Marca marca = new Marca();
        marca.setNome(marcaDTO.nome());
        marca.setFornecedores(new ArrayList<>()); // Inicializando a lista de fornecedores
        return marca;
    }
}

