/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.randomsearch;

import geneticalgorithms.Statistics.GenerationStatistics;
import geneticalgorithms.cromossomes.ICromossomeFactory;
import geneticalgorithms.populations.Population;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author fkay1
 */
public class RandomSearch {
    private Population population;
    
    private GenerationStatistics stats;
    
    protected final void setPopulation(Population pop) {
        this.population = pop;
    }
    
    public final Population getPopulation() {
        return this.population;
    }
    
    protected final void setStats(GenerationStatistics stats) {
        this.stats = stats;
    }
    
    public final GenerationStatistics getStats() {
        return this.stats;
    }
    
    public RandomSearch(int size, ICromossomeFactory cromossomeFactory) {
        population = new Population(size, 0, 0, cromossomeFactory);
        population.init();
        
        stats = new GenerationStatistics(population.getSize(), 0);
        
        stats.setPopulationDetails(population.getAvgFitness(), population.getBestCromossome(), population.getWorstCromossome());
        
        System.out.println("Melhor cromossomo usando Random search:");
        System.out.println(getPopulation().getBestCromossome());
    }
    
    public RandomSearch() {
        
    }
    
    // Save summary collection to a file for reports
    public final void statisticsToFile(String filename) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(stats.summaryHeader());
            writer.write(stats.summaryLine());
        }
        catch (IOException e) {
            System.out.println("Erro ao gravar arquivo de resutlados: " + filename);
            System.out.println(e.getMessage());
        }
    } 
}
