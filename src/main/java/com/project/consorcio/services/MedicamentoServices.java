package com.project.consorcio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.consorcio.entity.Medicamento;
import com.project.consorcio.repository.MedicamentoRepository;

@Service
public class MedicamentoServices {
	@Autowired
	private MedicamentoRepository repo;
	
	public void registrar(Medicamento medicamento) {
		repo.save(medicamento); // Se registra el codigo no existe 
	}
	
	public void actualizar(Medicamento medicamento) {
		repo.save(medicamento); // Se actualiza si el código ya existe.
	}
	
	public void eliminarPorId(Integer codigo) {
		repo.deleteById(codigo);
	}

	
	public Medicamento buscarPorId(Integer codigo) {
		return repo.findById(codigo).orElse(null);
	}
	
	public List<Medicamento> listarTodos() {
		return repo.findAll();
	}
}
