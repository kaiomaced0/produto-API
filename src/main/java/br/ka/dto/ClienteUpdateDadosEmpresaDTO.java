package br.ka.dto;

public record ClienteUpdateDadosEmpresaDTO(
        Long id,
        String nomeEmpresa,
        String cnpj) {
}
