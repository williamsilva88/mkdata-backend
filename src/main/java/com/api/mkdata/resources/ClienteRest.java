package com.api.mkdata.resources;

import com.api.mkdata.domain.DTO.ClienteDTO;
import com.api.mkdata.domain.DTO.ClienteFiltroDTO;
import com.api.mkdata.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteRest {

    @Value("${ambiente}")
    private String ambiente;

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<ClienteDTO>> getClientes(
            @RequestParam(value = "nome", required=false) String nome,
            @RequestParam(value = "ativo", required=false) Boolean ativo
    ){
        ClienteFiltroDTO filtro = new ClienteFiltroDTO(nome, ativo);
        return ResponseEntity.ok().body(clienteService.getClients(filtro));
    }

   @PostMapping(value = "")
    public ResponseEntity<ClienteDTO> newClient(@RequestBody ClienteDTO cliente){
        return ResponseEntity.ok().body(clienteService.insertClient(cliente));
    }

    @PutMapping(value = "")
    public ResponseEntity<ClienteDTO> updateClient(@RequestBody ClienteDTO cliente){
        return ResponseEntity.ok().body(clienteService.updateClients(cliente));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable("id") Long id){
        try {
            clienteService.deleteClients(id);
            return ResponseEntity.ok().body(new Object());
        }catch (Exception e){
            return ResponseEntity.ok().body(new Object());
        }

    }

}
