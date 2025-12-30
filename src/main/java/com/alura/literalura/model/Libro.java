package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores;
    private String idioma;
    private Integer numeroDeDescargas;

    public Libro() {}

    public Libro(String titulo, List<Autor> autores, String idioma, Integer numeroDeDescargas) {
        this.titulo = titulo;
        this.autores = autores;
        this.idioma = idioma;
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return "----------LIBRO----------\n" +
                "Título: " + titulo + "\n" +
                "Autor: " + autores.stream()
                .map(Autor::getNombre)
                .collect(Collectors.joining(", ")) + "\n" +
                "Idioma: " + idioma + "\n" +
                "Número de descargas: " + numeroDeDescargas + "\n" +
                "++++++++++++++++++++++++++";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autor) {
        this.autores = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }
}
