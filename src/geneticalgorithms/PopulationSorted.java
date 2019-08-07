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
    public void nextGeneration(){
        super.nextGeneration();
        //this.getCromossomes().sort(Comparator.comparing(c -> c.getFitness()));
        Collections.sort(this.getCromossomes());
    }
    
    
    public PopulationSorted(int size, double probMutate, ICromossomeFactory cromossomeFactory) {
        super(size, probMutate, cromossomeFactory);
    }
    
}
