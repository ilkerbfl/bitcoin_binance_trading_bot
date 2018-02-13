package com.bitcoin.repository;

import com.bitcoin.domain.OrderBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by İlker ÇATAK on 12/9/17.
 */
public interface OrderBookRepository extends JpaRepository<OrderBookEntity,Long> {
}
