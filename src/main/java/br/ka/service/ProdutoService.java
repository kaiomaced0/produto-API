package br.ka.service;

import br.ka.dto.CategoriaDTO;
import br.ka.dto.ProdutoUpdateDTO;
import br.ka.dto.responseDTO.ProdutoResponseDTO;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import java.util.List;
import br.ka.dto.ProdutoDTO;

public interface ProdutoService {

    public List<ProdutoResponseDTO> getAll();

    public Response getId(Long id);

    public Response insert(ProdutoDTO produto);

    public Response delete(@PathParam("id") Long id);

    public Response update(Long id, ProdutoUpdateDTO produtoDTO);

    public Response addCategoria(Long id, List<Long> categorias);

    public Response estoque();

    public Response addEstoque(Long id, Integer quantidae);
    public Response removeEstoque(Long id, Integer quantidae);
}

