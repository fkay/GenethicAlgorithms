/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.randomsearch;

import geneticalgorithms.Statistics.GenerationStatisticsSorted;
import geneticalgorithms.cromossomes.ICromossomeFactory;
import geneticalgorithms.populations.PopulationSorted;

/**
 *
 * @author fkay1
 */
public class RandomSearchSorted extends RandomSearch{
    
    public RandomSearchSorted(int size, ICromossomeFactory cromossomeFactory, int bestToSave, String distFilename) {
        this.setPopulation(new PopulationSorted(size, 0, 0, 0, cromossomeFactory));
        
        getPopulation().init();
        
        this.setStats(new GenerationStatisticsSorted(getPopulation().getSize(), 0) );
        
        ((GenerationStatisticsSorted)this.getStats()).setPopulationDetails(getPopulation().getAvgFitness(), getPopulation().getVarFitness(),
                getPopulation().getCromossomes().subList(0, bestToSave), 
                getPopulation().getCromossomes().subList(getPopulation().getCromossomes().size() - bestToSave, getPopulation().getCromossomes().size()));    
        System.out.println("Melhor cromossomo usando Random search:");
        System.out.println(getPopulation().getBestCromossome());
        
        if(distFilename != null) {
            this.getPopulation().saveCromossomes(distFilename);
        }
    }
}
