package br.ka.dto;
import br.ka.model.Notificacao;

public record NotificacaoUpdateDTO(
        Long id,
        String titulo, String descricao, Long tipoNotificacao) {


}
