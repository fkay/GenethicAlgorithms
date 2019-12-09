/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.Statistics;

import geneticalgorithms.cromossomes.Cromossome;
import java.util.List;

/**
 *
 * @author Fabricio
 */
public class GenerationSummarySorted extends GenerationSummary{
    private final List<Cromossome> topCromossomes;
    
    public List<Cromossome> getTopCromossomes() {
        return topCromossomes;
    }
    
    public GenerationSummarySorted(List<Cromossome> top, double avgFitness, int mutatedCount) {
        super(top.get(0), avgFitness, mutatedCount);
        this.topCromossomes = top;
    }
    
    @Override
    public String summaryLine() {
        // Locale.ROOT to use '.' as decimal separator
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < topCromossomes.size(); i++) {
            sb.append(String.format("%f;",this.getTopCromossomes().get(i).getFitness()));
        }
        sb.append(String.format("%f; %d\n", this.getAvg(), this.getMutatedCount()));
        return  sb.toString();
    }
    @Override
    public String summaryHeader() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < topCromossomes.size(); i++) {
            sb.append(String.format("Melhor%d;",i + 1));
        }
        sb.append("Media;Mutacoes\n");
        return sb.toString();
    }
    
}
