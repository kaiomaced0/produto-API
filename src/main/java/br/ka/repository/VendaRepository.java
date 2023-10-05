package br.ka.repository;
import br.ka.model.Venda;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class VendaRepository implements PanacheRepository<Venda> {
    public List<Venda> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%").list();
    }
}
