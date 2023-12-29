package br.ka.service;

import br.ka.dto.NotificacaoDTO;
import br.ka.dto.NotificacaoUpdateDTO;
import br.ka.dto.responseDTO.NotificacaoResponseDTO;
import jakarta.ws.rs.core.Response;

import java.util.List;

public interface NotificacaoService {

    List<NotificacaoResponseDTO> getAll();

    Response getId(Long id);

    Response insert(NotificacaoDTO notificacaoDTO);

    Response delete(Long id);

    Response update(NotificacaoUpdateDTO notificacaoUpdateDTO);
}
