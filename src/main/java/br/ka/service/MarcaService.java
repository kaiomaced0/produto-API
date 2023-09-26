package br.ka.service;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import br.ka.dto.MarcaUpdateDTO;
import br.ka.dto.MarcaDTO;
import br.ka.dto.responseDTO.MarcaResponseDTO;
import java.util.List;

public interface MarcaService {

    List<MarcaResponseDTO> getAll();

    Response getId(Long id);

    Response insert(MarcaDTO marcaDTO);

    Response delete(@PathParam("id") Long id);

    Response update(MarcaUpdateDTO marcaUpdateDTO);
}
