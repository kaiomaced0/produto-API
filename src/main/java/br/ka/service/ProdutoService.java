package br.ka.service;

import br.ka.dto.ProdutoUpdateDTO;
import br.ka.dto.responseDTO.ProdutoResponseDTO;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import java.util.List;
import br.ka.dto.ProdutoDTO;
import br.ka.dto.responseDTO.ProdutoResponseDTO;

public interface ProdutoService {

    public List<ProdutoResponseDTO> getAll();

    public Response getId(Long id);

    public Response insert(ProdutoDTO produto);

    public Response delete(@PathParam("id") Long id);

    public Response update(ProdutoUpdateDTO produtoDTO);
}

