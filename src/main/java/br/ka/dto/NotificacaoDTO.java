package br.ka.dto;
import br.ka.model.Notificacao;
import br.ka.model.TipoNotificacao;

public record NotificacaoDTO(
        String titulo, Long idEmpresa, String descricao, Long tipoNotificacao) {

    public static Notificacao criaNotificacao(NotificacaoDTO notificacaoDTO) {
        Notificacao notificacao = new Notificacao();
        notificacao.setTitulo(notificacaoDTO.titulo);
        notificacao.setDescricao(notificacaoDTO.descricao);
        notificacao.setLida(false);
        notificacao.setTipoNotificacao(TipoNotificacao.valueOf(notificacaoDTO.tipoNotificacao.intValue()));


        return notificacao;
    }
}
