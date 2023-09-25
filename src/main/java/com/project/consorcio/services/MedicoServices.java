package com.project.consorcio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.consorcio.entity.Medico;
import com.project.consorcio.repository.MedicoRepository;

@Service
public class MedicoServices {
	@Autowired
	private MedicoRepository repo;
	
	public void insert(Medico medico) {
		repo.save(medico);
	}
	
	public List<Medico> listarTodos() {
		return repo.findAll();
	}
}
