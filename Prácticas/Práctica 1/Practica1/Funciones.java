import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase con la que se manipularan las categorias 
 * @author Jose Guadalupe de Jesus Marin Parra
 */
public class Funciones{
  private Category category;
  ArrayList lista = new ArrayList();

  /**
   * Metodo constructor que crea una categoria
   * @param category Categoria que se creara
   */
  public void createCategory(Category category){
    this.category = category;
    this.lista.add(category);
  }

  /**
   * Metodo con el que obtenemos la lista de categorias
   * @return Lista con las categorias REGISTRADAS
   */
  public ArrayList<Category> getCategories(){
    if (lista.size() != 0) {
      return lista;
    }else {
      System.out.println("No existen categorias registradas");
    }
    return lista;
  }

  /**
   * Metodo para buscar la categoria de acuerdo a un id dado
   * @param category_id Id para buscar la categoria asociada
   * @return La categoria con el id asociado
   */
  public Category getCategory(int category_id){
    for (int i=0; i<this.getCategories().size(); i++) {
      if (this.getCategories().get(i).getCategoryId() == category_id) {
        return this.getCategories().get(i);
      }
    }
    System.out.println("No existe una categoria con el id ingresado");
    return null;
  }

  /**
   * Metodo para borrar una categoria segun un id dado
   * @para category_id Id con el que se borrara la categoria asociada
   */
  public void deleteCategory(int category_id){
    for (int i=0; i<this.getCategories().size(); i++) {
      if (this.getCategories().get(i).getCategoryId() == category_id) {
        this.getCategories().remove(i);
        System.out.println("Categoria eliminada.");
      }else {
        System.out.println("No existe una categoria con el id ingresado");
      }
    }
  }

  /**
   * Método para evitar errores cuando el usuario ingrese la opcion en el menu
   */
  public static int daEleccion(){
      boolean error = false;
      int eleccion = 0;
      Scanner en = new Scanner(System.in);
      try {
        eleccion = en.nextInt();
      }catch (Exception e) {
        System.out.println("Dato introducido no correcto.");
        error = true;
      }
      finally{
        if(error == true){
          return  daEleccion();
        }else{
          return eleccion;
        }
      }
  }

  public static void main(String[] args) {

    Funciones fun = new Funciones();
    int opcion = 0;
    Scanner in = new Scanner(System.in);

    System.out.println("|||||||||||||||   B I E N V E N I D O   |||||||||||||||");

    do {
      System.out.println();
      System.out.println("Bienvenido \n¿Qué deseas hacer?");
      System.out.println("1. Crear un objeto Category y registrarlo en la lista.\n2. Mostrar la lista de todas las categorias registradas."+
      "\n3. Buscar una categoria con su id.\n4. Eliminar una categoria con su id.\n5. Salir.");
      opcion = daEleccion();
      System.out.println();
      switch (opcion) {
        case 1:
            System.out.println("Ingresa el nombre de la categoria");
            String nombreCat = in.nextLine();
            boolean cond = false;
            int idCat;
            do {
              System.out.println("Ingresa el id de la categoria.");
              idCat = daEleccion();
              if (fun.getCategories().size() != 0) {
                if (fun.getCategory(idCat) != null) {
                  System.out.println("Id ya registrado. Intenta otra vez.");
                  cond = false;
                }else if (idCat <= 0) {
                  System.out.println("No puedes registrar un ID cero o negativo. Intenta otra vez.");
                  cond = false;
                } else {
                  System.out.println("Asi que se agrega.");
                  cond = true;
                }
              }else {
                System.out.println("Asi que se agrega.");
                cond = true;
              }
            } while (cond == false);
            Category cat = new Category(nombreCat,idCat);
            fun.createCategory(cat);
        break;

        case 2:
            if (fun.getCategories().size() != 0) {
              System.out.println("CATEGORIAS REGISTRADAS.");
              for (int i=0; i<fun.getCategories().size(); i++) {
                System.out.println("Categoria: " + fun.getCategories().get(i).getCategory() + ". Id: " + fun.getCategories().get(i).getCategoryId());
              }
            }
        break;

        case 3:
            System.out.println("Ingresa el ID para buscar la categoria.");
            int idBuscar = daEleccion();
            if (fun.getCategories().size() != 0) {
              System.out.println(fun.getCategory(idBuscar));
            }
        break;

        case 4:
            System.out.println("Ingresa el ID para borrar la categoria.");
            int idBorrar = daEleccion();
            if (fun.getCategories().size() != 0) {
              fun.deleteCategory(idBorrar);
            }
        break;

        case 5:
            System.out.println("Hasta la proxima :D");
            System.exit(1);
        break;

        default:
            System.out.println("Respuesta inválida, intenta nuevamente.");
      }
    } while (opcion != 5);
  }
}
