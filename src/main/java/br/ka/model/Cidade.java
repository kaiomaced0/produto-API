package br.ka.model;

import br.ka.model.EntityClass;
import br.ka.model.Estado;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Cidade extends EntityClass {

    @ManyToOne
    private Estado estado;
    private String nome;

    // Getters e Setters
}
