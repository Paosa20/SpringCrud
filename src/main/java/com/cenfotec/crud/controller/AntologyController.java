package com.cenfotec.crud.controller;

import com.cenfotec.crud.domain.Article;
import com.cenfotec.crud.repo.AntologyRepository;
import com.cenfotec.crud.service.AntologyServiceImpl;
import com.cenfotec.crud.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.cenfotec.crud.domain.Antology;
import com.cenfotec.crud.service.AntologyService;

import java.util.Optional;

@Controller
public class AntologyController {

	@Autowired
	AntologyService anthologyService;

	@Autowired
	ArticleService articleService;
	
	@RequestMapping("/")
	public String home(Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/insertar",  method = RequestMethod.GET)
	public String insertarPage(Model model) {
		model.addAttribute(new Antology());
		return "insertar";
	}	
	
	@RequestMapping(value = "/insertar",  method = RequestMethod.POST)
	public String insertarAction(Antology antology, BindingResult result, Model model) {
		anthologyService.save(antology);
		return "index";
	}
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("anthologies",anthologyService.getAll());
		return "listar";
	}

	@GetMapping("/modificar/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Optional<Antology> antology = anthologyService.get(id);

		if (antology.isPresent()){
			model.addAttribute("antology", antology);
			return "update";
		}

		return "index";
	}

	@PostMapping("/modificar/{id}")
	public String insertarUpdate(@PathVariable("id") long id, Antology antology, BindingResult result, Model model) {

		if (result.hasErrors()){
			antology.setId(id);
			return "/modificar/{id}";
		}
		anthologyService.save(antology);
		model.addAttribute("anthologies",anthologyService.getAll());
		return "listar";
	}

	@GetMapping("agregar-articulos/{id}")//MODIFICAR
	public String showCategoryForm(@PathVariable("id") long id, Model model) {
		Optional<Antology> antology = anthologyService.get(id);
		Article newArticle = new Article();
		if (antology.isPresent()) {
			newArticle.setAnthology(antology.get());
			model.addAttribute("antology",antology.get());
			model.addAttribute("article",newArticle);
			return "agregar-articulos";
		}
		return "notfound";
	}

	@RequestMapping(value = "/agregar-articulos/{id}", method = RequestMethod.POST)
	public String crearCategorias(@PathVariable("id") long id, Article article, BindingResult result, Model model){
		/*Optional<Antology> an = anthologyService.get(id);
		//Article article = new Article(, an);

		return "agregar-articulos";*/

		Optional<Antology> antology = anthologyService.get(id);
		if (antology.isPresent()) {
			article.setAnthology(antology.get());
			articleService.save(article);

			model.addAttribute("anthologies",anthologyService.getAll());
			return "listar";
		}
		return "index";

	}

	@RequestMapping(value="/mostrar-detalle/{id}") //GET DE DETALLES
	public String mostrarDetalle(Model model, @PathVariable long id) {
		Optional<Antology> detalleAntologia = anthologyService.get(id);
		if (detalleAntologia.isPresent()) {
			model.addAttribute("detalleAntologia",detalleAntologia.get());
			return "mostrar-detalle";
		}
		return "index";
	}
	/*@PostMapping("/editar/{id}") //REVIASR
	public String updateUser(@PathVariable("id") long id, String nombre, Antology antology,
							 BindingResult result, Model model) {
		if (result.hasErrors()) {
			antology.setId(id);
			antology.setName(nombre);
			return "listar";
		}

		anthologyService.save(antology);
		model.addAttribute("anthologies", anthologyService.getAll());
		return "redirect:/listar";
	}*/


}
