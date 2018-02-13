package com.bitcoin.repository;

import com.bitcoin.domain.OrderBookFull;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by İlker ÇATAK on 12/10/17.
 */
public interface OrderBookFullRepository extends JpaRepository<OrderBookFull,Long> {
}
