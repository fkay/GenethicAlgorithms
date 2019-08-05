/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Fabricio
 * Classe abstrata que executa o algoritmo genético simples
 * T - classe da populacao
 */
public class SimpleGA{
    private Population population;

    private List<GenerationSummary> summary;
    
    // the best cromossome of all gnerations
    // can be lost due no elitist selection and mutation
    private Cromossome bestCromossomeAll;
    
    public void execute(int generations) {
        if(getPopulation() == null) {
            System.out.println("Nenhuma popuplacao configurada");
            return;
        }
        getPopulation().initPopulation();
        System.out.println(getPopulation().toString());
        bestCromossomeAll = getPopulation().getBestCromossome();
        for (int i = 0; i < generations; i++) {
            getPopulation().nextGeneration();
            System.out.println(getPopulation().toString());
            
            // append the summary for this generatios
            summary.add(new GenerationSummary(getPopulation().getBestCromossome(), 
                            getPopulation().getAvgFitness()));
            
            if (bestCromossomeAll.getFitness() < getPopulation().getBestCromossome().getFitness()) {
                bestCromossomeAll = getPopulation().getBestCromossome();
            }
        }
        System.out.println("Melhor cromossomo das gerações:");
        System.out.println(bestCromossomeAll);
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
    
    /**
     * @return the population
     */
    public final List getSummary() {
        return summary;
    }
    
    public SimpleGA(int sizePopulation, double probMutate, ICromossomeFactory cromossomeFactory) {
        this.population = new Population(sizePopulation, probMutate, cromossomeFactory);
        this.summary = new ArrayList();
    }
    
    public final void summaryToFile(String filename) {
        
    }
}
