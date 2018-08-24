package com.example.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> list = new ArrayList<>();

    public ValidationError(Integer status, String msg, long timestamp) {
        super(status, msg, timestamp);
    }

    public List<FieldMessage> getList() {
        return list;
    }

    public void addError(String fieldName, String messagem) {
        list.add(new FieldMessage(fieldName,messagem));
    }
}
