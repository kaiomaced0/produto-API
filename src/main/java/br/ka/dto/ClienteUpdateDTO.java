package br.ka.dto;
import br.ka.model.Cliente;
import br.ka.model.Cidade;

public record ClienteUpdateDTO(
        Long id,
        String nomeEmpresa,
        String cnpj,
        String nomeCliente,
        String cpfCliente,
        CidadeDTO cidadeDTO,
        String endereco) {
}
