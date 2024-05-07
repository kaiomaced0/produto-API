package br.ka.service.impl;

import br.ka.model.EntityClass;
import br.ka.model.Usuario;
import br.ka.repository.CategoriaRepository;
import br.ka.repository.NotificacaoRepository;
import br.ka.repository.UsuarioRepository;
import br.ka.service.FornecedorService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;
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

    @Inject
    NotificacaoRepository notificacaoRepository;

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    public List<FornecedorResponseDTO> getAll() {
        Usuario u = usuarioRepository.findByCpf(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Fornecedor.getAll()");
            return repository.listAll().stream().filter(f -> f.getEmpresa() ==u.getEmpresa()).filter(EntityClass::getAtivo)
                    .map(FornecedorResponseDTO::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Fornecedor.getAll()");
            return null;
        }
    }

    @Override
    public Response getId(Long id) {
        Usuario u = usuarioRepository.findByCpf(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Fornecedor.getId()");
            Fornecedor fornecedor = repository.findById(id);
            if(fornecedor.getAtivo() && u.getEmpresa() == fornecedor.getEmpresa()) {
                return Response.ok(new FornecedorResponseDTO(fornecedor)).build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Fornecedor.getId()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response insert(FornecedorDTO fornecedorDTO) {
        Usuario u = usuarioRepository.findByCpf(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Fornecedor.insert()");
            Fornecedor fornecedor =  FornecedorDTO.criaFornecedor(fornecedorDTO);
            fornecedor.setEmpresa(u.getEmpresa());
            fornecedor.setCategorias(new HashSet<>());
            fornecedorDTO.idCategoria().stream().forEach(c -> fornecedor.getCategorias().add(categoriaRepository.findById(c)));
            repository.persist(fornecedor);
            return Response.ok(new FornecedorResponseDTO(fornecedor)).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Fornecedor.insert()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response update(Long id, FornecedorUpdateDTO dto) {
        Usuario u = usuarioRepository.findByCpf(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Fornecedor.update()");
            Fornecedor fornecedor = repository.findById(id);
            if(fornecedor.getAtivo() && u.getEmpresa() == fornecedor.getEmpresa()){

                return Response.ok().build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Fornecedor.update()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        Usuario u = usuarioRepository.findByCpf(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Fornecedor.delete()");
            Fornecedor f = repository.findById(id);
            if(!f.getAtivo() && u.getEmpresa() != f.getEmpresa()){
                throw new Exception();
            }
            f.setAtivo(false);
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Fornecedor.delete()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }
}
