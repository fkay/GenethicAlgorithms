/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms;

import java.util.Locale;

/**
 * Class to store informations for each of the generations
 * @author Fabricio
 */
public class GenerationSummary {
    private final Cromossome bestCromossome;
    private final double avgFitness;

    public GenerationSummary(Cromossome best, double avgFitness) {
        this.bestCromossome = best;
        this.avgFitness = avgFitness;
    }
    
    public double getBest() {
        return bestCromossome.getFitness();
    }
    
    public double getAvg() {
        return avgFitness;
    }
    
    public String summaryLine() {
        // Locale.ROOT to use '.' as decimal separator
        return String.format(Locale.ROOT, "%f;%f\n", this.getBest(), this.getAvg());
    }
    public String summaryHeader() {
        return String.format("Melhor;Media\n");
    }
}
