package com.daduo.api.tiktokapi.model.error;

import lombok.Data;

import java.util.List;

@Data
public class Errors {
    private final List<Error> errors;

    public Errors(List<Error> errors) {
        this.errors = errors;
    }
}
