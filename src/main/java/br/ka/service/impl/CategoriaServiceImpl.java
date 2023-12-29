package br.ka.service.impl;

import br.ka.model.Empresa;
import br.ka.model.EntityClass;
import br.ka.model.Usuario;
import br.ka.repository.NotificacaoRepository;
import br.ka.repository.UsuarioRepository;
import br.ka.service.CategoriaService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
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

    @Inject
    NotificacaoRepository notificacaoRepository;

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    public List<CategoriaResponseDTO> getAll() {
        Usuario u = usuarioRepository.findByCpf(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Categoria.getAll()");
            return repository.listAll().stream().filter(c -> c.getEmpresa() == u.getEmpresa()).filter(EntityClass::getAtivo)
                    .map(CategoriaResponseDTO::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Categoria.getAll()");
            return null;
        }
    }

    @Override
    public Response getId(Long id) {
        Usuario u = usuarioRepository.findByCpf(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Categoria.getId()");
            Categoria categoria = repository.findById(id);
            if(categoria.getAtivo() && categoria.getEmpresa() == u.getEmpresa()) {
                return Response.ok(new CategoriaResponseDTO(categoria)).build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Categoria.getId()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    @Transactional
    public Response insert(CategoriaDTO categoriaDTO) {
        Usuario u = usuarioRepository.findByCpf(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Categoria.insert()");
            Categoria categoria = CategoriaDTO.criaCategoria(categoriaDTO);
            categoria.setEmpresa(u.getEmpresa());
            repository.persist(categoria);
            return Response.ok(new CategoriaResponseDTO(categoria)).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Categoria.insert()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    @Transactional
    public Response update(CategoriaUpdateDTO categoriaUpdateDTO) {
        Usuario u = usuarioRepository.findByCpf(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Categoria.update()");
            Categoria categoria = repository.findById(categoriaUpdateDTO.id());
            if(categoria.getEmpresa() != u.getEmpresa()){
                throw new Exception();
            }
            categoria.setNome(categoriaUpdateDTO.nome());
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Categoria.update()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        Usuario u = usuarioRepository.findByCpf(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Categoria.delete()");
            Categoria c = new Categoria();
            c = repository.findById(id);
            if(c.getEmpresa() != u.getEmpresa()){
                throw new Exception();
            }
            c.setAtivo(false);

            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Categoria.delete()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
