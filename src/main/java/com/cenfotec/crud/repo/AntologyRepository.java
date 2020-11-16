package com.cenfotec.crud.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfotec.crud.domain.Antology;

public interface AntologyRepository extends JpaRepository<Antology, Long>{
	public List<Antology> findByNameContaining(String word);
}
