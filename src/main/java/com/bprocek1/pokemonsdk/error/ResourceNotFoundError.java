package com.bprocek1.pokemonsdk.error;

public class ResourceNotFoundError extends Exception{
    public ResourceNotFoundError(String message) {
        super(message);
    }

    public ResourceNotFoundError(String message, Throwable cause) {
        super(message,cause);
    }
}
