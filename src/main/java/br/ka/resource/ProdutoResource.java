package br.ka.resource;

import br.ka.dto.ProdutoDTO;
import br.ka.dto.ProdutoUpdateDTO;
import br.ka.dto.responseDTO.ProdutoResponseDTO;
import br.ka.service.ProdutoService;
import jakarta.annotation.security.RolesAllowed;
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
    @Path("/estoques")
    @PermitAll
    public Response getEstoques() {
        return service.estoque();
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
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ProdutoUpdateDTO produtoUpdateDTO) {
        return service.update(id, produtoUpdateDTO);
    }
    @PATCH
    @PermitAll
    @Path("/add/{id}")
    public Response addEstoque(@PathParam("id") Long id, Integer quantidade) {
        return service.addEstoque(id, quantidade);
    }
    @PATCH
    @RolesAllowed({"Admin"})
    @Path("/remove/{id}")
    public Response removeEstoque(@PathParam("id") Long id, Integer quantidade) {
        return service.removeEstoque(id, quantidade);
    }

    @POST
    @PermitAll
    @Path("/addcategorias/{id}")
    public Response addCategorias(@PathParam("id") Long id, List<Long> categorias) {
        return service.addCategoria(id, categorias);
    }

    @PATCH
    @Path("/delete/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }
}
