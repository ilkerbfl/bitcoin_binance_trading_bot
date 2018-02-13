package com.bitcoin.service.mapper;

import com.bitcoin.domain.*;
import com.bitcoin.service.dto.NewOrderLocaleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NewOrderLocale and its DTO NewOrderLocaleDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NewOrderLocaleMapper extends EntityMapper<NewOrderLocaleDTO, NewOrderLocale> {

    

    

    default NewOrderLocale fromId(Long id) {
        if (id == null) {
            return null;
        }
        NewOrderLocale newOrderLocale = new NewOrderLocale();
        newOrderLocale.setId(id);
        return newOrderLocale;
    }
}
