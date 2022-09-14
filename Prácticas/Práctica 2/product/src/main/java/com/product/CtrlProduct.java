package com.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

@RestController
public class CtrlProduct {
	
	@GetMapping("/Category")
	public ArrayList<Category> categorias() {
		Category c1 = new Category("Abarrotes",1);
		Category c2 = new Category("Electronica",2);
		Category c3 = new Category("Linea Blanca",3);
		ArrayList<Category> listaCategoria = new ArrayList<Category>();
		listaCategoria.add(c1);
		listaCategoria.add(c2);
		listaCategoria.add(c3);
		return listaCategoria;
	}
}
