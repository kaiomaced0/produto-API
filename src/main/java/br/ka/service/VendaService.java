package br.ka.service;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import java.util.List;
import br.ka.dto.responseDTO.VendaResponseDTO;
import br.ka.dto.VendaDTO;
import br.ka.dto.VendaUpdateDTO;

public interface VendaService {

    List<VendaResponseDTO> getAll();

    Response getId(Long id);

    Response insert(VendaDTO VendaDTO);

    Response update(VendaUpdateDTO VendaUpdateDTO);

    Response delete(@PathParam("id") Long id);
}
