package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("birth_year") Integer nacimiento,
        @JsonAlias("death_year") Integer fallecimiento,
        @JsonAlias("name") String nombre
) {
}
