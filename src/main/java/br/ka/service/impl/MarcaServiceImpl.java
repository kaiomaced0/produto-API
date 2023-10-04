package br.ka.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import br.ka.dto.MarcaUpdateDTO;
import br.ka.model.EntityClass;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import br.ka.repository.MarcaRepository;
import br.ka.model.Marca;
import br.ka.dto.MarcaDTO;
import br.ka.dto.responseDTO.MarcaResponseDTO;
import br.ka.service.MarcaService;

@ApplicationScoped
public class MarcaServiceImpl implements MarcaService {

    public static final Logger LOG = Logger.getLogger(MarcaServiceImpl.class);

    @Inject
    MarcaRepository repository;

    @Override
    public List<MarcaResponseDTO> getAll() {
        try {
            LOG.info("Requisição Marca.getAll()");
            return repository.findAll().stream().filter(EntityClass::getAtivo)
                    .map(MarcaResponseDTO::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Marca.getAll()");
            return null;
        }
    }

    @Override
    public Response getId(Long id) {
        try {
            LOG.info("Requisição Marca.getId()");
            Marca marca = repository.findById(id);
            if(marca.getAtivo()){
                return Response.ok(new MarcaResponseDTO(marca)).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Marca.getId()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    @Transactional
    public Response insert(MarcaDTO marcaDTO) {
        try {
            LOG.info("Requisição Marca.insert()");
            Marca marca = MarcaDTO.criaMarca(marcaDTO);
            repository.persist(marca);
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Marca.insert()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        try {
            LOG.info("Requisição Marca.delete()");
            repository.findById(id).setAtivo(false);
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Marca.delete()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @Override
    public Response update(MarcaUpdateDTO marcaUpdateDTO) {
        try {
            LOG.info("Requisição Marca.update()");
            Marca marca = repository.findById(marcaUpdateDTO.id());
            if(marca.getAtivo()){
                marca.setNome(marcaUpdateDTO.nome());
                return Response.ok().build();
            }else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Marca.update()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
