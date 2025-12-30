package com.alura.literalura.main;

import com.alura.literalura.dto.AutorDTO;
import com.alura.literalura.dto.GutendexResponseDTO;
import com.alura.literalura.dto.LibroDTO;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.DatosLibro;
import com.alura.literalura.model.Libro;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;
import com.alura.literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Main {
    private final LibroService libroService;
    private final ConsumoAPI consumoApi;
    private final ConvierteDatos conversor;

    private Scanner scanner = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com/books/?search=";

    @Autowired
    public Main(LibroService libroService, ConsumoAPI consumoApi, ConvierteDatos conversor) {
        this.libroService = libroService;
        this.consumoApi =  consumoApi;
        this.conversor = conversor;
    }

    public void principal() {
        var opcion = -1;
        while (opcion != 0) {
            mostrarMenu();
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opción no válida. Intente de nuevo.");
                scanner.nextLine();
                continue; // Volver al inicio del ciclo
            }

            if (opcion > 0 && opcion < 7) {
                switch (opcion) {
                    case 1:
                        buscarLibroPorTitulo();
                        break;
                    case 2:
                        listarLibrosRegistrados();
                        break;
                    case 3:
                    buscarLibrosRegistradosPorAutor();
                        break;
                    case 4:
                    listarAutoresRegistrados();
                        break;
                    case 5:
                    listarAutoresVivosRegistradosEnUnDeterminadoAnio();
                        break;
                    case 6:
                    listarLibrosRegistradosPorIdioma();
                        break;
                    default:
                        System.out.println("Opción inválida. Intenta de nuevo.");
                }
            } else if (opcion != 0) {
                System.out.println("Opción no valida. Intente de nuevo.");
            }
        }
        System.out.println("Gracias por su preferencia, ¡vuelva pronto!");
    }

    private static void mostrarMenu() {
        System.out.println("--------- MENÚ ----------");
        System.out.println("""
                1 - Buscar libro por título
                2 - Listar libros registrados
                3 - Buscar libros registrados por autor
                4 - Listar autores registrados
                5 - Listar autores vivos registrados en un determinado año
                6 - Listar libros registrados por idioma
                
                0 - Salir
                """);
    }

    private DatosLibro getDatosLibro() {
        System.out.println("Introduzca el título del libro que desea buscar: ");
        var nombreLibro = scanner.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));

        System.out.println("JSON recibido: " + json);

        //Mapear al contenedor
        GutendexResponseDTO response = conversor.obtenerDatos(json, GutendexResponseDTO.class);

        //Tomar el primer libro
        if (response == null || response.results() == null || response.results().isEmpty()) {
            System.out.println("No resultados encontrados.");
            return null;
        }
        return response.results().getFirst();
    }

    private void buscarLibroPorTitulo() {
        DatosLibro datos = getDatosLibro();
        if (datos == null) {
            System.out.println("No existe el libro que desea buscar.");
            return; //Salir sin romper la app
        }
        //Delegar al servicio para guardar el libro
        Libro libroGuardado = libroService.guardarLibro(datos);
        System.out.println(libroGuardado);
    }

    private void listarLibrosRegistrados() {
        List<LibroDTO> libros = libroService.obtenerTodosLosLibros();
        libros.stream()
                .sorted(Comparator.comparing(LibroDTO::titulo))
                .forEach(l -> {
                    String s = "---------- LIBRO ----------\n" +
                        "Título: " + l.titulo() + "\n" +
                        "Autor: " + l.autores().stream()
                            .map(Autor::getNombre)
                            .collect(Collectors.joining(", "))  + "\n" +
                        "Idioma: " + l.idioma() + "\n" +
                        "Número de descargas: " + l.numeroDeDescargas() + "\n" +
                        "++++++++++++++++++++++++++\n";
                    System.out.println(s);
                });
    }

    private void buscarLibrosRegistradosPorAutor() {
        listarAutoresRegistrados();
        System.out.println("Escribe el apellido y nombre del autor/a del cual deseas buscar sus libros.");
        var nombreAutor = scanner.nextLine();

        Optional<Autor> autor = libroService.obtenerAutorPorNombre(nombreAutor.trim());
        if (autor.isPresent()) {
            var autorEncontrado = autor.get();
            List<LibroDTO> librosDelAutor = libroService.obtenerLosLibrosDeUnAutor(autorEncontrado.getNombre());
            System.out.println("----- LIBROS REGISTRADOS DE " + autorEncontrado.getNombre().toUpperCase() + " -----");
            librosDelAutor.stream()
                    .sorted(Comparator.comparing(LibroDTO::titulo))
                    .forEach(l -> {
                        String s = "Título: " + l.titulo() + "\n" +
                                "Idioma: " + l.idioma() + "\n" +
                                "Número de descargas: " + l.numeroDeDescargas() + "\n" +
                                "++++++++++++++++++++++++++\n";
                        System.out.println(s);
                    });
        } else {
            System.out.println("No existe el autor que desea buscar.");
        }
    }

    private void listarAutoresRegistrados() {
        List<AutorDTO> autores = libroService.obtenerTodosLosAutores();
        System.out.println("---------- LISTA DE AUTORES REGISTRADOS ----------");
        autores.stream()
                .sorted(Comparator.comparing(AutorDTO::nombre))
                .forEach(a -> {
                    String s = a.nombre() +
                            (a.nacimiento() != null ? " (" + a.nacimiento() : "" ) +
                            (a.fallecimiento() != null ? " - " + a.fallecimiento() +")" : ")");
                    System.out.println(s);
                });
        System.out.println("++++++++++++++++++++++++++\n");
    }

    private void listarAutoresVivosRegistradosEnUnDeterminadoAnio() {
        System.out.println("Ingrese un año para averiguar cuáles autores se encontraban vivos en esa época:");
        int anio = 0;
        try {
            anio = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Opción no válida.");
            scanner.nextLine();
        }

        List<AutorDTO> autoresVivos = libroService.obtenerAutoresVivosEn(anio);
        if (autoresVivos.isEmpty()) {
            System.out.println("No hay autores registrados vivos en ese año.");
        }else {
            System.out.println("---------- LISTA DE AUTORES VIVOS EN EL AÑO " + anio + " ----------");
            autoresVivos.stream()
                    .sorted(Comparator.comparing(AutorDTO::nombre))
                    .forEach(a -> {
                        String s = a.nombre() +
                                (a.nacimiento() != null ? " (" + a.nacimiento() : "" ) +
                                (a.fallecimiento() != null ? " - " + a.fallecimiento() +")" : ")");
                        System.out.println(s);
                    });
            System.out.println("++++++++++++++++++++++++++\n");
        }
    }


    private void listarLibrosRegistradosPorIdioma() {
        System.out.println("Ingrese el idioma para buscar los libros:");
        System.out.println("""
                es - español
                en - inglés
                fi - finés
                fr - francés
                pt - portugués
                """);
        var idioma = scanner.nextLine();

        List<LibroDTO> librosEnIdioma = libroService.obtenerTodosLosLibrosEnUnIdioma(idioma);
        if (librosEnIdioma.isEmpty()) {
            System.out.println("No hay libros registrados en ese idioma");
        } else {
            System.out.println("---------- LISTA DE LIBROS EN IDIOMA " + idioma.toUpperCase() + " ----------");
            System.out.println("Se han encontrado " + librosEnIdioma.size() + " libros en ese idioma.");
            librosEnIdioma.stream()
                    .sorted(Comparator.comparing(LibroDTO::titulo))
                    .forEach(l -> {
                        String s = "---------- LIBRO ----------\n" +
                                "Título: " + l.titulo() + "\n" +
                                "Autor: " + l.autores().stream()
                                .map(Autor::getNombre)
                                .collect(Collectors.joining(", "))  + "\n" +
                                "Número de descargas: " + l.numeroDeDescargas() + "\n" +
                                "++++++++++++++++++++++++++\n";
                        System.out.println(s);
                    });
            System.out.println("++++++++++++++++++++++++++\n");
        }
    }
}
