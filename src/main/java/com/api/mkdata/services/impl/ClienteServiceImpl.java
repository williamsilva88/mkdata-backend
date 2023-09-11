package com.api.mkdata.services.impl;

import com.api.mkdata.domain.DTO.ClienteDTO;
import com.api.mkdata.domain.DTO.ClienteFiltroDTO;
import com.api.mkdata.domain.entity.Cliente;
import com.api.mkdata.domain.dao.ClienteDao;
import com.api.mkdata.domain.entity.repository.ClienteRepository;
import com.api.mkdata.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteDao clienteDao;

    @Override
    public List<ClienteDTO> getClients(ClienteFiltroDTO filtro){
        List<Cliente> clientes = clienteDao.findClientsFiltro(filtro);
        List<ClienteDTO> clientesDto = Cliente.toClienteDto(clientes);
        return clientesDto;
    }

    @Override
    public ClienteDTO insertClient(ClienteDTO client) {
        client.setId(null);
        client.setDataCadastro(LocalDateTime.now());
        client.setAtivo(true);
        Cliente cli = client.toCliente();
        return saveCliente(cli);
    }

    @Override
    public ClienteDTO updateClients(ClienteDTO client) {
        Optional<Cliente> cliente = clienteRepository.findById(client.getId());
        if(cliente.isPresent()){
            cliente.get().setNome(client.getNome());
            cliente.get().setTipo(client.getTipo().getDescricao());
            cliente.get().setDocumento(client.getDocumento());
            cliente.get().setRgOuIe(client.getRgOuIe());
            cliente.get().setAtivo(client.getAtivo());
            cliente.get().setTelefones(client.toCliente().getTelefones());
            cliente.get().getTelefones().forEach(value->{
                value.setCliente(cliente.get());
            });
            client = saveCliente(cliente.get());
        }
        return client;
    }

    private ClienteDTO saveCliente(Cliente client){
        Cliente cliente = clienteRepository.save(client);
        return cliente.toClienteDto();
    }

    @Override
    public void deleteClients(Long id) {
        clienteRepository.deleteById(id);
    };

}
