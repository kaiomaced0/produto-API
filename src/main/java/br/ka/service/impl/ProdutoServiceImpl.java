package br.ka.service.impl;
import br.ka.dto.ProdutoUpdateDTO;
import br.ka.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;
import br.ka.dto.ProdutoDTO;
import br.ka.dto.responseDTO.ProdutoResponseDTO;
import br.ka.model.Produto;
import br.ka.repository.ProdutoRepository;

@ApplicationScoped
public class ProdutoServiceImpl implements ProdutoService {

    public static final Logger LOG = Logger.getLogger(ProdutoServiceImpl.class);

    @Inject
    ProdutoRepository repository;

    @Override
    public List<ProdutoResponseDTO> getAll() {
        try {
            LOG.info("Requisição Produto.getAll()");
            return repository.listAll().stream()
                    .map(produto -> new ProdutoResponseDTO(produto))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Produto.getAll()", e);
            return null;
        }
    }

    @Override
    public Response getId(Long id) {
        try {
            LOG.info("Requisição Produto.getId()");
            Produto produto = repository.findById(id);
            if (produto != null) {
                return Response.ok(new ProdutoResponseDTO(produto)).build();
            } else {
                return Response.status(Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Produto.getId()", e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional
    public Response insert(ProdutoDTO produtoDTO) {
        try {
            LOG.info("Requisição Produto.insert()");
            Produto produto = ProdutoDTO.criaProduto(produtoDTO);
            repository.persist(produto);
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Produto.insert()", e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        try {
            LOG.info("Requisição Produto.delete()");
            if (repository.deleteById(id)) {
                return Response.ok().build();
            } else {
                return Response.status(Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Produto.delete()", e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional
    public Response update(ProdutoUpdateDTO produtoDTO) {
        try {
            LOG.info("Requisição Produto.update()");
            Produto produto = repository.findById(produtoDTO.id());

            if (produto != null) {
                produto.setNome(produtoDTO.nome());
                produto.setDescricao(produtoDTO.descricao());
                // Set other fields similarly
                repository.persist(produto);
                return Response.ok().build();
            } else {
                return Response.status(Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Produto.update()", e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
