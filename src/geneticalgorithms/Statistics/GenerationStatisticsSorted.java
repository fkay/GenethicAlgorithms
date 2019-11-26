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
 * @author fkay1
 */
public class GenerationStatisticsSorted extends GenerationStatistics {
    private List<Cromossome> topCromossomes;
    private List<Cromossome> bottomCromossomes;
    
    public GenerationStatisticsSorted(int popSize, int generationNumber) {
        super(popSize, generationNumber);
    }

    /**
     * @return the topCromossomes
     */
    public List<Cromossome> getTopCromossomes() {
        return topCromossomes;
    }

    /**
     * @param topCromossomes the topCromossomes to set
     */
    public void setTopCromossomes(List<Cromossome> topCromossomes) {
        this.topCromossomes = topCromossomes;
        this.setBestCromossome(topCromossomes.get(0));
    }

    /**
     * @return the bottomCromossomes
     */
    public List<Cromossome> getBottomCromossomes() {
        return bottomCromossomes;
    }

    /**
     * @param bottomCromossomes the bottomCromossomes to set
     */
    public void setBottomCromossomes(List<Cromossome> bottomCromossomes) {
        this.bottomCromossomes = bottomCromossomes;
        this.setWorstCromossome(bottomCromossomes.get(bottomCromossomes.size()-1));
    }
    
    public void setPopulationDetails(double avgFitness, double varFitness, List<Cromossome> best, List<Cromossome> worst) {
        this.setAvgFitness(avgFitness);
        this.setVarFitness(varFitness);
        this.setTopCromossomes(best);
        this.setBottomCromossomes(worst);
    }
    
    @Override
    public String summaryLine() {
        // Locale.ROOT to use '.' as decimal separator
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d;", this.getGenerationNumber()));
        for (int i = 0; i < topCromossomes.size(); i++) {
            sb.append(String.format("%f;",this.getTopCromossomes().get(i).getSpecial()));
        }
        for (int i = 0; i < bottomCromossomes.size(); i++) {
            sb.append(String.format("%f;",this.getBottomCromossomes().get(i).getSpecial()));
        }
        sb.append(String.format("%f; %f; %f; %f\n", this.getAvgFitness(), this.getVarFitness(),
                this.getCrossoverCromossomeTx(),
                this.getMutatedCromossomeTx()) );
        return  sb.toString();
    }
    @Override
    public String summaryHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("Geracao;");
        for (int i = 0; i < topCromossomes.size(); i++) {
            sb.append(String.format("Melhor%d;",i + 1));
        }
        for (int i = 0; i < bottomCromossomes.size(); i++) {
            sb.append(String.format("Pior%d;", i + 1));
        }
        sb.append("Media;Var;Tx Crossover;Tx Mutacao\n");
        return sb.toString();
    }
    
}
