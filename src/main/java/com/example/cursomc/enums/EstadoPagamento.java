package com.example.cursomc.enums;

public enum EstadoPagamento {

    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private int codigo;
    private String descricao;

    private EstadoPagamento(int codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EstadoPagamento toEnum(Integer codigo){ // Recebe codigo e retorna enum
        if(codigo == null){
            return null;
        }

        for(EstadoPagamento aux : EstadoPagamento.values()){
            if(codigo.equals(aux.getCodigo()))
                return aux;
        }

        throw new IllegalArgumentException("Id invalido: " + codigo);
    }

}
