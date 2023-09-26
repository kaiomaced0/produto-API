package br.ka.service;

import jakarta.ws.rs.core.Response;
import java.util.List;
import br.ka.dto.CategoriaDTO;
import br.ka.dto.CategoriaUpdateDTO;
import br.ka.dto.responseDTO.CategoriaResponseDTO;

public interface CategoriaService {

    List<CategoriaResponseDTO> getAll();

    Response getId(Long id);

    Response insert(CategoriaDTO categoriaDTO);

    Response delete(Long id);

    Response update(CategoriaUpdateDTO categoriaUpdateDTO);
}
