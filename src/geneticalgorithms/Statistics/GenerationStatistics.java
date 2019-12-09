/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.Statistics;

import geneticalgorithms.cromossomes.Cromossome;

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
    private double varFitness;
    private final int popSize;
    private final int generationNumber;

    public GenerationStatistics(int popSize, int generationNumber) {
        this.popSize = popSize;
        this.generationNumber = generationNumber;
        this.nMutations = 0;
        this.nCromMutated = 0;
        this.nCromCrossover = 0;
        this.avgFitness = 0;
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
     * @param varFitness the avgFitness to set
     */
    public void setVarFitness(double varFitness) {
        this.varFitness = varFitness;
    }

    /**
     * @return the varFitness
     */
    public double getVarFitness() {
        return varFitness;
    }
    
    /**
     * @return the popSize
     */
    public int getPopSize() {
        return popSize;
    }
    
    /**
     * @return the generationNumber
     */
    public int getGenerationNumber() {
        return generationNumber;
    }
    
    /**
     * @return the mutated cromossomes tax
     */
    public double getMutatedCromossomeTx() {
        return 1.0 * this.nCromMutated / this.popSize;
    }
    
    /**
     * @return the crossover cromossomes tax
     */
    public double getCrossoverCromossomeTx() {
        return 1.0 * this.nCromCrossover / this.popSize;
    }
    
    public void setPopulationDetails(double avgFitness, Cromossome best, Cromossome worst) {
        this.setAvgFitness(avgFitness);
        this.setBestCromossome(best);
        this.setWorstCromossome(worst);
    }
    
     public String statsLine() {
        // Locale.ROOT to use '.' as decimal separator
        return String.format("%d; %f; %f; %f; %f; %f\n",
                this.generationNumber,
                this.getBestCromossome().getFitness(), this.getWorstCromossome().getFitness(), 
                this.getAvgFitness(), this.getCrossoverCromossomeTx(),
                this.getMutatedCromossomeTx());
    }
    public String statsHeader() {
        return String.format("Geracao;Melhor;Pior;Media;Tx Crossover;Tx Mutacao\n");
    }
    
}
