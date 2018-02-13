package com.bitcoin.repository;

import com.bitcoin.domain.CoinWillBeListened;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CoinWillBeListened entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CoinWillBeListenedRepository extends JpaRepository<CoinWillBeListened, Long> {

}
