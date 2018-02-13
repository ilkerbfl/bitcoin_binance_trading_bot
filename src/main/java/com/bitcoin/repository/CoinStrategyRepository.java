package com.bitcoin.repository;

import com.bitcoin.domain.CoinStrategy;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by İlker ÇATAK on 1/20/18.
 */
public interface CoinStrategyRepository  extends JpaRepository<CoinStrategy,Long>{
}
