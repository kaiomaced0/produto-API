package br.ka.resource;

import br.ka.dto.CidadeDTO;
import br.ka.dto.CidadeUpdateDTO;
import br.ka.dto.responseDTO.CidadeResponseDTO;
import br.ka.service.CidadeService;
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
    public List<CidadeResponseDTO> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getId(@PathParam("id") Long id) {
        return service.getId(id);
    }

    @POST
    public Response insert(CidadeDTO dto) {
        return service.insert(dto);
    }

    @PUT
    public Response update(CidadeUpdateDTO updateDTO) {
        return service.update(updateDTO);
    }

    @PATCH
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }
}
