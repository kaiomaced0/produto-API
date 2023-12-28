package br.ka.service;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import java.util.List;
import br.ka.dto.responseDTO.FornecedorResponseDTO;
import br.ka.dto.FornecedorDTO;
import br.ka.dto.FornecedorUpdateDTO;

public interface FornecedorService {

    List<FornecedorResponseDTO> getAll();

    Response getId(Long id);

    Response insert(FornecedorDTO fornecedorDTO);

    Response update(FornecedorUpdateDTO dto);

    Response delete(@PathParam("id") Long id);
}
