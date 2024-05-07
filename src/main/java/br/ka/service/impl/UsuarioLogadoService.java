package br.ka.service.impl;

import br.ka.dto.responseDTO.UsuarioResponseDTO;
import br.ka.repository.UsuarioRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;

@ApplicationScoped
public class UsuarioLogadoService {

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO getPerfilUsuarioLogado() {

        String cpf = jsonWebToken.getSubject();
        try {
            Log.info("Requisição UsuarioLogado.getPerfilUsuarioLogado()");
            return new UsuarioResponseDTO(usuarioRepository.findByCpf(cpf));
        } catch (Exception e) {
            Log.error("Erro ao rodar Requisição UsuarioLogado.getPerfilUsuarioLogado()");
            return null;
        }

    }

}



