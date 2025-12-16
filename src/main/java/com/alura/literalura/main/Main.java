package com.alura.literalura.main;

import com.alura.literalura.model.DatosLibro;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "gutendex.com/books";
    private ConvierteDatos conversor = new ConvierteDatos();
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
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
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
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }
    }

    private DatosLibro getDatosLibro() {
        System.out.println("Introduzca el título del libro que desea buscar: ");
        var nombreLibro = scanner.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
        System.out.println(json);
        DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
        return datos;
    }

    private void buscarLibroPorTitulo() {
        DatosLibro datos = getDatosLibro();
        Libro libro = new Libro(datos);
        repositorio.save(libro);
        System.out.println(libro.toString());
    }
}
