/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.cromossomes.operators;

import geneticalgorithms.Statistics.GenerationStatistics;
import geneticalgorithms.cromossomes.Cromossome;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fkay1
 */
public class CrossOverUniform implements ICrossOver{
    
    public CrossOverUniform(){
        System.out.println("Crossover uniform");
    }

    @Override
    public Cromossome crossover(Cromossome me, Cromossome other, double probCrossover, 
            GenerationStatistics stat) {
                List newGenes = new ArrayList();
        Cromossome newCromossome = me.getNewCromossome();
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
