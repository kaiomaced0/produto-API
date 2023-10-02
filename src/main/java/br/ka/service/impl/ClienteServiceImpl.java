package br.ka.service.impl;

import br.ka.model.EntityClass;
import br.ka.service.ClienteService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import java.util.stream.Collectors;
import java.util.List;
import br.ka.repository.ClienteRepository;
import br.ka.model.Cliente;
import br.ka.dto.ClienteDTO;
import br.ka.dto.ClienteUpdateDTO;
import br.ka.dto.responseDTO.ClienteResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    public static final Logger LOG = Logger.getLogger(ClienteServiceImpl.class);

    @Inject
    ClienteRepository repository;

    @Override
    public List<ClienteResponseDTO> getAll() {
        try {
            LOG.info("Requisição Cliente.getAll()");
            return repository.listAll().stream().filter(EntityClass::getAtivo)
                    .map(ClienteResponseDTO::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cliente.getAll()");
            return null;
        }
    }

    @Override
    public Response getId(Long id) {
        try {
            LOG.info("Requisição Cliente.getId()");
            Cliente cliente = repository.findById(id);
            if(cliente != null && cliente.getAtivo()) {
                return Response.ok(new ClienteResponseDTO(cliente)).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cliente.getId()");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional
    public Response insert(ClienteDTO clienteDTO) {
        try {
            LOG.info("Requisição Cliente.insert()");
            Cliente cliente = ClienteDTO.criaCliente(clienteDTO);
            repository.persist(cliente);
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cliente.insert()");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional
    public Response update(ClienteUpdateDTO clienteUpdateDTO) {
        try {
            LOG.info("Requisição Cliente.update()");
            Cliente existingCliente = repository.findById(clienteUpdateDTO.id());
            if (existingCliente != null && existingCliente.getAtivo()) {
                // Atualize os campos necessários do existingCliente
                // ...
                return Response.ok().build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cliente.update()");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        try {
            LOG.info("Requisição Cliente.delete()");
            Cliente cliente = repository.findById(id);
            if (cliente != null) {
                cliente.setAtivo(false);
                return Response.ok().build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cliente.delete()");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
