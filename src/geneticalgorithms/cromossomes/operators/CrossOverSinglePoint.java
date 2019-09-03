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
public class CrossOverSinglePoint<T> implements ICrossOver<T>{
    
    public CrossOverSinglePoint() {
        System.out.println("Crossover single point");
    }

    /**
     *
     * @param me One of the father
     * @param other the other fathes
     * @param probCrossover probabilty to crossover
     * @param cromossomeFactory Cromossome factory
     * @param stat the statistics object
     * @return
     */
    @Override
    public Cromossome<T> crossover(Cromossome<T> me, Cromossome<T> other, double probCrossover, 
            ICromossomeFactory<T> cromossomeFactory, GenerationStatistics stat) {
        List<T> newGenes = new ArrayList<>();
        Cromossome<T> newCromossome = cromossomeFactory.getNewCromossome();
        if(Math.random() < probCrossover) {
            stat.incnCromCrossover();
            // get crossover point
            int crossPoint = (new Double(Math.random() * me.getSize())).intValue();
            if(Math.random() < 0.5) {
                newGenes.addAll(me.getGenes().subList(0, crossPoint));
                newGenes.addAll(other.getGenes().subList(crossPoint,me.getSize()));
            }
            else {
                newGenes.addAll(other.getGenes().subList(0, crossPoint));
                newGenes.addAll(me.getGenes().subList(crossPoint,me.getSize()));
            }
        } // only copy genes
        else {
            newGenes.addAll(me.getGenes());
        }
        newCromossome.setGenes(newGenes, false);
        
        return newCromossome;
    }
    
}
