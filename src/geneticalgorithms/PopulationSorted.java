/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms;

import java.util.Collections;
//import java.util.Comparator;

/**
 *
 * @author Fabricio
 */
public class PopulationSorted extends Population{
    
    @Override
    public void nextGeneration(GenerationStatistics stat){
        super.nextGeneration(stat);
        //this.getCromossomes().sort(Comparator.comparing(c -> c.getFitness()));
        Collections.sort(this.getCromossomes(), Collections.reverseOrder());
    }
    
    @Override
    public void init() {
        super.init();
        Collections.sort(this.getCromossomes(), Collections.reverseOrder());
    }
    
    
    public PopulationSorted(int size, double probMutate, 
            double probCrossover, ICromossomeFactory cromossomeFactory) {
        super(size, probMutate, probCrossover, cromossomeFactory);
    }
    
}
