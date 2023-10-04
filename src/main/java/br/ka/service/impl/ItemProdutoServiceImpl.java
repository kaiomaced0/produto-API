package br.ka.service.impl;

import br.ka.service.ItemProdutoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import java.util.stream.Collectors;
import java.util.List;
import br.ka.repository.ItemProdutoRepository;
import br.ka.model.ItemProduto;
import br.ka.dto.ItemProdutoDTO;
import br.ka.dto.ItemProdutoUpdateDTO;
import br.ka.dto.responseDTO.ItemProdutoResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemProdutoServiceImpl implements ItemProdutoService {

    public static final Logger LOG = Logger.getLogger(ItemProdutoServiceImpl.class);

    @Inject
    ItemProdutoRepository repository;

    @Override
    public List<ItemProdutoResponseDTO> getAll() {
        return repository.listAll().stream()
                .map(ItemProdutoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Response getId(Long id) {
        ItemProduto item = repository.findById(id);
        if (item != null && item.getAtivo()) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    @Transactional
    public Response insert(ItemProdutoDTO itemProdutoDTO) {
        ItemProduto item = ItemProdutoDTO.criaItemProduto(itemProdutoDTO);
        repository.persist(item);
        return Response.ok(item).build();
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        ItemProduto item = repository.findById(id);
        if (item != null) {
            item.setAtivo(false);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    @Transactional
    public Response update(ItemProdutoUpdateDTO itemProdutoUpdateDTO) {
        ItemProduto item = repository.findById(itemProdutoUpdateDTO.id());
        if (item != null) {
            repository.persist(item);
            return Response.ok(item).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
