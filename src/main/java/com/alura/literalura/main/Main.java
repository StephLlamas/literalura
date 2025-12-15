package com.alura.literalura.main;

import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;

import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    //private final String URL_BASE = "http://www.omdbapi.com/?t=";
    //private final String API_KEY = "&apikey=" + System.getenv("API_KEY_OMDB");
    private LibroRepository repositorio;

    public Main(LibroRepository repository) {
        this.repositorio = repository;
    }

    public void mostrarMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Buscar libros registrados por autor
                    4 - Listar autores registrados
                    5 - Listar autores vivos registrados en un determinado año
                    6 - Listar libros registrados por idioma
                    7 - Listar libros registrados por categoría
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
//                    buscarLibroPorTitulo();
                    break;
                case 2:
//                    listarLibrosRegistrados();
                    break;
                case 3:
//                    buscarLibrosRegistradosPorAutor();
                    break;
                case 4:
//                    listarAutoresRegistrados();
                    break;
                case 5:
//                    listarAutoresVivosRegistradosEnUnDeterminadoAnio();
                    break;
                case 6:
//                    listarLibrosRegistradosPorIdioma();
                    break;
                case 7:
//                    listarLibrosRegistradosPorCategoria();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }
    }


}
