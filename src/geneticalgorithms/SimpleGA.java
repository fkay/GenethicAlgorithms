/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

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
    
    public void execute(int generations, boolean quiet) {
        if(getPopulation() == null) {
            System.out.println("Nenhuma popuplacao configurada");
            return;
        }
        final int step = generations / 15;
        
        getPopulation().initPopulation();
        if(!quiet)
            printGenerationInfo(0);
        bestCromossomeAll = getPopulation().getBestCromossome();
        for (int i = 0; i < generations; i++) {
            getPopulation().nextGeneration();
            if(!quiet)
                printGenerationInfo(i);
            else
                if( i % step == 0)
                    System.out.printf("Geração %d\n", i);
            
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
    
    // Print generation information (can be override to show other things)
    public void printGenerationInfo(int generation) {
        System.out.printf("Geração %d\n", generation);
        System.out.println(getPopulation().toString());
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
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Geracao;" + summary.get(0).summaryHeader());
            int i = 1;
            for (GenerationSummary gs : summary) {
                writer.write(String.format("%d;",i++));
                writer.write(gs.summaryLine());
            }
        }
        catch (IOException e) {
            System.out.println("Erro ao gravar arquivo de resutlados: " + filename);
            System.out.println(e.getMessage());
        }
    }
}
