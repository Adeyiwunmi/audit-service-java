package com.jumia.jpay.audit.domain;

import com.jumia.jpay.audit.dto.enumerated_entity.EnumeratedEntityDto;
import org.springframework.data.annotation.Id;

/**
 * @author adeyi.adebolu
 * created on 07/04/2019
 */
public class EnumeratedEntity {
    @Id
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnumeratedEntityDto convertToDto(){
        EnumeratedEntityDto dto = new EnumeratedEntityDto();
        dto.setId(getId());
        dto.setName(getName());
        return dto;
    }
}
