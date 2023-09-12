package com.api.mkdata.domain.DTO;

import com.api.mkdata.domain.entity.Cliente;
import com.api.mkdata.domain.entity.Contato;
import com.api.mkdata.enums.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long id;
    private String nome;
    private TipoDocumento tipo;
    private String documento;
    private String rgOuIe;
    private LocalDateTime dataCadastro;
    private Boolean ativo;
    private List<ContatoDTO> telefones;

    public Cliente toCliente(){
        Cliente c = new Cliente();
        c.setId(this.getId());
        c.setNome(this.getNome());
        c.setTipo(this.getTipo().getDescricao());
        c.setDocumento(this.getDocumento());
        c.setRgOuIe(this.getRgOuIe());
        c.setDataCadastro(this.getDataCadastro());
        c.setAtivo(this.getAtivo());
        return c;
    }

    public static List<Cliente> toCliente(List<ClienteDTO> clientes){
        List<Cliente> c = new ArrayList<>();
        if(clientes != null){
            clientes.forEach(cli->{
                c.add(cli.toCliente());
            });
        }
        return c;
    }
}
