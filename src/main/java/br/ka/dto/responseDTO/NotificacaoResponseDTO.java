package br.ka.dto.responseDTO;

import br.ka.model.Notificacao;

public record NotificacaoResponseDTO(
        Long id,
        String titulo) {

    public NotificacaoResponseDTO(Notificacao notificacao) {
        this(notificacao.getId(), notificacao.getTitulo());
    }
}
