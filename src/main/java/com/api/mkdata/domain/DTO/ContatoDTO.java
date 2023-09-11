package com.api.mkdata.domain.DTO;

import com.api.mkdata.domain.entity.Cliente;
import com.api.mkdata.domain.entity.Contato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContatoDTO {

    private Long id;
    private String descricao;
    private String telefone;
    private ClienteDTO cliente;

    public Contato toContato(Cliente cli){
        Contato c = new Contato();
        if(this.getDescricao() != null || this.getTelefone() != null){
            return new Contato(this.getId(), this.getDescricao(), this.getTelefone(), cli);
        }
        return c;
    }

    public static List<Contato> toContato(List<ContatoDTO> contato, Cliente cli){
        List<Contato> c = new ArrayList<>();
        contato.forEach(con->{
            Contato novoContato = con.toContato(cli);
            if(novoContato != null){
                c.add(novoContato);
            }
        });
        return c;
    }
}
