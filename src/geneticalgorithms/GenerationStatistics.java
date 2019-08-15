/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms;

import java.util.Locale;

/**
 *
 * @author Fabricio
 */
public class GenerationStatistics {
    private int nMutations;
    private int nCromMutated;
    private int nCromCrossover;
    
    private Cromossome bestCromossome;
    private Cromossome worstCromossome;
    private double avgFitness;
    private final int popSize;

    public GenerationStatistics(int popSize) {
        this.popSize = popSize;
    }

    /**
     * @return the nMutations
     */
    public int getnMutations() {
        return nMutations;
    }

    /**
     * Increment n mutations
     */
    public void incnMutations() {
        this.nMutations++;
    }

    /**
     * @return the nCromMutated
     */
    public int getnCromMutated() {
        return nCromMutated;
    }

    /**
     * increment n cromossomes mutated
     */
    public void incnCromMutated() {
        this.nCromMutated++;
    }

    /**
     * @return the nCromCrossover
     */
    public int getnCromCrossover() {
        return nCromCrossover;
    }

    /**
     * increment n cromossomes crossover
     */
    public void incnCromCrossover() {
        this.nCromCrossover++;
    }

    /**
     * @return the bestCromossome
     */
    public Cromossome getBestCromossome() {
        return bestCromossome;
    }
    
    /**
     * @param bestCromossome the bestCromossome to set
     */
    public void setBestCromossome(Cromossome bestCromossome) {
        this.bestCromossome = bestCromossome;
    }
    
    /**
     * @return the worstCromossome
     */
    public Cromossome getWorstCromossome() {
        return worstCromossome;
    }
    
    /**
     * @param worstCromossome the worstCromossome to set
     */
    public void setWorstCromossome(Cromossome worstCromossome) {
        this.worstCromossome = worstCromossome;
    }

    /**
     * @param avgFitness the avgFitness to set
     */
    public void setAvgFitness(double avgFitness) {
        this.avgFitness = avgFitness;
    }

    /**
     * @return the avgFitness
     */
    public double getAvgFitness() {
        return avgFitness;
    }
    
    /**
     * @return the mutated cromossomes tax
     */
    public double getMutatedCromossomeTx() {
        return this.nCromMutated / this.popSize;
    }
    
    /**
     * @return the crossover cromossomes tax
     */
    public double getCrossoverCromossomeTx() {
        return this.nCromCrossover / this.popSize;
    }
    
     public String summaryLine() {
        // Locale.ROOT to use '.' as decimal separator
        return String.format(Locale.ROOT, "%f; %f; %f; %f; %f\n", 
                this.getBestCromossome().getFitness(), this.getWorstCromossome().getFitness(), 
                this.getAvgFitness(), this.getCrossoverCromossomeTx(),
                this.getMutatedCromossomeTx());
    }
    public String summaryHeader() {
        return String.format("Melhor;Pior;Media;Tx Mutacao;Tx Crossover\n");
    }
    
}
