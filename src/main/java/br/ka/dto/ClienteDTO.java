package br.ka.dto;
import br.ka.model.Cliente;
import br.ka.model.Cidade;

public record ClienteDTO(
        String nomeEmpresa,
        String cnpj,
        String nomeCliente,
        String cpfCliente,
        CidadeDTO cidadeDTO,
        String endereco) {

    public static Cliente criaCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNomeEmpresa(clienteDTO.nomeEmpresa());
        cliente.setCnpj(clienteDTO.cnpj());
        cliente.setNomeCliente(clienteDTO.nomeCliente());
        cliente.setCpfCliente(clienteDTO.cpfCliente());
        cliente.setCidade(CidadeDTO.criaCidade(clienteDTO.cidadeDTO()));
        cliente.setEndereco(clienteDTO.endereco());
        return cliente;
    }
}
