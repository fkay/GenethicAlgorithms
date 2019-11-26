/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.SGA;

import geneticalgorithms.Statistics.GenerationStatisticsSorted;
import geneticalgorithms.cromossomes.ICromossomeFactory;
import geneticalgorithms.populations.PopulationSorted;
import java.util.List;


/**
 *
 * @author Fabricio
 */
public class SimpleGASorted extends SimpleGA {
    final int bestToSave;
    
    @Override
    public void execute(int generations, boolean quiet, boolean saveSummary, String distFilename, List<Integer> genToSave) {
        if(getPopulation() == null) {
            System.out.println("Nenhuma popuplacao configurada");
            return;
        }
        final int step = (generations > 15 ? generations / 15 : 1);
        
        getPopulation().init();
        
        // always saves distribution for fist generation
        if(distFilename != null)
            getPopulation().saveCromossomes(distFilename + String.format("g%d.csv", 0));
        
        if(!quiet)
            printGenerationInfo(0);
        this.setBestCromossomeAll(getPopulation().getBestCromossome());
        GenerationStatisticsSorted statistic = new GenerationStatisticsSorted(getPopulation().getSize(), 0);
        statistic.setPopulationDetails(getPopulation().getAvgSpecial(), getPopulation().getVarSpecial(), 
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
                {
                    System.out.printf("Geração %d\n", i);
                    // saves distribution on steps if request list is null
                    if(distFilename != null && genToSave == null)
                        getPopulation().saveCromossomes(distFilename + String.format("g%d.csv", i + 1));
                        
                }
            
            // saves distribution on list requested
            if(distFilename != null && genToSave != null && genToSave.contains(i + 1))
                getPopulation().saveCromossomes(distFilename + String.format("g%d.csv", i + 1));

            statistic.setPopulationDetails(getPopulation().getAvgSpecial(), getPopulation().getVarSpecial(), 
                getPopulation().getCromossomes().subList(0, bestToSave), 
                getPopulation().getCromossomes().subList(getPopulation().getCromossomes().size() - bestToSave, getPopulation().getCromossomes().size()));
            // append the summary for this generatios
            this.getStatistics().add(statistic);
            
            if(saveSummary) saveSummarys(i);
            
            if (this.getBestCromossomeAll().getFitness() < getPopulation().getBestCromossome().getFitness()) {
                this.setBestCromossomeAll(getPopulation().getBestCromossome());
            }
        }
        System.out.println("Melhor cromossomo das gerações:");
        System.out.println(this.getBestCromossomeAll());
        
        // saves last distribution if not saved yet
        if (distFilename != null) {
            if(genToSave != null){
                if(!genToSave.contains(generations))
                    getPopulation().saveCromossomes(distFilename + String.format("g%d.csv", generations));
            }
            else {
                if((generations - 1) % step != 0)
                    getPopulation().saveCromossomes(distFilename + String.format("g%d.csv", generations));
            }
        }
    }
    
    
    public SimpleGASorted(int sizePopulation, double probMutate, double probCrossover, 
            int elite, ICromossomeFactory cromossomeFactory, int bestToSave) {
        this.setPopulation(new PopulationSorted(sizePopulation, probMutate, probCrossover, elite, cromossomeFactory));
        this.bestToSave = bestToSave;
    }
    
}
