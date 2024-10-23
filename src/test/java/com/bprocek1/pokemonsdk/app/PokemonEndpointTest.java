package com.bprocek1.pokemonsdk.app;

import com.bprocek1.pokemonsdk.pokemon.model.GenerationResponse;
import com.bprocek1.pokemonsdk.pokemon.sdk.PokemonClient;
import com.bprocek1.pokemonsdk.pokemon.model.PokemonResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Unit test for simple App.
 */
public class PokemonEndpointTest {

    /**
     * Test Pokemon endpoint
     */
    @Test
    public void testPokemonInfo() throws Exception {
        HttpClient httpClientMock = Mockito.mock(HttpClient.class);

        // Set up the mock behavior
        HttpResponse<String> response = Mockito.mock(HttpResponse.class);
        when(response.body()).thenReturn("{ \"id\":111222 }");

        when(httpClientMock.send(Mockito.any(HttpRequest.class), Mockito.any(HttpResponse.BodyHandler.class))).thenReturn(response);
        when(response.statusCode()).thenReturn(200);

        var client = new PokemonClient.ClientBuilder()
                .withGET()
                .withHttpClient(httpClientMock)
                .withUri("pokemon/111222")
                .withResponseHandler(responseBody -> {
                    try {
                        return new ObjectMapper().readValue(responseBody, PokemonResponse.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .build();

        var resp = (PokemonResponse)client.invoke();
        System.out.println(resp.getName());

        // Verify response received
        assertEquals(resp.getId(), "111222");
    }

    @Test
    public void testGenerationsInfo() throws Exception {
        HttpClient httpClientMock = Mockito.mock(HttpClient.class);

        // Set up the mock behavior
        HttpResponse<String> response = Mockito.mock(HttpResponse.class);
        when(response.body()).thenReturn("{ \"id\":111222 }");

        when(httpClientMock.send(Mockito.any(HttpRequest.class), Mockito.any(HttpResponse.BodyHandler.class))).thenReturn(response);
        when(response.statusCode()).thenReturn(200);

        var client = new PokemonClient.ClientBuilder()
                .withGET()
                .withHttpClient(httpClientMock)
                .withUri("generations/35")
                .withResponseHandler(responseBody -> {
                    try {
                        return new ObjectMapper().readValue(responseBody, GenerationResponse.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .build();

        var resp = (GenerationResponse)client.invoke();
        System.out.println(resp.getName());

        // Verify response received
        assertEquals(resp.getId(), "111222");
    }
}
