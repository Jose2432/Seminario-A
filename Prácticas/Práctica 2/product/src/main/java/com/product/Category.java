package com.product;

public class Category {

	  private int category_id;
	  private String category;

	  /**
	   * Metodo constructor que crea una categoria con datos ingresados
	   * @param category Nombre de la categorias
	   * @param category_id Id de la categorias
	   */
	  public Category(String category, int category_id){
	    this.category = category;
	    this.category_id = category_id;
	  }

	  /**
	   * Metodo con el que obtenemos el nombre de la categorias
	   * @return El nombre de la categoria
	   */
	  public String getCategory(){
	    return this.category;
	  }

	  /**
	   * Metodo con el que obtenemos el id
	   * @return El id de la categoria
	   */
	  public int getCategoryId(){
	    return this.category_id;
	  }

	  /**
	   * Convierte los datos de la categoria en una cadena de texto
	   * @return Una cadena de texto con los datos de la categoria
	   */
	  public String toString(){
	    return "Categoria: " + this.category + " con Id: " + category_id;
	  }
	  
}
