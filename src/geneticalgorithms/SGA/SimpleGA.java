/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.SGA;

import geneticalgorithms.Statistics.GenerationStatistics;
import geneticalgorithms.cromossomes.ICromossomeFactory;
import geneticalgorithms.populations.Population;
import geneticalgorithms.cromossomes.Cromossome;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

    private final List<GenerationStatistics> stats;
    
    protected List<String> summary;
    protected List<String> summaryResume;
    
    // the best cromossome of all gnerations
    // can be lost due no elitist selection and mutation
    private Cromossome bestCromossomeAll;
    
    public void execute(int generations, boolean quiet, boolean saveSummary, String distFilename) {
        this.execute(generations, quiet, saveSummary, distFilename, null);
    }
    
    public void execute(int generations, boolean quiet, boolean saveSummary, String distFilename, List<Integer> geracaoToSave) {
        if(getPopulation() == null) {
            System.out.println("Nenhuma populacao configurada");
            return;
        }
        final int step = (generations > 15 ? generations / 15 : 1);
        
        getPopulation().init();
        
        // always saves distribution for fist generation
        if(distFilename != null)
            getPopulation().saveCromossomes(distFilename + String.format("t%d.csv", 0));
        
        if(!quiet)
            printGenerationInfo(0);
        bestCromossomeAll = getPopulation().getBestCromossome();
        GenerationStatistics statistic = new GenerationStatistics(population.getSize(), 0);
        statistic.setPopulationDetails(population.getAvgFitness(), population.getBestCromossome(), population.getWorstCromossome());
        stats.add(statistic);
        for (int i = 0; i < generations; i++) {
            statistic = new GenerationStatistics(population.getSize(), i + 1);
            getPopulation().nextGeneration(statistic);
            if(!quiet)
                printGenerationInfo(i);
            else
                if( i % step == 0)
                {
                    System.out.printf("Geração %d\n", i);
                    // saves distribution on steps if request list is null
                    if(distFilename != null && geracaoToSave == null)
                        getPopulation().saveCromossomes(distFilename + String.format("t%d.csv", i + 1));
                        
                }
            
            // saves distribution on list requested
            if(distFilename != null && geracaoToSave != null && geracaoToSave.contains(i + 1))
                getPopulation().saveCromossomes(distFilename + String.format("t%d.csv", i + 1));

            
            // append the summary for this generatios
            statistic.setPopulationDetails(population.getAvgFitness(), population.getBestCromossome(), population.getWorstCromossome());
            stats.add(statistic);
            
            if(saveSummary) saveSummarys(i);
            
            // Saves distribution of all generations
            //if(distFilename != null)
            //    getPopulation().saveCromossomes(distFilename + String.format("t%d.csv", i + 1));
            
            if (bestCromossomeAll.getFitness() < getPopulation().getBestCromossome().getFitness()) {
                bestCromossomeAll = getPopulation().getBestCromossome();
            }
        }
        System.out.println("Melhor cromossomo das gerações:");
        System.out.println(bestCromossomeAll);
        
        // saves last distribution if not saved yet
        if (distFilename != null) {
            if(geracaoToSave != null){
                if(!geracaoToSave.contains(generations))
                    getPopulation().saveCromossomes(distFilename + String.format("t%d.csv", generations));
            }
            else {
                if((generations - 1) % step != 0)
                    getPopulation().saveCromossomes(distFilename + String.format("t%d.csv", generations));
            }
        }
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
     * @return the Statisitic
     */
    public final List getStatistics() {
        return stats;
    }
    
    /**
     * @return the Best Cromossome of all generations
     */
    public final Cromossome getBestCromossomeAll() {
        return this.bestCromossomeAll;
    }
    
    /**
     * @param best 
     */
    public final void setBestCromossomeAll(Cromossome best) {
        this.bestCromossomeAll = best;
    }
    
    // Basic constructor
    public SimpleGA(int sizePopulation, double probMutate, 
            double probCrossover, ICromossomeFactory cromossomeFactory) {
        this.population = new Population(sizePopulation, probMutate, 
                probCrossover, cromossomeFactory);
        this.stats = new ArrayList();
    }
    
    // for subclasse have his own constructor when needed
    protected SimpleGA() {
        this.stats = new ArrayList();
    }
    
    
    // Save summary collection to a file for reports
    public final void statisticsToFile(String filename) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(stats.get(0).summaryHeader());
            for (GenerationStatistics gs : stats) {
                writer.write(gs.summaryLine());
            }
        }
        catch (IOException e) {
            System.out.println("Erro ao gravar arquivo de resutlados: " + filename);
            System.out.println(e.getMessage());
        }
    }
    
    public final void summaryToFile(String filename) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String summ : summary) {
                writer.write(summ);
            }
        }
        catch (IOException e) {
            System.out.println("Erro ao gravar arquivo de resumo: " + filename);
            System.out.println(e.getMessage());
        }
    }
    
    public final void summaryResumeToFile(String filename) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String summ : summaryResume) {
                writer.write(summ);
            }
        }
        catch (IOException e) {
            System.out.println("Erro ao gravar arquivo de resumo: " + filename);
            System.out.println(e.getMessage());
        }
    }
    
    protected void saveSummarys(int geracao) {
        if(geracao == 0) {
            this.summary = new ArrayList<>();
            this.summaryResume = new ArrayList<>();
        }
        
        this.summary.add(this.getPopulation().popuplationSummary());
        this.summaryResume.add(this.getPopulation().getPreviousGenerationSummary());
    }
}
