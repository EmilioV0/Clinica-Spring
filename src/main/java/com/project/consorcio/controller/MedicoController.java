package com.project.consorcio.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.consorcio.entity.Distrito;
import com.project.consorcio.entity.Especialidad;
import com.project.consorcio.entity.Medico;
import com.project.consorcio.entity.Sede;
import com.project.consorcio.services.DistritoServices;
import com.project.consorcio.services.EspecialidadServices;
import com.project.consorcio.services.MedicoServices;
import com.project.consorcio.services.SedeServices;

@Controller
@RequestMapping("/medico")
public class MedicoController {
	@Autowired
	private MedicoServices medicoServices;
	
	@Autowired
	private DistritoServices distritoServices;
	
	@Autowired 
	private SedeServices sedeServices;
	
	@Autowired
	private EspecialidadServices especialidadServices;
	
	
	@RequestMapping("/lista")
	public String index(Model model) {
		model.addAttribute("medicos", medicoServices.listarTodos());
		model.addAttribute("especialidades", especialidadServices.listarTodos());
		model.addAttribute("sedes", sedeServices.listarTodos());
		model.addAttribute("distritos", distritoServices.listarTodos());
		return "medico";
	}
	
	@RequestMapping("/grabar")
	public String grabar(
			@RequestParam("nombre") String nombre,
			@RequestParam("apellidos") String apellidos,
			@RequestParam("fecha") String fecha,
			@RequestParam("direccion") String direccion,
			@RequestParam("sexo") String sexo,
			@RequestParam("estadoCivil") String estadoCivil,
			@RequestParam("dni") String dni,
			@RequestParam("sueldo") double sueldo,
			@RequestParam("especialidad") Integer codigoEspecialidad,
			@RequestParam("sede") Integer codigoSede,
			@RequestParam("distrito") Integer codigoDistrito,
			RedirectAttributes redirect	
		) {
		
		try {
			LocalDate fechaNacimiento = LocalDate.parse(fecha);
			Distrito distrito = new Distrito(codigoDistrito);
			Especialidad especialidad = new Especialidad(codigoEspecialidad);
			Sede sede = new Sede(codigoSede);
			// Medico medico = new Medico(nombre, apellidos, fechaNacimiento, sexo, estadoCivil, dni, sueldo, grabar(nombre, apellidos, fecha, direccion, sexo, estadoCivil, dni, sueldo, codigoEspecialidad, codigoSede, codigoDistrito, redirect));

			
			
		} catch(Exception e) {
			
		} 
		
		return null;
	}
	
	
}
