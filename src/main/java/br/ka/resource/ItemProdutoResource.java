package br.ka.resource;

import br.ka.dto.ItemProdutoDTO;
import br.ka.dto.ItemProdutoUpdateDTO;
import br.ka.dto.responseDTO.ItemProdutoResponseDTO;
import br.ka.service.ItemProdutoService;
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
    public List<ItemProdutoResponseDTO> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getId(@PathParam("id") Long id) {
        return service.getId(id);
    }

    @POST
    public Response insert(ItemProdutoDTO dto) {
        return service.insert(dto);
    }

    @PUT
    public Response update(ItemProdutoUpdateDTO updateDTO) {
        return service.update(updateDTO);
    }

    @PATCH
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }
}
