package com.bitcoin.repository;

import com.bitcoin.domain.NewOrderLocale;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the NewOrderLocale entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NewOrderLocaleRepository extends JpaRepository<NewOrderLocale, Long> {

}
