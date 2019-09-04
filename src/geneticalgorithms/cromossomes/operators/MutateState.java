/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.cromossomes.operators;

import geneticalgorithms.Statistics.GenerationStatistics;
import geneticalgorithms.cromossomes.Cromossome;
import java.util.List;
import org.apache.commons.math3.distribution.BinomialDistribution;

/**
 *
 * @author Fabricio Kassardjian
 */
public class MutateState<T> implements IMutate<T>{
    private static BinomialDistribution bdist;
    
    @Override
    public Cromossome<T> mutate(Cromossome<T> me, double probMutate, GenerationStatistics stat) {
        if(bdist == null || bdist.getProbabilityOfSuccess() != probMutate) {
            bdist = new BinomialDistribution(me.getSize(), probMutate);
        }
        int genesToMute = bdist.sample();

        if(genesToMute == 0) {
            me.setGenes(me.getGenes(), true);
            return me;
        }
        
        List<T> states = me.getPossibleStates();
        
        List<T> genes = me.getGenes();
        for (int i = 0; i < genesToMute; i++) {
            me.setMutated(true);
            int index = (int) (Math.random() * genes.size());
            int choice = (int) (Math.random() * states.size());
            genes.set(index, states.get(choice));
            stat.incnMutations();
        }
        
        if(me.getMutated())
            stat.incnCromMutated();
        // by setting the genes we force recalculate fitness
        me.setGenes(genes, true);
        
        return me;
    }
}
