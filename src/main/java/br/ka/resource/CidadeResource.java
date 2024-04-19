package br.ka.resource;

import br.ka.dto.CidadeDTO;
import br.ka.dto.CidadeUpdateDTO;
import br.ka.dto.responseDTO.CidadeResponseDTO;
import br.ka.service.CidadeService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/cidades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CidadeResource {

    @Inject
    CidadeService service;

    @GET
    @PermitAll
    public Response getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    @PermitAll
    public Response getId(@PathParam("id") Long id) {
        return service.getId(id);
    }

    @POST
    @RolesAllowed({"Admin"})
    public Response insert(CidadeDTO dto) {
        return service.insert(dto);
    }

    @PUT
    @RolesAllowed({"Admin"})
    public Response update(CidadeUpdateDTO updateDTO) {
        return service.update(updateDTO);
    }

    @PATCH
    @Path("/delete/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }
}
