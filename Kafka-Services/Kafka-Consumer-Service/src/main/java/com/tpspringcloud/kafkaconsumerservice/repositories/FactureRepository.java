package com.tpspringcloud.kafkaconsumerservice.repositories;

import com.tpspringcloud.kafkaconsumerservice.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FactureRepository extends JpaRepository<Facture, Long> {
}
