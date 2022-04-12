package com.afps.demoWebflux.controller;

import com.afps.demoWebflux.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/personas/")
public class PersonaController {

    private static final Logger log = LoggerFactory.getLogger(PersonaController.class);

    @GetMapping("/mostrar")
    public Mono<Persona> mostrar(){
        return  Mono.just(new Persona(1,"Andres"));
    }

    @GetMapping("/listar")
    public Flux<Persona> listar(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Andres"));
        personas.add(new Persona(2,"Felipe"));

        Flux<Persona> personaFlux =Flux.fromIterable(personas);
        return  personaFlux;
    }

    @GetMapping("/response")
    public Mono<ResponseEntity<Flux<Persona>>> listarEntity(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Andres"));
        personas.add(new Persona(2,"Felipe"));

        Flux<Persona> personaFlux =Flux.fromIterable(personas);

        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(personaFlux));
    }
    @DeleteMapping("/{modo}")
    public Mono<ResponseEntity<Void>> eliminar(@PathVariable("modo") Integer modo){
        return buscarPersona(modo).flatMap(persona -> {
            return eliminar(persona).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

    }


    public Mono<Void> eliminar(Persona p){
        //log.info("Eliminando "+ p.getIdPersona());
        return Mono.empty();
    }

    public Mono<Persona> buscarPersona(Integer modo){
        if(modo==1){
            return  Mono.just(new Persona(1,"Andres"));
        }else{
            return Mono.empty();
        }
    }
}
