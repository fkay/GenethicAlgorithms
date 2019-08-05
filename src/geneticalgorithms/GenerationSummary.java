/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms;

/**
 *
 * @author Fabricio
 */
public class GenerationSummary {
    private final Cromossome bestCromossome;
    private final double avgFitness;

    public GenerationSummary(Cromossome best, double avgFitness) {
        this.bestCromossome = best;
        this.avgFitness = avgFitness;
    }
}
