package br.ka.resource;

import br.ka.dto.FornecedorDTO;
import br.ka.dto.FornecedorUpdateDTO;
import br.ka.dto.responseDTO.FornecedorResponseDTO;
import br.ka.service.FornecedorService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;

import java.util.List;

@Path("/fornecedores")
@Produces("application/json")
@Consumes("application/json")
public class FornecedorResource {

    @Inject
    FornecedorService service;

    @GET
    @PermitAll
    public List<FornecedorResponseDTO> getAll() {
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
    public Response insert(FornecedorDTO fornecedorDTO) {
        return service.insert(fornecedorDTO);
    }

    @PUT
    @PermitAll
    public Response update(FornecedorUpdateDTO fornecedorUpdateDTO) {
        return service.update(fornecedorUpdateDTO);
    }

    @PATCH
    @Path("/delete/{id}")
    @PermitAll
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }
}
