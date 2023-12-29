package br.ka.resource;

import br.ka.dto.ProdutoDTO;
import br.ka.dto.ProdutoUpdateDTO;
import br.ka.dto.responseDTO.ProdutoResponseDTO;
import br.ka.service.ProdutoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;

import java.util.List;

@Path("/produtos")
@Produces("application/json")
@Consumes("application/json")
public class ProdutoResource {

    @Inject
    ProdutoService service;

    @GET
    @PermitAll
    public List<ProdutoResponseDTO> getAll() {
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
    public Response insert(ProdutoDTO produtoDTO) {
        return service.insert(produtoDTO);
    }

    @PUT
    @PermitAll
    public Response update(ProdutoUpdateDTO produtoUpdateDTO) {
        return service.update(produtoUpdateDTO);
    }

    @PATCH
    @Path("/delete/{id}")
    @PermitAll
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }
}
