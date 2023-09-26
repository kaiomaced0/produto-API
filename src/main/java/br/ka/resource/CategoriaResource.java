package br.ka.resource;

import br.ka.dto.CategoriaDTO;
import br.ka.dto.CategoriaUpdateDTO;
import br.ka.dto.responseDTO.CategoriaResponseDTO;
import br.ka.service.CategoriaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;

import java.util.List;

@Path("/categorias")
@Produces("application/json")
@Consumes("application/json")
public class CategoriaResource {

    @Inject
    CategoriaService service;

    @GET
    @PermitAll
    public List<CategoriaResponseDTO> getAll() {
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
    public Response insert(CategoriaDTO categoriaDTO) {
        return service.insert(categoriaDTO);
    }

    @PUT
    @PermitAll
    public Response update(CategoriaUpdateDTO categoriaUpdateDTO) {
        return service.update(categoriaUpdateDTO);
    }

    @DELETE
    @Path("/{id}")
    @PermitAll
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }
}
