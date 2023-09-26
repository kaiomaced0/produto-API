package br.ka.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Marca extends EntityClass {

    @Column(name = "nome")
    private String nome;

    @OneToMany
    @JoinColumn(name = "lista_fornecedores_marca")
    private List<Fornecedor> fornecedores;

    // getters and setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }
}
