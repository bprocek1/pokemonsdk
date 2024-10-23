package com.bprocek1.pokemonsdk.pokemon.sdk;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;
import java.util.function.Function;

import org.apache.http.HttpStatus;

import javax.net.ssl.SSLContext;

/**
 * Handles lower-level HTTP integrations with the API endpoint
 */
public class PokemonClient {
    private static String POKEMON_CONSUMPTION_ENDPOINT = "https://pokeapi.co/api/v2";

    private String uri;
    private String httpMethod;
    private SSLContext sslContext;

    private Function<String, Object> responseHandler;
    private HttpClient httpClient;

    private PokemonClient(ClientBuilder builder) {
        this.uri = builder.uri;
        this.sslContext = builder.sslContext;
        this.httpMethod = builder.httpMethod;
        this.responseHandler = builder.responseHandler;
        this.httpClient = builder.httpClient;
    }

    public Object invoke() throws IOException, InterruptedException {

        var reqBuilder = HttpRequest.newBuilder()
                .uri(URI.create(MessageFormat.format("{0}/{1}", POKEMON_CONSUMPTION_ENDPOINT, this.uri)))
                .header("Content-Type", "application/json")
                .GET();

        if ("GET".equals(this.httpMethod)) {
            reqBuilder = reqBuilder.GET();
        }

        var httpRequest = reqBuilder.build();

        if(this.httpClient == null) {
            final HttpClient.Builder clientBuilder = HttpClient.newBuilder();

            // Add SSLContext, if any
            if (this.sslContext != null) {
                clientBuilder.sslContext(this.sslContext);
            }

            // Send request to URI
            this.httpClient = clientBuilder.build();
        }

        // Use client to send request
        HttpResponse<String> response = this.httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // Fail of non-200
        if (response.statusCode() != HttpStatus.SC_OK) {
            throw new IllegalStateException( String.format("Response returned with status code %d", response.statusCode()));
        }

        if (this.responseHandler != null) {
            return this.responseHandler.apply(response.body());
        } else {
            return response.body();
        }
    }

    public static class ClientBuilder {

        private String uri;
        //optional parameters
        private String httpMethod;
        private SSLContext sslContext;

        private HttpClient httpClient;

        private Function<String, Object> responseHandler;

        public ClientBuilder withResponseHandler(Function<String, Object> responseHandler) {
            this.responseHandler = responseHandler;
            return this;
        }

        public ClientBuilder withSSLContext(SSLContext sslContext) {
            this.sslContext = sslContext;
            return this;
        }

        public ClientBuilder withUri(String uri) {
            try {
                URI.create(uri);
                this.uri = uri;
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid URI: " + uri);
            }
            return this;
        }

        public ClientBuilder withGET() {
            this.httpMethod = "GET";
            return this;
        }
        public ClientBuilder withHttpClient(HttpClient client) {
            this.httpClient = client;
            return this;
        }

        public PokemonClient build(){
            return new PokemonClient(this);
        }
    }

}