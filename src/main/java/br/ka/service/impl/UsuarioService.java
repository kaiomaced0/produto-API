package br.ka.service.impl;

import br.ka.dto.AuthUsuarioDTO;
import br.ka.dto.UsuarioDTO;
import br.ka.model.Perfil;
import br.ka.model.Usuario;
import br.ka.repository.UsuarioRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class UsuarioService{

    @Inject
    HashService hash;
    @Inject
    UsuarioRepository repository;

    public Usuario byLoginAndSenha(AuthUsuarioDTO authDTO){
        String senha = hash.getHashSenha(authDTO.senha());
        Usuario usuario = new Usuario();
        usuario = repository.findByEmailAndSenha(authDTO.login(), senha);
        if(usuario == null){
            usuario = repository.findByCpfAndSenha(authDTO.login(), senha);
        }
        return usuario;
    }

}
