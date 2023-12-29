package br.ka.service.impl;

import br.ka.model.Usuario;
import br.ka.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.JsonWebToken;

@ApplicationScoped
public class UsuarioLogadoService {

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    UsuarioRepository usuarioRepository;
    @Inject
    HashService hash;

//    @Transactional
//    public UsuarioResponseDTO updateSenha(MudarSenhaDTO senha) {
//        try {
//
//            Usuario entity = usuarioRepository.findByCpf(jsonWebToken.getSubject());
//
//            if(hash.getHashSenha(senha.senhaAntiga()) != entity.getSenha())
//                throw new Exception("Senha anterior Incorreta");
//
//            entity.setSenha(hash.getHashSenha(senha.novaSenha()));
//            return new UsuarioResponseDTO(entity);
//        } catch (Exception e) {
//            return null;
//        }
//
//    }

}



