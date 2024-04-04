package br.ka.dto;

import br.ka.model.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(
        String nome,
        String login,
        String email,
        String senha,
        String cpf,
        Integer idPerfil) {
    public static Usuario criaUsuario(UsuarioDTO usuarioDTO) {
        Usuario entity = new Usuario();
        entity.setNome(usuarioDTO.nome());
        entity.setLogin(usuarioDTO.login());
        entity.setCpf(usuarioDTO.cpf());
        return entity;
    }

}
