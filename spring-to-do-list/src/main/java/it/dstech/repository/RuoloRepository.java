package it.dstech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.dstech.model.Ruolo;

@Repository
public interface RuoloRepository extends JpaRepository<Ruolo, Long> {
	Ruolo findByRuolo(String ruolo);
}
