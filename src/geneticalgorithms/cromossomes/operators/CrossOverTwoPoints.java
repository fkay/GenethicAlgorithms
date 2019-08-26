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
public class CrossOverTwoPoints implements ICrossOver{

    @Override
    public Cromossome crossover(Cromossome me, Cromossome other, double probCrossover, 
            ICromossomeFactory cromossomeFactory, GenerationStatistics stat) {
        List newGenes = new ArrayList();
        Cromossome newCromossome = cromossomeFactory.getNewCromossome();
        if(Math.random() < probCrossover) {
            stat.incnCromCrossover();
            // get crossover point
            int crossPoint1 = (new Double(Math.random() * me.getSize())).intValue();
            int crossPoint2 = (new Double(Math.random() * me.getSize())).intValue();
            
            // order points
            if(crossPoint1 > crossPoint2) {
                int temp = crossPoint1;
                crossPoint1 = crossPoint2;
                crossPoint2 = temp;
            }
            
            if(Math.random() < 0.5) {
                newGenes.addAll(me.getGenes().subList(0, crossPoint1));
                newGenes.addAll(other.getGenes().subList(crossPoint1, crossPoint2));
                newGenes.addAll(me.getGenes().subList(crossPoint2,me.getSize()));
            }
            else {
                newGenes.addAll(other.getGenes().subList(0, crossPoint1));
                newGenes.addAll(me.getGenes().subList(crossPoint1, crossPoint2));
                newGenes.addAll(other.getGenes().subList(crossPoint2,me.getSize()));
            }
        } // only copy genes
        else {
            newGenes.addAll(me.getGenes());
        }
        newCromossome.setGenes(newGenes, false);
        
        return newCromossome;
    }
    
}
