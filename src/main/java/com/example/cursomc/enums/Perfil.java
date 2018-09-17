package com.example.cursomc.enums;

public enum Perfil {

    ADMIN(1, "ROLE_ADMIN"), // exigencia do spring security
    CLIENTE(2, "ROLE CLIENTE");

    private int codigo;
    private String descricao;

    private Perfil(int codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer codigo){ // Recebe codigo e retorna enum
        if(codigo == null){
            return null;
        }

        for(Perfil aux : Perfil.values()){
            if(codigo.equals(aux.getCodigo()))
                return aux;
        }

        throw new IllegalArgumentException("Id invalido: " + codigo);
    }

}
