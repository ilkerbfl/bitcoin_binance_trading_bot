package com.bitcoin.service.mapper;

import com.bitcoin.domain.*;
import com.bitcoin.service.dto.CoinWillBeListenedDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CoinWillBeListened and its DTO CoinWillBeListenedDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CoinWillBeListenedMapper extends EntityMapper<CoinWillBeListenedDTO, CoinWillBeListened> {

    

    

    default CoinWillBeListened fromId(Long id) {
        if (id == null) {
            return null;
        }
        CoinWillBeListened coinWillBeListened = new CoinWillBeListened();
        coinWillBeListened.setId(id);
        return coinWillBeListened;
    }
}
