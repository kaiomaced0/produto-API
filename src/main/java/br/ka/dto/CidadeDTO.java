package br.ka.dto;
import br.ka.model.Cidade;

public record CidadeDTO(
        String nome,
        Integer estado) {

    public static Cidade criaCidade(CidadeDTO cidadeDTO) {
        Cidade cidade = new Cidade();
        cidade.setNome(cidadeDTO.nome());
        return cidade;
    }
}
