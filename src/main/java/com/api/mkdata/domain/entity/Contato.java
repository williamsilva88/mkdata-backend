package com.api.mkdata.domain.entity;

import com.api.mkdata.domain.DTO.ContatoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuppressWarnings("serial")
@Table(name="contato")
public class Contato {

    @Id
    @GeneratedValue(generator = "seq_contatos", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator (name = "seq_contatos", sequenceName = "contato_con_id_seq", allocationSize = 1)
    @Column(name="con_id")
    private Long id;

    @Column(name="com_descricao", nullable = false)
    private String descricao;

    @Column(name="con_telefone", nullable = false)
    private String telefone;

    @JoinColumn(name = "cli_id")
    private Long cliId;

    public ContatoDTO toContato(){
        ContatoDTO c = new ContatoDTO();
        if(this.getDescricao() != null || this.getTelefone() != null){
            return new ContatoDTO(this.getId(), this.getDescricao(), this.getTelefone(), this.getCliId());
        }
        return c;
    }

    public static List<ContatoDTO> toContato(List<Contato> contato){
        List<ContatoDTO> c = new ArrayList<>();
        if(contato != null){
            contato.forEach(con->{
                ContatoDTO novoContato = con.toContato();
                if(novoContato != null){
                    c.add(novoContato);
                }
            });
        }
        return c;
    }
}
