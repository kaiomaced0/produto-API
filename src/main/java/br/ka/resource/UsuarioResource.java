package br.ka.resource;


import br.ka.dto.UsuarioDTO;
import br.ka.dto.UsuarioUpdateSenhaDTO;
import br.ka.dto.responseDTO.UsuarioResponseDTO;
import br.ka.service.impl.UsuarioService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService usuarioService;



    @GET
    @RolesAllowed({"Admin"})
    public List<UsuarioResponseDTO> getAll(){
        return usuarioService.getAll();

    }

    @GET
    @RolesAllowed({"Admin"})
    @Path("/{nome}")
    public Response getNome(@PathParam("nome") String nome){
        return usuarioService.getNome(nome);

    }

    @GET
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response getId(@PathParam("id") long id){
        return usuarioService.getId(id);

    }

    @POST
    @PermitAll
    @Transactional
    public Response insert(UsuarioDTO p){
        return usuarioService.insert(p);
    }

    @PATCH
    @RolesAllowed({"Admin"})
    @Path("/senha/{id}")
    @Transactional
    public Response updateSenha(@PathParam("id") Long id, UsuarioUpdateSenhaDTO senha){
        return usuarioService.updateSenha(senha);
    }
}
