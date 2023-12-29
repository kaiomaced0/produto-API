package br.ka.service.impl;

import br.ka.model.EntityClass;
import br.ka.model.Usuario;
import br.ka.repository.NotificacaoRepository;
import br.ka.repository.UsuarioRepository;
import br.ka.service.ClienteService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import java.util.stream.Collectors;
import java.util.List;

import br.ka.repository.CidadeRepository;
import br.ka.repository.ClienteRepository;
import br.ka.model.Cliente;
import br.ka.dto.ClienteDTO;
import br.ka.dto.ClienteUpdateDTO;
import br.ka.dto.ClienteUpdateDadosClienteDTO;
import br.ka.dto.ClienteUpdateDadosEmpresaDTO;
import br.ka.dto.ClienteUpdateEnderecoDTO;
import br.ka.dto.responseDTO.ClienteResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    public static final Logger LOG = Logger.getLogger(ClienteServiceImpl.class);

    @Inject
    ClienteRepository repository;

    @Inject
    CidadeRepository cidadeRepository;
    @Inject
    NotificacaoRepository notificacaoRepository;

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    public List<ClienteResponseDTO> getAll() {
        Usuario u = usuarioRepository.findByCpf(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Cliente.getAll()");
            return repository.listAll().stream().filter(c -> c.getEmpresa() == u.getEmpresa()).filter(EntityClass::getAtivo)
                    .map(ClienteResponseDTO::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cliente.getAll()");
            return null;
        }
    }

    @Override
    public Response getId(Long id) {
        Usuario u = usuarioRepository.findByCpf(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Cliente.getId()");
            Cliente cliente = repository.findById(id);
            if(cliente.getAtivo() && cliente.getEmpresa() == u.getEmpresa()) {
                return Response.ok(new ClienteResponseDTO(cliente)).build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cliente.getId()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    @Transactional
    public Response insert(ClienteDTO clienteDTO) {

        Usuario u = usuarioRepository.findByCpf(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Cliente.insert()");
            Cliente cliente = ClienteDTO.criaCliente(clienteDTO);
            cliente.setEmpresa(u.getEmpresa());
            cliente.setCidade(cidadeRepository.findById(clienteDTO.idCidade()));
            repository.persist(cliente);
            return Response.ok(new ClienteResponseDTO(cliente)).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cliente.insert()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    @Transactional
    public Response update(ClienteUpdateDTO clienteUpdateDTO) {
        Usuario u = usuarioRepository.findByCpf(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Cliente.update()");
            Cliente cliente = repository.findById(clienteUpdateDTO.id());
            if (cliente.getAtivo() && cliente.getEmpresa() == u.getEmpresa()) {
                cliente.setCidade(cidadeRepository.findById(clienteUpdateDTO.cidade()));
                cliente.setNomeCliente(clienteUpdateDTO.nomeCliente());
                cliente.setNomeEmpresa(clienteUpdateDTO.nomeEmpresa());
                cliente.setCnpj(clienteUpdateDTO.cnpj());
                cliente.setCpfCliente(clienteUpdateDTO.cpfCliente());
                cliente.setEndereco(clienteUpdateDTO.endereco());
                return Response.ok(new ClienteResponseDTO(cliente)).build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cliente.update()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        Usuario u = usuarioRepository.findByCpf(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Cliente.delete()");
            Cliente cliente = repository.findById(id);
            if (cliente != null && cliente.getEmpresa() == u.getEmpresa()) {
                cliente.setAtivo(false);
                return Response.ok().build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cliente.delete()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response updateDadosEmpresa(ClienteUpdateDadosEmpresaDTO clienteUpdateDadosEmpresaDTO) {
        Usuario u = usuarioRepository.findByCpf(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Cliente.update()");
            Cliente cliente = repository.findById(clienteUpdateDadosEmpresaDTO.id());
            if (cliente.getAtivo() && cliente.getEmpresa() == u.getEmpresa()) {
                cliente.setNomeEmpresa(clienteUpdateDadosEmpresaDTO.nomeEmpresa());
                cliente.setCnpj(clienteUpdateDadosEmpresaDTO.cnpj());
                return Response.ok(new ClienteResponseDTO(cliente)).build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cliente.update()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response updateDadosCliente(ClienteUpdateDadosClienteDTO clienteUpdateDadosClienteDTO) {
        Usuario u = usuarioRepository.findByCpf(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Cliente.update()");
            Cliente cliente = repository.findById(clienteUpdateDadosClienteDTO.id());
            if (cliente.getAtivo() && cliente.getEmpresa() == u.getEmpresa()) {
                cliente.setNomeCliente(clienteUpdateDadosClienteDTO.nomeCliente());
                cliente.setCpfCliente(clienteUpdateDadosClienteDTO.cpfCliente());
                return Response.ok(new ClienteResponseDTO(cliente)).build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cliente.update()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }    
    }

    @Override
    public Response updateEndereco(ClienteUpdateEnderecoDTO clienteUpdateEnderecoDTO) {
        Usuario u = usuarioRepository.findByCpf(jsonWebToken.getSubject());
        
        try {
            LOG.info("Requisição Cliente.update()");
            Cliente cliente = repository.findById(clienteUpdateEnderecoDTO.id());
            if (cliente.getAtivo() && u.getEmpresa() == cliente.getEmpresa()) {
                cliente.setCidade(cidadeRepository.findById(clienteUpdateEnderecoDTO.cidade()));
                cliente.setEndereco(clienteUpdateEnderecoDTO.endereco());
                return Response.ok(new ClienteResponseDTO(cliente)).build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cliente.update()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    
    }
}
