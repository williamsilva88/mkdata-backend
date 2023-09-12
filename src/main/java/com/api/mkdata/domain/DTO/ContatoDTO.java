package com.api.mkdata.domain.DTO;

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
    private Long cliId;

    public Contato toContato(){
        Contato c = new Contato();
        if(this.getDescricao() != null || this.getTelefone() != null){
            return new Contato(this.getId(), this.getDescricao(), this.getTelefone(), this.getCliId());
        }
        return c;
    }

    public static List<Contato> toContato(List<ContatoDTO> contato){
        List<Contato> c = new ArrayList<>();
        if(contato != null){
            contato.forEach(con->{
                Contato novoContato = con.toContato();
                if(novoContato != null){
                    c.add(novoContato);
                }
            });
        }
        return c;
    }
}
