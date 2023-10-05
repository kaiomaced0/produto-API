package br.ka.resource;

import br.ka.dto.MarcaDTO;
import br.ka.dto.MarcaUpdateDTO;
import br.ka.dto.responseDTO.MarcaResponseDTO;
import br.ka.service.MarcaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;

import java.util.List;

@Path("/marcas")
@Produces("application/json")
@Consumes("application/json")
public class MarcaResource {

    @Inject
    MarcaService service;

    @GET
    @PermitAll
    public List<MarcaResponseDTO> getAll() {
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
    public Response insert(MarcaDTO marcaDTO) {
        return service.insert(marcaDTO);
    }

    @PATCH
    @PermitAll
    public Response update(MarcaUpdateDTO marcaUpdateDTO) {
        return service.update(marcaUpdateDTO);
    }

    @PATCH
    @Path("/{id}")
    @PermitAll
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }
}
