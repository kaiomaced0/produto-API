package br.ka.model;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Venda extends EntityClass {
    @JoinColumn(name = "itemproduto_venda")
    @OneToMany
    private List<ItemProduto> itemProdutos;

    @JoinColumn
    @ManyToOne
    private Cliente cliente;
    private String observacao;
    private Double valorTotal;

    public List<ItemProduto> getItemProdutos() {
        return itemProdutos;
    }
    public void setItemProdutos(List<ItemProduto> itemProdutos) {
        this.itemProdutos = itemProdutos;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    public Double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    

}
