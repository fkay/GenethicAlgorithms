/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.populations;

import geneticalgorithms.Statistics.GenerationStatistics;
import geneticalgorithms.cromossomes.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//import java.util.Comparator;

/**
 *
 * @author Fabricio
 */
public class PopulationSorted extends Population{
    private int elite;
    
    @Override
    public void nextGeneration(GenerationStatistics stat){
        List<Cromossome> elitePop = new ArrayList();
        
        // separate elite population
        for(int i=0; i < elite; i++){
            elitePop.add(this.getCromossomes().get(i));
        }
        
        super.nextGeneration(stat);
        
        // substitui cromossomos definidos na elite
        for (int i = 0; i < elite; i++) {
            getCromossomes().set(i, elitePop.get(i));
        }
        
        // Calc fitness sum
        sumFitness();
        // Procura o melhor cromossomo
        this.classifyFitness();
        
        //this.getCromossomes().sort(Comparator.comparing(c -> c.getFitness()));
        Collections.sort(this.getCromossomes(), Collections.reverseOrder());
    }
    
    @Override
    public void init() {
        super.init();
        Collections.sort(this.getCromossomes(), Collections.reverseOrder());
    }
    
    
    public PopulationSorted(int size, double probMutate, 
            double probCrossover, int elite, ICromossomeFactory cromossomeFactory) {
        super(size, probMutate, probCrossover, cromossomeFactory);
        this.elite = elite;
    }
    
    public int getElite() {
        return this.elite;
    }
    
}
