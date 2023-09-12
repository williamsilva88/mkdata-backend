package com.api.mkdata.domain.entity;

import com.api.mkdata.domain.DTO.ClienteDTO;
import com.api.mkdata.domain.DTO.ContatoDTO;
import com.api.mkdata.domain.util.BooleanConverter;
import com.api.mkdata.enums.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cliente")
public class Cliente {

    @Id
    @GeneratedValue(generator = "seq_clientes", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator (name = "seq_clientes", sequenceName = "cliente_cli_id_seq", allocationSize = 1)
    @Column(name="cli_id")
    private Long id;

    @Column(name="cli_nome", nullable = false)
    private String nome;

    @Column(name="cli_tipo", nullable = false)
    private String tipo;

    @Column(name="cli_cpf_cnpj", nullable = false)
    private String documento;

    @Column(name="cli_rg_ie")
    private String rgOuIe;

    @Column(name="cli_data_cadastro")
    private LocalDateTime dataCadastro;

    @Column(name="cli_ativo")
    @Convert(converter = BooleanConverter.class)
    private Boolean ativo;

    public ClienteDTO toClienteDto(){
        ClienteDTO c = new ClienteDTO();
        c.setId(this.getId());
        c.setNome(this.getNome());
        c.setTipo(TipoDocumento.valueOf(this.getTipo()));
        c.setDocumento(this.getDocumento());
        c.setRgOuIe(this.getRgOuIe());
        c.setDataCadastro(this.getDataCadastro());
        c.setAtivo(this.getAtivo());
        c.setTelefones(new ArrayList<>());
        return c;
    }

    public static List<ClienteDTO> toClienteDto(List<Cliente> clientes){
        List<ClienteDTO> c = new ArrayList<>();
        clientes.forEach(cli->{
            c.add(cli.toClienteDto());
        });
        return c;
    }
}
