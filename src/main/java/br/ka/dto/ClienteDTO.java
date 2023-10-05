package br.ka.dto;
import br.ka.model.Cliente;

public record ClienteDTO(
        String nomeEmpresa,
        String cnpj,
        String nomeCliente,
        String cpfCliente,
        Long idCidade,
        String endereco) {

    public static Cliente criaCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNomeEmpresa(clienteDTO.nomeEmpresa());
        cliente.setCnpj(clienteDTO.cnpj());
        cliente.setNomeCliente(clienteDTO.nomeCliente());
        cliente.setCpfCliente(clienteDTO.cpfCliente());
        cliente.setEndereco(clienteDTO.endereco());
        return cliente;
    }
}
