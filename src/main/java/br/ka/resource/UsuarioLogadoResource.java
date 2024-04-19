package br.ka.resource;

import br.ka.service.impl.UsuarioLogadoService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuariologado")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioLogadoResource {

    @Inject
    UsuarioLogadoService service;
    @GET
    @PermitAll
    public Response getUsuarioLogado(){
        try {
            return Response.ok(service.getPerfilUsuarioLogado()).build();
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
    }
}
