package com.bitcoin.service.mapper;

import com.bitcoin.domain.*;
import com.bitcoin.service.dto.StrategyOfCoinDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StrategyOfCoin and its DTO StrategyOfCoinDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StrategyOfCoinMapper extends EntityMapper<StrategyOfCoinDTO, StrategyOfCoin> {

    

    

    default StrategyOfCoin fromId(Long id) {
        if (id == null) {
            return null;
        }
        StrategyOfCoin strategyOfCoin = new StrategyOfCoin();
        strategyOfCoin.setId(id);
        return strategyOfCoin;
    }
}
