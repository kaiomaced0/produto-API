package br.ka.service.impl;

import br.ka.dto.NotificacaoDTO;
import br.ka.dto.NotificacaoUpdateDTO;
import br.ka.dto.responseDTO.NotificacaoResponseDTO;
import br.ka.model.Notificacao;
import br.ka.model.EntityClass;
import br.ka.repository.NotificacaoRepository;
import br.ka.service.NotificacaoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class NotificacaoServiceImpl implements NotificacaoService {

    public static final Logger LOG = Logger.getLogger(NotificacaoServiceImpl.class);

    @Inject
    NotificacaoRepository repository;

    @Override
    public List<NotificacaoResponseDTO> getAll() {
        try {
            LOG.info("Requisição Notificacao.getAll()");
            return repository.listAll().stream().filter(EntityClass::getAtivo)
                    .map(NotificacaoResponseDTO::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Notificacao.getAll()");
            return null;
        }
    }

    @Override
    public Response getId(Long id) {
        try {
            LOG.info("Requisição Notificacao.getId()");
            Notificacao notificacao = repository.findById(id);
            if(notificacao.getAtivo()) {
                return Response.ok(new NotificacaoResponseDTO(notificacao)).build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Notificacao.getId()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    @Transactional
    public Response insert(NotificacaoDTO notificacaoDTO) {
        try {
            LOG.info("Requisição Notificacao.insert()");
            Notificacao notificacao = NotificacaoDTO.criaNotificacao(notificacaoDTO);
            repository.persist(notificacao);
            return Response.ok(new NotificacaoResponseDTO(notificacao)).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Notificacao.insert()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    @Transactional
    public Response update(NotificacaoUpdateDTO notificacaoUpdateDTO) {
        try {
            LOG.info("Requisição Notificacao.update()");
            Notificacao Notificacao = repository.findById(notificacaoUpdateDTO.id());
            Notificacao.setTitulo(notificacaoUpdateDTO.titulo());
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Notificacao.update()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        try {
            LOG.info("Requisição Notificacao.delete()");
            repository.findById(id).setAtivo(false);
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Notificacao.delete()");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
