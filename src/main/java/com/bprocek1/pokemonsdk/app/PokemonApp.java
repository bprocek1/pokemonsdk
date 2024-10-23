package com.bprocek1.pokemonsdk.app;

import com.bprocek1.pokemonsdk.pokemon.PokemonAPI;

/**
 * GenerationsApp - fetches pokemon info using pokemeon URI
 */
public class PokemonApp {
    public static void main(String[] args) throws Exception {
        if(args.length != 1) {
            System.err.println("Please provide a Pokemon ID or Name");
            System.exit(1);
        }
        System.out.println("Fetching pokemon card with id " + args[0]);
        System.out.println(PokemonAPI.getPokemon(args[0]));
    }
}
