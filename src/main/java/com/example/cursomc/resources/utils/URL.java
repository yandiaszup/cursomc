package com.example.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

    // Reebe uma string oom espaços e os retira
    public static String decodeParam(String s){
        try {
            return URLDecoder.decode(s,"UTF-8");
        }
        catch (UnsupportedEncodingException e ){
            return "";
        }
    }


    //recebe um string com numeros separados entre virgulas e coloca dentro de uma lista de inteiro
    // Ex : "1,2,3" ---------> [1,2,3]
    public static List<Integer> decodeIntList(String s){
        String [] vet =s.split(",");
        List<Integer> listInt = new ArrayList<>();
        for (int i=0; i<vet.length; i++)
            listInt.add(Integer.parseInt(vet[i]));
        return listInt;
    }
}
