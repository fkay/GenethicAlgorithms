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
        if(getPopulation() == null) {
            System.out.println("Nenhuma popuplacao configurada");
            return;
        }
        getPopulation().initPopulation();
        System.out.println(getPopulation().toString());
        for (int i = 0; i < generations; i++) {
            getPopulation().nextGeneration();
            System.out.println(getPopulation().toString());
        }
    }

    /**
     * @return the population
     */
    public final Population getPopulation() {
        return population;
    }

    /**
     * @param population the population to set
     */
    public final void setPopulation(Population population) {
        this.population = population;
    }
}
