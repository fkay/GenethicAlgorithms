/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.cromossomes.operators;

import geneticalgorithms.Statistics.GenerationStatistics;
import geneticalgorithms.cromossomes.Cromossome;
import java.util.List;

/**
 *
 * @author Fabricio Kassardjian
 */
public class MutateState implements IMutate{
    public Cromossome mutate(Cromossome me, double probMutate, GenerationStatistics stat) {
        List states = me.getPossibleStates();
        
        List genes = me.getGenes();
        for (int i = 0; i < me.getSize(); i++) {
            if(Math.random() < probMutate) {
                me.setMutated(true);
                //genes.set(i, -1 * genes.get(i));
                int choice = (int) (Math.random() * states.size());
                genes.set(i, states.get(choice));
                stat.incnMutations();
            }
        }
        if(me.getMutated())
            stat.incnCromMutated();
        // by setting the genes we force recalculate fitness
        me.setGenes(genes, true);
        
        return me;
    }
}
