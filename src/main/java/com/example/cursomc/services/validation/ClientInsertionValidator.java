package com.example.cursomc.services.validation;

import com.example.cursomc.DTO.ClientNewDto;
import com.example.cursomc.enums.TipoCliente;
import com.example.cursomc.resources.FieldMessage;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;


import com.example.cursomc.services.validation.utils.*;

public class ClientInsertionValidator implements ConstraintValidator<ClientInsertion, ClientNewDto> {
    @Override
    public void initialize(ClientInsertion ann) {

    }

    @Override
    public boolean isValid(ClientNewDto objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        // inclua os testes aqui, inserindo erros na lista
        if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !Br.isValidCPF(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage());
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
