package com.alura.literalura.service;

import com.alura.literalura.dto.AutorDTO;
import com.alura.literalura.dto.LibroDTO;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.DatosLibro;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;

    private IConvierteDatos convierteDatos;

    //Guarda libro proveniente de la API, asegurando que los autores no se dupliquen.
    public Libro guardarLibro(DatosLibro datosLibro) {
        if (datosLibro == null) {
            throw new IllegalArgumentException("Datos nulos");
        }

        //Convertir autores de DatosAutor -> Autor, reutilizando si ya existen
        List<Autor> autores = datosLibro.autores().stream()
                .map(a -> autorRepository.findByNombreIgnoreCase(a.nombre())
                        .orElseGet(() -> {
                            Autor nuevoAutor = new Autor(a.nombre(), a.nacimiento(), a.fallecimiento());
                            return autorRepository.save(nuevoAutor);
                        }))
                .collect(Collectors.toList());

        //Crear entidad Libro
        Libro libro = new Libro();
        libro.setTitulo(datosLibro.titulo());
        libro.setAutores(autores);
        libro.setIdioma(datosLibro.idiomas().isEmpty() ? null : datosLibro.idiomas().getFirst());
        libro.setNumeroDeDescargas(datosLibro.numeroDeDescargas());

        //Guardar libro
        return libroRepository.save(libro);
    }

    public List<LibroDTO> obtenerTodosLosLibros() {
        return convierteDatos(libroRepository.findAll());
    }

    public List<AutorDTO> obtenerTodosLosAutores() {
        return convierteDatosAutor(autorRepository.findAll());
    }

    public List<LibroDTO> obtenerLosLibrosDeUnAutor(String nombre) {
        return convierteDatos(libroRepository.findByAutores_Nombre(nombre));
    }

    public Optional<Autor> obtenerAutorPorNombre(String nombre) {
        return autorRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<AutorDTO> obtenerAutoresVivosEn(Integer anio) {
        return convierteDatosAutor(autorRepository.encontrarAutoresVivosEn(anio));
    }

    public List<LibroDTO> obtenerTodosLosLibrosEnUnIdioma(String idioma) {
        return convierteDatos(libroRepository.findByIdioma(idioma));
    }

    public List<AutorDTO> convierteDatosAutor(List<Autor> autores) {
        return autores.stream()
                .map(a -> new AutorDTO(a.getNombre(), a.getNacimiento(), a.getFallecimiento()))
                .collect(Collectors.toList());
    }

    public List<LibroDTO> convierteDatos(List<Libro> libros) {
        return libros.stream()
                .map(l -> new LibroDTO(l.getId(), l.getTitulo(), l.getAutores(), l.getIdioma(),
                        l.getNumeroDeDescargas()))
                .collect(Collectors.toList());
    }
}
