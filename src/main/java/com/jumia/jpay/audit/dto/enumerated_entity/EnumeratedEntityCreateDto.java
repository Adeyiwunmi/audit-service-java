package com.jumia.jpay.audit.dto.enumerated_entity;

import javax.validation.constraints.NotEmpty;

/**
 * @author adeyi.adebolu
 * created on 07/04/2019
 */
public class EnumeratedEntityCreateDto {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
