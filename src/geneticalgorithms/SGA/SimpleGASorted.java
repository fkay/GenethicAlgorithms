/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.SGA;

import geneticalgorithms.Statistics.GenerationStatisticsSorted;
import geneticalgorithms.cromossomes.ICromossomeFactory;
import geneticalgorithms.populations.PopulationSorted;
import java.util.ArrayList;


/**
 *
 * @author Fabricio
 */
public class SimpleGASorted extends SimpleGA {
    final int bestToSave;
    
    @Override
    public void execute(int generations, boolean quiet, boolean saveSummary) {
        if(getPopulation() == null) {
            System.out.println("Nenhuma popuplacao configurada");
            return;
        }
        final int step = generations / 15;
        
        if(saveSummary){
            this.summary = new ArrayList<>();
        }
        
        getPopulation().init();
        if(!quiet)
            printGenerationInfo(0);
        this.setBestCromossomeAll(getPopulation().getBestCromossome());
        GenerationStatisticsSorted statistic = new GenerationStatisticsSorted(getPopulation().getSize(), 0);
        statistic.setPopulationDetails(getPopulation().getAvgFitness(), 
                getPopulation().getCromossomes().subList(0, bestToSave), 
                getPopulation().getCromossomes().subList(getPopulation().getCromossomes().size() - bestToSave, getPopulation().getCromossomes().size()));
        this.getStatistics().add(statistic);
        for (int i = 0; i < generations; i++) {
            statistic = new GenerationStatisticsSorted(getPopulation().getSize(), i + 1);
            getPopulation().nextGeneration(statistic);
            if(!quiet)
                printGenerationInfo(i);
            else
                if( i % step == 0)
                    System.out.printf("Geração %d\n", i);
            statistic.setPopulationDetails(getPopulation().getAvgFitness(), 
                getPopulation().getCromossomes().subList(0, bestToSave), 
                getPopulation().getCromossomes().subList(getPopulation().getCromossomes().size() - bestToSave, getPopulation().getCromossomes().size()));
            // append the summary for this generatios
            this.getStatistics().add(statistic);
            
            if(saveSummary) {
                String s = this.getPopulation().popuplationSummary();
                this.summary.add(s);
            }
            
            if (this.getBestCromossomeAll().getFitness() < getPopulation().getBestCromossome().getFitness()) {
                this.setBestCromossomeAll(getPopulation().getBestCromossome());
            }
        }
        System.out.println("Melhor cromossomo das gerações:");
        System.out.println(this.getBestCromossomeAll());
    }
    
    
    public SimpleGASorted(int sizePopulation, double probMutate, 
            double probCrossover, ICromossomeFactory cromossomeFactory,
            int bestToSave) {
        this.setPopulation(new PopulationSorted(sizePopulation, probMutate, probCrossover, cromossomeFactory));
        this.bestToSave = bestToSave;
    }
    
}
