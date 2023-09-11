package com.api.mkdata.domain.entity;

import com.api.mkdata.domain.DTO.ClienteDTO;
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

    @ManyToOne(optional = false, cascade = {CascadeType.ALL, CascadeType.REFRESH})
    @JoinColumn(name = "cli_id", foreignKey = @ForeignKey(name = "contato_fk_cli_id"))
    private Cliente cliente;

    public ContatoDTO toContato(ClienteDTO cli){
        ContatoDTO c = new ContatoDTO();
        if(this.getDescricao() != null || this.getTelefone() != null){
            return new ContatoDTO(this.getId(), this.getDescricao(), this.getTelefone(), cli);
        }
        return c;
    }

    public static List<ContatoDTO> toContato(List<Contato> contato, ClienteDTO cli){
        List<ContatoDTO> c = new ArrayList<>();
        contato.forEach(con->{
            ContatoDTO novoContato = con.toContato(cli);
            if(novoContato != null){
                c.add(novoContato);
            }
        });
        return c;
    }
}
