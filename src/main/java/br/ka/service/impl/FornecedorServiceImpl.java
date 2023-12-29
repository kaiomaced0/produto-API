package br.ka.service.impl;

import br.ka.model.EntityClass;
import br.ka.repository.CategoriaRepository;
import br.ka.service.FornecedorService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import java.util.stream.Collectors;
import java.util.List;
import org.jboss.logging.Logger;
import br.ka.repository.FornecedorRepository;
import br.ka.model.Fornecedor;
import br.ka.dto.FornecedorDTO;
import br.ka.dto.FornecedorUpdateDTO;
import br.ka.dto.responseDTO.FornecedorResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService {

    public static final Logger LOG = Logger.getLogger(FornecedorServiceImpl.class);

    @Inject
    FornecedorRepository repository;

    @Inject
    CategoriaRepository categoriaRepository;

    @Override
    public List<FornecedorResponseDTO> getAll() {
        try {
            LOG.info("Requisição Fornecedor.getAll()");
            return repository.listAll().stream().filter(EntityClass::getAtivo)
                    .map(FornecedorResponseDTO::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Fornecedor.getAll()");
            return null;
        }
    }

    @Override
    public Response getId(Long id) {
        try {
            LOG.info("Requisição Fornecedor.getId()");
            Fornecedor fornecedor = repository.findById(id);
            if(fornecedor.getAtivo()) {
                return Response.ok(new FornecedorResponseDTO(fornecedor)).build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Fornecedor.getId()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    @Transactional
    public Response insert(FornecedorDTO fornecedorDTO) {
        try {
            LOG.info("Requisição Fornecedor.insert()");
            Fornecedor fornecedor =  FornecedorDTO.criaFornecedor(fornecedorDTO);
            fornecedorDTO.idCategoria().stream().forEach(c -> fornecedor.getCategorias().add(categoriaRepository.findById(c)));
            repository.persist(fornecedor);
            return Response.ok(new FornecedorResponseDTO(fornecedor)).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Fornecedor.insert()");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional
    public Response update(FornecedorUpdateDTO dto) {
        try {
            LOG.info("Requisição Fornecedor.update()");
            Fornecedor fornecedor = repository.findById(dto.id());
            if(fornecedor.getAtivo()){

                return Response.ok().build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Fornecedor.update()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        try {
            LOG.info("Requisição Fornecedor.delete()");
            Fornecedor f = repository.findById(id);
            f.setAtivo(false);
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Fornecedor.delete()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
