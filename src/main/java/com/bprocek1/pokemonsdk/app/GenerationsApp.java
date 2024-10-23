package com.bprocek1.pokemonsdk.app;

import com.bprocek1.pokemonsdk.pokemon.PokemonAPI;

/**
 * GenerationsApp - fetches pokemon info using generations URI
 */
public class GenerationsApp {
    public static void main(String[] args) throws Exception {
        if(args.length != 1) {
            System.err.println("Please provide an Pokemon ID or Name");
            System.exit(1);
        }
        System.out.println("Fetching pokemon generation info with id " + args[0]);
        System.out.println(PokemonAPI.getGeneration(args[0]));
    }
}
