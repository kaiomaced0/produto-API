package br.ka.resource;

import br.ka.dto.ClienteDTO;
import br.ka.dto.ClienteUpdateDTO;
import br.ka.dto.ClienteUpdateDadosClienteDTO;
import br.ka.dto.ClienteUpdateDadosEmpresaDTO;
import br.ka.dto.ClienteUpdateEnderecoDTO;
import br.ka.dto.responseDTO.ClienteResponseDTO;
import br.ka.service.ClienteService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
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
    @PermitAll
    public Response insert(ClienteDTO dto) {
        return service.insert(dto);
    }

    @PUT
    @PermitAll
    public Response update(ClienteUpdateDTO updateDTO) {
        return service.update(updateDTO);
    }

    @PUT
    @PermitAll
    public Response updateDadosEmpresa(ClienteUpdateDadosEmpresaDTO updateDTO) {
        return service.updateDadosEmpresa(updateDTO);
    }

    @PUT
    @PermitAll
    public Response updateDadosCliente(ClienteUpdateDadosClienteDTO updateDTO) {
        return service.updateDadosCliente(updateDTO);
    }

    @PUT
    @PermitAll
    public Response updateEndereco(ClienteUpdateEnderecoDTO updateDTO) {
        return service.updateEndereco(updateDTO);
    }

    @PATCH
    @Path("/delete/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }
}
