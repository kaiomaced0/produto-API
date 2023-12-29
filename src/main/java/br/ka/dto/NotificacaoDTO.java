package br.ka.dto;
import br.ka.model.Notificacao;

public record NotificacaoDTO(
        String titulo) {

    public static Notificacao criaNotificacao(NotificacaoDTO notificacaoDTO) {
        Notificacao Notificacao = new Notificacao();
        Notificacao.setTitulo(notificacaoDTO.titulo());
        return Notificacao;
    }
}
