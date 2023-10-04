package br.ka.resource;

import br.ka.dto.ItemProdutoDTO;
import br.ka.dto.ItemProdutoUpdateDTO;
import br.ka.dto.responseDTO.ItemProdutoResponseDTO;
import br.ka.service.ItemProdutoService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/itens-produto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemProdutoResource {

    @Inject
    ItemProdutoService service;

    @GET
    @PermitAll
    public List<ItemProdutoResponseDTO> getAll() {
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
    public Response insert(ItemProdutoDTO dto) {
        return service.insert(dto);
    }

    @PUT
    @PermitAll
    public Response update(ItemProdutoUpdateDTO updateDTO) {
        return service.update(updateDTO);
    }

    @PATCH
    @Path("/{id}")
    @PermitAll
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }
}
