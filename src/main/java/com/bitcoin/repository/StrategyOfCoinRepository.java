package com.bitcoin.repository;

import com.bitcoin.domain.StrategyOfCoin;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.Optional;


/**
 * Spring Data JPA repository for the StrategyOfCoin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StrategyOfCoinRepository extends JpaRepository<StrategyOfCoin, Long> {

    StrategyOfCoin findByCoinName(String coinName);

}
