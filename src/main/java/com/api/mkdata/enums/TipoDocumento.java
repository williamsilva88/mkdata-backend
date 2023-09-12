package com.api.mkdata.enums;

public enum TipoDocumento {

    FISICA("FISICA"),
    JURIDICA("JURIDICA");

    private String descricao;

    TipoDocumento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}
