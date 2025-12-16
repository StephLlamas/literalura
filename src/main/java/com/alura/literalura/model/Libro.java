package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores;
    private String idioma;
    private Integer numeroDeDescargas;

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.autores = datosLibro.autores();
        this.idioma = datosLibro.idioma();
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }

    @Override
    public String toString() {
        return "Título: '" + titulo + '\'' +
                "Autor: '" + autores + '\'' +
                "Idioma: '" + idioma + '\'' +
                "Número de descargas: '" + numeroDeDescargas + '\'';
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

    public List<Autor> getAutor() {
        return autores;
    }

    public void setAutor(List<Autor> autor) {
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
