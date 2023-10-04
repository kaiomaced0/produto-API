package br.ka.service.impl;

import br.ka.model.EntityClass;
import br.ka.model.Estado;
import br.ka.service.CidadeService;
import br.ka.repository.CidadeRepository;
import br.ka.model.Cidade;
import br.ka.dto.CidadeDTO;
import br.ka.dto.CidadeUpdateDTO;
import br.ka.dto.responseDTO.CidadeResponseDTO;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CidadeServiceImpl implements CidadeService {

    public static final Logger LOG = Logger.getLogger(CidadeServiceImpl.class);

    @Inject
    CidadeRepository repository;

    @Override
    public List<CidadeResponseDTO> getAll() {
        try {
            LOG.info("Requisição Cidade.getAll()");
            return repository.listAll().stream().filter(EntityClass::getAtivo)
                    .map(CidadeResponseDTO::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cidade.getAll()", e);
            return null;
        }
    }

    @Override
    public Response getId(Long id) {
        try {
            LOG.info("Requisição Cidade.getId()");
            Cidade cidade = repository.findById(id);
            if (cidade != null) {
                return Response.ok(new CidadeResponseDTO(cidade)).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cidade.getId()", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional
    public Response insert(CidadeDTO cidadeDTO) {
        try {
            LOG.info("Requisição Cidade.insert()");
            Cidade cidade = CidadeDTO.criaCidade(cidadeDTO);
            cidade.setEstado(Estado.valueOf(cidadeDTO.estado()));
            repository.persist(cidade);
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cidade.insert()", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional
    public Response update(CidadeUpdateDTO cidadeUpdateDTO) {
        try {
            LOG.info("Requisição Cidade.update()");
            Cidade existingCidade = repository.findById(cidadeUpdateDTO.id());
            if (existingCidade != null) {
                existingCidade.setNome(cidadeUpdateDTO.nome());
                existingCidade.setEstado(Estado.valueOf(cidadeUpdateDTO.estadoId()));
                return Response.ok().build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cidade.update()", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        try {
            LOG.info("Requisição Cidade.delete()");
            Cidade cidade = repository.findById(id);
            if (cidade != null) {
                cidade.setAtivo(false);
                return Response.ok().build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cidade.delete()", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
