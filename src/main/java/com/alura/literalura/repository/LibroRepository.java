package com.alura.literalura.repository;

import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    //Buscar libros por nombre de Autor
    List<Libro> findByAutores_Nombre(String nombre);

    //Buscar libros por idioma
    List<Libro> findByIdioma(String idioma);
}
