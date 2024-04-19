package br.ka.dto.responseDTO;

import br.ka.model.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String login,
        String cpf,
        String nome,
        String email,
        EmpresaResponseDTO empresa

) {
    public UsuarioResponseDTO(Usuario user) {
        this(user.getId(),
                user.getLogin(),
                user.getCpf(),
                user.getNome(),
                user.getEmail(),
                new EmpresaResponseDTO(user.getEmpresa().getId(), user.getEmpresa().getNome()));

    }
}
