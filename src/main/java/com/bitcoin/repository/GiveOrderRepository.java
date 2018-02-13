package com.bitcoin.repository;

import com.bitcoin.domain.GiveOrder;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the GiveOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GiveOrderRepository extends JpaRepository<GiveOrder, Long> {

}
