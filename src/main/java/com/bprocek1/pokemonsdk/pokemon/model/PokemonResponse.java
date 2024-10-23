package com.bprocek1.pokemonsdk.pokemon.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonResponse {
    private String id;
    private String name;
    private String base_experience;

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

    public String getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(String base_experience) {
        this.base_experience = base_experience;
    }

    // TODO - add more fields from endpoint response
    public String toString() {
        return "PokemonResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", base_experience='" + base_experience + '\'' + '}';
    }

}
