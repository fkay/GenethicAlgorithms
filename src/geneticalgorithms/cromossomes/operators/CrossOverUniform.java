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
public class CrossOverUniform implements ICrossOver{
    
    public CrossOverUniform(){
        System.out.println("Crossover uniform");
    }

    @Override
    public Cromossome crossover(Cromossome me, Cromossome other, double probCrossover, 
            GenerationStatistics stat) {
        List newGenes = new ArrayList();
        Cromossome newCromossome = me.getNewCromossome();
        
        List<Integer> genesH = new ArrayList<>();
        
        if(Math.random() < probCrossover) {
            stat.incnCromCrossover();

            for (int i = 0; i < me.getSize(); i++) {
                if(Math.random() < 0.5) {
                    newGenes.add(me.getGenes().get(i));
                    genesH.add(1);
                }
                else {
                    newGenes.add(other.getGenes().get(i));
                    genesH.add(2);
                }
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
