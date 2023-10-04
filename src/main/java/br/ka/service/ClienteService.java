package br.ka.service;

import jakarta.ws.rs.core.Response;
import java.util.List;
import br.ka.dto.ClienteDTO;
import br.ka.dto.ClienteUpdateDTO;
import br.ka.dto.ClienteUpdateDadosClienteDTO;
import br.ka.dto.ClienteUpdateDadosEmpresaDTO;
import br.ka.dto.ClienteUpdateEnderecoDTO;
import br.ka.dto.responseDTO.ClienteResponseDTO;

public interface ClienteService {

    List<ClienteResponseDTO> getAll();

    Response getId(Long id);

    Response insert(ClienteDTO clienteDTO);

    Response update(ClienteUpdateDTO clienteUpdateDTO);

    Response updateDadosEmpresa(ClienteUpdateDadosEmpresaDTO clienteUpdateDadosEmpresaDTO);

    Response updateDadosCliente(ClienteUpdateDadosClienteDTO clienteUpdateDadosClienteDTO);

    Response updateEndereco(ClienteUpdateEnderecoDTO clienteUpdateEnderecoDTO);

    Response delete(Long id);
}
