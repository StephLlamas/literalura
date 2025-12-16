package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")

public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer nacimiento;
    private Integer fallecimiento;
    private String nombre;
    @ManyToMany(mappedBy = "autores")
    private List<Libro> libros;

    public Autor(DatosAutor datosAutor) {
        this.nacimiento = datosAutor.nacimiento();
        this.fallecimiento = datosAutor.fallecimiento();
        this.nombre = datosAutor.nombre();
    }

    @Override
    public String toString() {
        return "Nombre:'" + nombre + '\'' +
                "Fecha de nacimiento: " + nacimiento + '\'' +
                "Fecha de fallecimiento: " + fallecimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Integer nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Integer getFallecimiento() {
        return fallecimiento;
    }

    public void setFallecimiento(Integer fallecimiento) {
        this.fallecimiento = fallecimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
