package br.ka.repository;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

import br.ka.model.ItemProduto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ItemProdutoRepository implements PanacheRepository<ItemProduto> {

    public List<ItemProduto> findByProdutoNome(String nome) {
        if (nome == null) {
            return null;
        }
        return find("UPPER(produto.nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%").list();
    }
}
