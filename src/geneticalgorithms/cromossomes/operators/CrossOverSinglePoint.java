/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.cromossomes.operators;

import geneticalgorithms.Statistics.GenerationStatistics;
import geneticalgorithms.cromossomes.Cromossome;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author fkay1
 */
public class CrossOverSinglePoint implements ICrossOver{
    
    public CrossOverSinglePoint() {
        System.out.println("Crossover single point");
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
            int crossPoint = (new Double(Math.random() * me.getSize())).intValue();
            if(Math.random() < 0.5) {
                newGenes.addAll(me.getGenes().subList(0, crossPoint));
                newGenes.addAll(other.getGenes().subList(crossPoint,me.getSize()));
                genesH.addAll(Collections.nCopies(crossPoint,1));
                genesH.addAll(Collections.nCopies(me.getSize() - crossPoint, 2));
            }
            else {
                newGenes.addAll(other.getGenes().subList(0, crossPoint));
                newGenes.addAll(me.getGenes().subList(crossPoint,me.getSize()));
                genesH.addAll(Collections.nCopies(crossPoint,2));
                genesH.addAll(Collections.nCopies(me.getSize() - crossPoint, 1));
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
