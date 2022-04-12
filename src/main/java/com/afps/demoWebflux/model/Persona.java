package com.afps.demoWebflux.model;


import lombok.*;

@Data
public class Persona {
    private Integer idPersona;
    private String nombre;

    public Persona(Integer idPersona, String nombre) {
        this.idPersona = idPersona;
        this.nombre = nombre;
    }
}

