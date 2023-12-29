package br.ka.resource;

import br.ka.dto.NotificacaoDTO;
import br.ka.dto.NotificacaoUpdateDTO;
import br.ka.dto.responseDTO.NotificacaoResponseDTO;
import br.ka.service.NotificacaoService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/notificacaos")
@Produces("application/json")
@Consumes("application/json")
public class NotificacaoResource {

    @Inject
    NotificacaoService service;

    @GET
    @PermitAll
    public List<NotificacaoResponseDTO> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    @PermitAll
    public Response getId(@PathParam("id") Long id) {
        return service.getId(id);
    }

    @POST
    @PermitAll
    public Response insert(NotificacaoDTO notificacaoDTO) {
        return service.insert(notificacaoDTO);
    }

    @PUT
    @PermitAll
    public Response update(NotificacaoUpdateDTO notificacaoUpdateDTO) {
        return service.update(notificacaoUpdateDTO);
    }

    @PATCH
    @Path("/delete/{id}")
    @PermitAll
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }
}
