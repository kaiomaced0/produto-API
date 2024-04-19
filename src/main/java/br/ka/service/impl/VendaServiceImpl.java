package br.ka.service.impl;

import br.ka.dto.responseDTO.UsuarioResponseDTO;
import br.ka.model.EntityClass;
import br.ka.model.ItemProduto;
import br.ka.model.Usuario;
import br.ka.repository.*;
import br.ka.service.VendaService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.ka.model.Venda;
import br.ka.dto.VendaDTO;
import br.ka.dto.VendaUpdateDTO;
import br.ka.dto.responseDTO.VendaResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VendaServiceImpl implements VendaService {

    public static final Logger LOG = Logger.getLogger(VendaServiceImpl.class);

    @Inject
    VendaRepository repository;
    @Inject
    ClienteRepository clienteRepository;
    @Inject
    ItemProdutoRepository itemProdutoRepository;

    @Inject
    NotificacaoRepository notificacaoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    ProdutoRepository produtoRepository;

    @Override
    public List<VendaResponseDTO> getAll() {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Venda.getAll()");
            return repository.listAll().stream().filter(v -> Objects.equals(v.getEmpresa(), u.getEmpresa())).filter(EntityClass::getAtivo)
                    .map(VendaResponseDTO::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Venda.getAll()");
            return null;
        }
    }

    @Override
    public Response getId(Long id) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Venda.getId()");
            Venda venda = repository.findById(id);
            if(venda.getAtivo() && venda.getEmpresa() == u.getEmpresa()) {
                return Response.ok(new VendaResponseDTO(venda)).build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Venda.getId()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response insert(VendaDTO vendaDTO) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Venda.insert()");
            Venda venda =  new Venda();
            venda.setCliente(clienteRepository.findById(vendaDTO.idCliente()));
            venda.setItemProdutos(new ArrayList<>());
            venda.setValorTotal(0.0);
            vendaDTO.itemProduto().forEach(v -> {
                ItemProduto i = new ItemProduto();
                i.setProduto(produtoRepository.findById(v.idProduto()));
                i.setQuantidade(v.quantidade());
                i.setPreco(v.quantidade() * i.getProduto().getValor());
                i.setAtivo(true);
                itemProdutoRepository.persist(i);
                venda.setValorTotal(venda.getValorTotal() + i.getPreco());
                venda.getItemProdutos().add(i);
            } );
            venda.setObservacao(vendaDTO.observacao());
            repository.persist(venda);
            return Response.ok(new VendaResponseDTO(venda)).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Venda.insert()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

//    @Override
//    @Transactional
//    public Response update(VendaUpdateDTO vendaUpdateDTO) {
//        try {
//            LOG.info("Requisição Venda.update()");
//            Venda venda = repository.findById(vendaUpdateDTO.id());
//            if(venda.getAtivo()){
//            venda.setCliente(clienteRepository.findById(vendaUpdateDTO.idCliente()));
//            if(venda.getItemProdutos() == null){
//                venda.setItemProdutos(new ArrayList<>());
//            }
//            vendaUpdateDTO.itemprodutos().forEach(v -> {
//                ItemProduto i = new ItemProduto();
//                i.setProduto(produtoRepository.findById(v.idProduto()));
//                i.setQuantidade(v.quantidade());
//                i.setPreco(v.quantidade() * i.getProduto().getValor());
//                i.setAtivo(true);
//                itemProdutoRepository.persist(i);
//                venda.getItemProdutos().add(i);
//            });
//            venda.setObservacao(vendaUpdateDTO.observacao());
//            venda.getItemProdutos().forEach(v -> venda.setValorTotal(venda.getValorTotal() + v.getPreco()));
//
//                return Response.ok(new VendaResponseDTO(venda)).build();
//            }
//            else{
//                throw new Exception();
//            }
//        } catch (Exception e) {
//            LOG.error("Erro ao rodar Requisição Venda.update()");
//            return Response.status(400).entity(e.getMessage()).build();
//        }
//    }

    @Override
    @Transactional
    public Response delete(Long id) {
        try {
            LOG.info("Requisição Venda.delete()");
            repository.findById(id).setAtivo(false);
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Venda.delete()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }
}
