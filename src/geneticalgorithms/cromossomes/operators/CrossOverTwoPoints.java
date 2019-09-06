/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.cromossomes.operators;

import geneticalgorithms.Statistics.GenerationStatistics;
import geneticalgorithms.cromossomes.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author fkay1
 */
public class CrossOverTwoPoints implements ICrossOver{
    
    public CrossOverTwoPoints() {
        System.out.println("Crossover two points");
    }

    @Override
    public Cromossome crossover(Cromossome me, Cromossome other, double probCrossover, 
            GenerationStatistics stat) {
        List newGenes = new ArrayList();
        Cromossome newCromossome = me.getNewCromossome();
        
        List<Integer> genesH = new ArrayList<>();
        
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
                
                genesH.addAll(Collections.nCopies(crossPoint1,1));
                genesH.addAll(Collections.nCopies(crossPoint2 - crossPoint1, 2));
                genesH.addAll(Collections.nCopies(me.getSize() - crossPoint2, 1));
            }
            else {
                newGenes.addAll(other.getGenes().subList(0, crossPoint1));
                newGenes.addAll(me.getGenes().subList(crossPoint1, crossPoint2));
                newGenes.addAll(other.getGenes().subList(crossPoint2,me.getSize()));
                
                genesH.addAll(Collections.nCopies(crossPoint1,2));
                genesH.addAll(Collections.nCopies(crossPoint2 - crossPoint1, 1));
                genesH.addAll(Collections.nCopies(me.getSize() - crossPoint2, 2));
            }
        } // only copy genes
        else {
            newGenes.addAll(me.getGenes());
            genesH.addAll(Collections.nCopies(me.getSize(), 1));
        }
        newCromossome.setGenes(newGenes, false);
        newCromossome.setGenesHeritage(genesH);
        
        return newCromossome;
    }
    
}
