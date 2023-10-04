package br.ka.resource;

import br.ka.dto.ClienteDTO;
import br.ka.dto.ClienteUpdateDTO;
import br.ka.dto.responseDTO.ClienteResponseDTO;
import br.ka.service.ClienteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    ClienteService service;

    @GET
    public List<ClienteResponseDTO> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getId(@PathParam("id") Long id) {
        return service.getId(id);
    }

    @POST
    public Response insert(ClienteDTO dto) {
        return service.insert(dto);
    }

    @PUT
    public Response update(ClienteUpdateDTO updateDTO) {
        return service.update(updateDTO);
    }

    @PATCH
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }
}
