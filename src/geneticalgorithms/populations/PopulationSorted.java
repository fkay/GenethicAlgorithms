/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.populations;

import geneticalgorithms.Statistics.GenerationStatistics;
import geneticalgorithms.cromossomes.ICromossomeFactory;
import java.util.Collections;
//import java.util.Comparator;

/**
 *
 * @author Fabricio
 * @param <T> genes type
 */
public class PopulationSorted<T> extends Population<T>{
    
    @Override
    public void nextGeneration(GenerationStatistics<T> stat){
        super.nextGeneration(stat);
        //this.getCromossomes().sort(Comparator.comparing(c -> c.getFitness()));
        Collections.sort(this.getCromossomes(), Collections.reverseOrder());
    }
    
    @Override
    public void init() {
        super.init();
        Collections.sort(this.getCromossomes(), Collections.reverseOrder());
    }
    
    /**
     *
     * @param size size of population
     * @param probMutate probability to mutate each gene
     * @param probCrossover probabilty to crossover
     * @param cromossomeFactory factory for crmossomes
     */
    public PopulationSorted(int size, double probMutate, 
            double probCrossover, ICromossomeFactory<T> cromossomeFactory) {
        super(size, probMutate, probCrossover, cromossomeFactory);
    }
    
}
