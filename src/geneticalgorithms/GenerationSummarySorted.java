/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms;

import java.util.List;
import java.util.Locale;

/**
 *
 * @author Fabricio
 */
public class GenerationSummarySorted extends GenerationSummary{
    private final List<Cromossome> topCromossomes;
    
    public List<Cromossome> getTopCromossomes() {
        return topCromossomes;
    }
    
    public GenerationSummarySorted(List<Cromossome> top, double avgFitness) {
        super(top.get(0), avgFitness);
        this.topCromossomes = top;
    }
    
    @Override
    public String summaryLine() {
        // Locale.ROOT to use '.' as decimal separator
        return String.format(Locale.ROOT, "%f;%f;%f;%f\n", this.getBest(), 
                this.getTopCromossomes().get(1), this.getTopCromossomes().get(2), this.getAvg());
    }
    @Override
    public String summaryHeader() {
        return String.format("Melhor1;Melhor2;Melhor3;Media\n");
    }
    
}
