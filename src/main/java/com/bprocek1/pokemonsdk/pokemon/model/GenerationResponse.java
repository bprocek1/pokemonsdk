package com.bprocek1.pokemonsdk.pokemon.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenerationResponse {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // TODO - add more fields from endpoint response
    public String toString() {
        return "GenerationResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
