package br.ka.service.impl;

import br.ka.model.EntityClass;
import br.ka.service.CategoriaService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import java.util.stream.Collectors;
import java.util.List;
import br.ka.repository.CategoriaRepository;
import br.ka.model.Categoria;
import br.ka.dto.CategoriaDTO;
import br.ka.dto.CategoriaUpdateDTO;
import br.ka.dto.responseDTO.CategoriaResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoriaServiceImpl implements CategoriaService {

    public static final Logger LOG = Logger.getLogger(CategoriaServiceImpl.class);

    @Inject
    CategoriaRepository repository;

    @Override
    public List<CategoriaResponseDTO> getAll() {
        try {
            LOG.info("Requisição Categoria.getAll()");
            return repository.listAll().stream().filter(EntityClass::getAtivo)
                    .map(CategoriaResponseDTO::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Categoria.getAll()");
            return null;
        }
    }

    @Override
    public Response getId(Long id) {
        try {
            LOG.info("Requisição Categoria.getId()");
            Categoria categoria = repository.findById(id);
            if(categoria != null && categoria.getAtivo()) {
                return Response.ok(new CategoriaResponseDTO(categoria)).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Categoria.getId()");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional
    public Response insert(CategoriaDTO categoriaDTO) {
        try {
            LOG.info("Requisição Categoria.insert()");
            Categoria categoria = CategoriaDTO.criaCategoria(categoriaDTO);
            repository.persist(categoria);
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Categoria.insert()");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional
    public Response update(CategoriaUpdateDTO categoriaUpdateDTO) {
        try {
            LOG.info("Requisição Categoria.update()");
            Categoria existingCategoria = repository.findById(categoriaUpdateDTO.id());
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Categoria.update()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        try {
            LOG.info("Requisição Categoria.delete()");
            repository.findById(id).setAtivo(false);
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Categoria.delete()");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
