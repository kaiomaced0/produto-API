package br.ka.service;

import jakarta.ws.rs.core.Response;
import java.util.List;
import br.ka.dto.CidadeDTO;
import br.ka.dto.CidadeUpdateDTO;
import br.ka.dto.responseDTO.CidadeResponseDTO;

public interface CidadeService {

    List<CidadeResponseDTO> getAll();

    Response getId(Long id);

    Response insert(CidadeDTO cidadeDTO);

    Response delete(Long id);

    Response update(CidadeUpdateDTO cidadeUpdateDTO);
}
