package br.ka.converterjpa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import br.ka.model.Estado;

@Converter(autoApply = true)
public class EstadoConverter implements AttributeConverter<Estado, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Estado estado) {
        return estado == null ? null : estado.getId();
    }

    @Override
    public Estado convertToEntityAttribute(Integer id) {
        return Estado.valueOf(id);
    }
}
