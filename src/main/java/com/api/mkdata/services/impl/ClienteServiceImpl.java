package com.api.mkdata.services.impl;

import com.api.mkdata.domain.DTO.ClienteDTO;
import com.api.mkdata.domain.DTO.ClienteFiltroDTO;
import com.api.mkdata.domain.DTO.ContatoDTO;
import com.api.mkdata.domain.dao.ContatoDao;
import com.api.mkdata.domain.entity.Cliente;
import com.api.mkdata.domain.dao.ClienteDao;
import com.api.mkdata.domain.entity.Contato;
import com.api.mkdata.domain.entity.repository.ClienteRepository;
import com.api.mkdata.domain.entity.repository.ContatoRepository;
import com.api.mkdata.services.ClienteService;
import com.api.mkdata.services.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    private ContatoDao contatoDao;

    @Override
    public List<ClienteDTO> getClients(ClienteFiltroDTO filtro){
        List<Cliente> clientes = clienteDao.findClientsFiltro(filtro);
        List<ClienteDTO> clientesDto = Cliente.toClienteDto(clientes);
        if(clientesDto != null){
            clientesDto.forEach(cli->{
                List<Contato> contatos = contatoDao.findClientsByClient(cli.getId());
                cli.setTelefones(Contato.toContato(contatos));
            });
        }
        return clientesDto;
    }

    @Override
    @Transactional
    public ClienteDTO insertClient(ClienteDTO client) {
        if(validationClient(client)){
            throw new BadRequestException("Informações obrigatórias incompletas!");
        }

        client.setId(null);
        client.setDataCadastro(LocalDateTime.now());
        client.setAtivo(true);
        Cliente cli = clienteRepository.save(client.toCliente());
        ClienteDTO cleinteSaved = cli.toClienteDto();
        if(client.getTelefones() != null){
            client.getTelefones().forEach(value->{
                value.setCliId(cli.getId());
                Contato contato = contatoRepository.save(value.toContato());
                cleinteSaved.getTelefones().add(contato.toContato());
            });
        }
        return cleinteSaved;
    }

    /**
     * Realiza a validação do objeto de clientes
     * @param client Objeto de informações a serem validadas
     * @return Retorna verdadeiro caso encontre impedimentos de obrigatoriedade
     */
    public Boolean validationClient(ClienteDTO client){
        return client.getNome().isEmpty() ||
                client.getNome() == "" ||
                client.getDocumento().isEmpty() ||
                client.getDocumento() == "" ||
                client.getTipo() == null
                ? true : false;
    }

    @Override
    @Transactional
    public ClienteDTO updateClients(ClienteDTO client) {
        if(validationClient(client)){
            throw new BadRequestException("Informações obrigatórias incompletas!");
        }

        Optional<Cliente> cliente = clienteRepository.findById(client.getId());
        ClienteDTO clientReseult = client.toCliente().toClienteDto();
        if(cliente.isPresent()){
            cliente.get().setNome(client.getNome());
            cliente.get().setTipo(client.getTipo().getDescricao());
            cliente.get().setDocumento(client.getDocumento());
            cliente.get().setRgOuIe(client.getRgOuIe());
            cliente.get().setAtivo(client.getAtivo());
            Cliente clienteUpdate = clienteRepository.save(cliente.get());
            List<Contato> contatos = contatoDao.findClientsByClient(clienteUpdate.getId());

            if(contatos != null){
                contatos.forEach(value->{
                    ContatoDTO cli = client.getTelefones().stream()
                            .filter(tel -> value.getId() == tel.getId())
                            .findAny()
                            .orElse(null);
                    if(cli == null){
                        contatoRepository.deleteById(value.getId());
                    }
                });
            }

            client.getTelefones().forEach(tel->{
                if(tel.getId() == null){
                    tel.setCliId(clienteUpdate.getId());
                    Contato contato = contatoRepository.save(tel.toContato());
                    clientReseult.getTelefones().add(contato.toContato());
                }else{
                    clientReseult.getTelefones().add(tel);
                }
            });

        }
        return clientReseult;
    }

    @Override
    @Transactional
    public void deleteClients(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()){
            contatoRepository.deleteByClient(id);
            clienteRepository.delete(cliente.get());
        }
    };

}
