package com.project.consorcio.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.consorcio.entity.Medicamento;
import com.project.consorcio.entity.TipoMedicamento;
import com.project.consorcio.services.MedicamentoServices;
import com.project.consorcio.services.TipoMedicamentoServices;

@Controller
@RequestMapping("/medicamento")
public class MedicamentoController {
	@Autowired
	private MedicamentoServices servicioMedicamento;

	@Autowired
	private TipoMedicamentoServices servicioTipoMedicamento;
	
	@RequestMapping("/lista")
	public String index(Model model) {
		model.addAttribute("medicamentos", servicioMedicamento.listarTodos());
		model.addAttribute("tipos", servicioTipoMedicamento.listarTodos());
		return "medicamento";
	}
	
	
	@RequestMapping("/grabar")
	public String grabar(
		@RequestParam("codigo") Integer codigo,
		@RequestParam("nombre") String nombre,
		@RequestParam("descripcion") String descripcion,
		@RequestParam("stock") int stock,
		@RequestParam("precio") double precio,
		@RequestParam("fecha") String fecha,
		@RequestParam("tipo") int codigoTipo,
		RedirectAttributes redirect
	) {
		try {
			LocalDate fechaVencimiento = LocalDate.parse(fecha);
			TipoMedicamento tipoMedicamento = new TipoMedicamento(codigoTipo, null);
			Medicamento medicamento = new Medicamento(nombre, descripcion, stock, precio, fechaVencimiento, tipoMedicamento);
			
			if ( codigo == 0 ) {
				servicioMedicamento.registrar(medicamento);
				// Crear atributo de tipo flash
				redirect.addFlashAttribute("MENSAJE", "Medicamento registrado");							
			} else {
				medicamento.setCodigo(codigo);
				servicioMedicamento.actualizar(medicamento);
				redirect.addFlashAttribute("MENSAJE", "Medicamento registrado");
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return "redirect:/medicamento/lista";
	}
	
	
	// ruta o direcciòn URL para buscar medicamento segun còdigo.
	
	@RequestMapping("/buscar")
	@ResponseBody
	public Medicamento buscar(@RequestParam("codigo") Integer codigo) {
		return servicioMedicamento.buscarPorId(codigo);
	}
	
	
	@GetMapping("/registrarTipo")
	public String registrarTipoMedicamento() {
		return "index";
	}
	
	@PostMapping("/grabarTipo")
	public String registrarTipoMedicamento(@RequestParam("nombre") String nombre) {
		TipoMedicamento tipoMedicamento = new TipoMedicamento(0, nombre);
		Medicamento medicamento = new Medicamento();

		medicamento.setTipo(tipoMedicamento);
		
		servicioTipoMedicamento.save(medicamento.getTipo());
		return "redirect:/medicamento/registrarTipo";
	}
}

// Model: El atributo queda guardad en memoria.
// RedirectAttributes: Es para crear un atributo flask. La pàgina recibe el atributo, lo consme, y lueo el atributo se elimina de memoria.
// ResponseBody: El valor de retorno de un metodo se convierte en formato json
// requestParam: Se utilizA para recuperar el valor de un parametro que ha sido enviado por la URL
