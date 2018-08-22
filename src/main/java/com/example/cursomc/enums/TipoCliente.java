package com.example.cursomc.enums;

public enum TipoCliente {

    PESSOAFISICA(1, "Pessoa Fisica"),
    PESSOAJURIDICA(2, "Pessoa Juridica");

    private int codigo;
    private String descricao;

    private TipoCliente(int codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoCliente toEnum(Integer codigo){ // Recebe codigo e retorna enum
        if(codigo == null){
            return null;
        }

        for(TipoCliente aux : TipoCliente.values()){
            if(codigo.equals(aux.getCodigo()))
              return aux;
        }

        throw new IllegalArgumentException("Id invalido: " + codigo);
    }
}
