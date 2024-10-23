package com.bprocek1.pokemonsdk.pokemon;

import com.bprocek1.pokemonsdk.pokemon.error.ResourceNotFoundError;
import com.bprocek1.pokemonsdk.pokemon.model.GenerationResponse;
import com.bprocek1.pokemonsdk.pokemon.model.PokemonResponse;
import com.bprocek1.pokemonsdk.pokemon.sdk.PokemonClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.MessageFormat;

public class PokemonAPI {

    public static PokemonResponse getPokemon(String idOrName) throws ResourceNotFoundError {
        var client = new PokemonClient.ClientBuilder()
            .withGET()
            .withUri(MessageFormat.format("pokemon/{0}", idOrName))
            .withResponseHandler(responseBody -> {
                    try {
                        return new ObjectMapper().readValue(responseBody, PokemonResponse.class);

                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
            .build();
        try {
            return (PokemonResponse) client.invoke();
        } catch (IllegalStateException e) {
            if (e.getMessage().contains("404")) {
                throw new ResourceNotFoundError("Unable to find resource with id or name " + idOrName);
            } else {
                throw new RuntimeException(e);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static GenerationResponse getGeneration(String idOrName) throws ResourceNotFoundError {
        var client = new PokemonClient.ClientBuilder()
                .withGET()
                .withUri(MessageFormat.format("generation/{0}", idOrName))
                .withResponseHandler(responseBody -> {
                    try {
                        return new ObjectMapper().readValue(responseBody, GenerationResponse.class);

                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .build();
        try {
            return (GenerationResponse) client.invoke();
        } catch (IllegalStateException e) {
            if (e.getMessage().contains("404")) {
                throw new ResourceNotFoundError("Unable to find resource with id or name " + idOrName);
            } else {
                throw new RuntimeException(e);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
