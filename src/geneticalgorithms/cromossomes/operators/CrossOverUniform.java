/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.cromossomes.operators;

import geneticalgorithms.Statistics.GenerationStatistics;
import geneticalgorithms.cromossomes.Cromossome;
import geneticalgorithms.cromossomes.ICromossomeFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fkay1
 */
public class CrossOverUniform<T> implements ICrossOver<T>{
    
    public CrossOverUniform(){
        System.out.println("Crossover uniform");
    }

    @Override
    public Cromossome<T> crossover(Cromossome<T> me, Cromossome<T> other, double probCrossover, 
            ICromossomeFactory<Cromossome<T>> cromossomeFactory, GenerationStatistics stat) {
        List<T> newGenes = new ArrayList<>();
        Cromossome<T> newCromossome = cromossomeFactory.getNewCromossome();
        if(Math.random() < probCrossover) {
            stat.incnCromCrossover();

            for (int i = 0; i < me.getSize(); i++) {
                if(Math.random() < 0.5) {
                    newGenes.add(me.getGenes().get(i));
                }
                else {
                    newGenes.add(other.getGenes().get(i));                
                }
            }
        } // only copy genes
        else {
            newGenes.addAll(me.getGenes());
        }
        newCromossome.setGenes(newGenes, false);
        
        return newCromossome;

    }
    
}
