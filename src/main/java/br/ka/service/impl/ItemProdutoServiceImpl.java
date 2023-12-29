package br.ka.service.impl;

import br.ka.service.ItemProdutoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import java.util.stream.Collectors;
import java.util.List;
import br.ka.repository.ItemProdutoRepository;
import br.ka.repository.ProdutoRepository;
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
    @Inject
    ProdutoRepository produtoRepository;

    @Override
    public List<ItemProdutoResponseDTO> getAll() {
        return repository.listAll().stream()
                .map(ItemProdutoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Response getId(Long id) {
        ItemProduto item = repository.findById(id);
        if (item.getAtivo()) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    @Transactional
    public Response insert(ItemProdutoDTO itemProdutoDTO) {
        try {
            ItemProduto item = ItemProdutoDTO.criaItemProduto(itemProdutoDTO);
            item.setProduto(produtoRepository.findById(itemProdutoDTO.idProduto()));
            item.setPreco(item.getProduto().getValor() * item.getQuantidade());
            repository.persist(item);
            return Response.ok(new ItemProdutoResponseDTO(item)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        try {
            ItemProduto item = repository.findById(id);

        if (item.getAtivo()) {
            item.setAtivo(false);
            return Response.ok().build();
        } else {
            throw new Exception();
        }
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
    }

    @Override
    @Transactional
    public Response update(ItemProdutoUpdateDTO itemProdutoUpdateDTO) {
        ItemProduto item = repository.findById(itemProdutoUpdateDTO.id());
        if (item.getAtivo()) {
            item.setProduto(produtoRepository.findById(itemProdutoUpdateDTO.idproduto()));
            item.setQuantidade(itemProdutoUpdateDTO.quantidade());
            item.setPreco(item.getQuantidade() * item.getProduto().getValor());
            return Response.ok(item).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
