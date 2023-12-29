package br.ka.converterjpa;

import br.ka.model.TipoNotificacao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoNotificacaoConverter implements AttributeConverter<TipoNotificacao, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TipoNotificacao tipoNotificacao) {
        return tipoNotificacao == null ? null : tipoNotificacao.getId();
    }

    @Override
    public TipoNotificacao convertToEntityAttribute(Integer id) {
        return TipoNotificacao.valueOf(id);
    }
}
