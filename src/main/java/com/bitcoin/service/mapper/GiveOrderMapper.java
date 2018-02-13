package com.bitcoin.service.mapper;

import com.bitcoin.domain.*;
import com.bitcoin.service.dto.GiveOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity GiveOrder and its DTO GiveOrderDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GiveOrderMapper extends EntityMapper<GiveOrderDTO, GiveOrder> {

    

    

    default GiveOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        GiveOrder giveOrder = new GiveOrder();
        giveOrder.setId(id);
        return giveOrder;
    }
}
