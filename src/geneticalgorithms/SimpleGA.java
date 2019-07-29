/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms;

/**
 *
 * @author Fabricio
 * Classe abstrata que executa o algoritmo genético simples
 * T - classe da populacao
 */
public abstract class SimpleGA{
    private Population population;
    
    public void execute(int generations) {
        population.initPopulation();
        System.out.println(population.toString());
        for (int i = 0; i < generations; i++) {
            population.nextGeneration();
            System.out.println(population.toString());
        }
    }
}
