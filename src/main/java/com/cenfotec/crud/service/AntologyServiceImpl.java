package com.cenfotec.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenfotec.crud.domain.Antology;
import com.cenfotec.crud.repo.AntologyRepository;

@Service
public class AntologyServiceImpl implements AntologyService {

	@Autowired
	AntologyRepository repo;
	
	@Override
	public void save(Antology antology) {
		repo.save(antology);
	}

	@Override
	public Optional<Antology> get(Long id) {
		return repo.findById(id);
	}

	@Override
	public List<Antology> find(String name) {
		return repo.findByNameContaining(name);
	}

	@Override
	public List<Antology> getAll() {
		return repo.findAll();
	}

}
