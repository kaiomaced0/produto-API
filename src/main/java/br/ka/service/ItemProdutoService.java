package br.ka.service;

import jakarta.ws.rs.core.Response;
import java.util.List;
import br.ka.dto.ItemProdutoDTO;
import br.ka.dto.ItemProdutoUpdateDTO;
import br.ka.dto.responseDTO.ItemProdutoResponseDTO;

public interface ItemProdutoService {

    List<ItemProdutoResponseDTO> getAll();

    Response getId(Long id);

    Response insert(ItemProdutoDTO itemProdutoDTO);

    Response delete(Long id);

    Response update(ItemProdutoUpdateDTO itemProdutoUpdateDTO);
}
