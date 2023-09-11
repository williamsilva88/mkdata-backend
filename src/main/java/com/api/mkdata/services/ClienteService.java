package com.api.mkdata.services;

import com.api.mkdata.domain.DTO.ClienteDTO;
import com.api.mkdata.domain.DTO.ClienteFiltroDTO;

import java.util.List;

public interface ClienteService {

    public List<ClienteDTO> getClients(ClienteFiltroDTO filter);

    public ClienteDTO insertClient(ClienteDTO client);

    public ClienteDTO updateClients(ClienteDTO client);

    public void deleteClients(Long id);
}
