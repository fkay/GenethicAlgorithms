/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.cromossomes;

import geneticalgorithms.Statistics.GenerationStatistics;
import geneticalgorithms.cromossomes.operators.*;
import java.util.List;

/**
 *
 * @author Fabricio
 * @param <T>
 */
public abstract class Cromossome<T> implements Comparable<Cromossome> {
    private List<T> genes;
    private double fitness;
    private boolean mutated;
    
    private final ICrossOver<T> crossOver;
    private final IMutate<T> imutate;
    
    public Cromossome(ICrossOver<T> crossover, IMutate<T> mutate) {
        this.crossOver = crossover;
        this.imutate = mutate;
    }

    /**
     * @return the genes
     */
    public List<T> getGenes() {
        return genes;
    }

    /**
     * @param genes the genes to set
     * @param calcFit flag indicating if should calc fitness
     */
    public void setGenes(List<T> genes, boolean calcFit) {
        this.genes = genes;
        // after get the new gens alredy check fitness
        if(calcFit)
            this.fitness = this.calcFitness();
    }
    
    /**
     * @return the size
     */
    public int getSize() {
        return this.genes.size();
    }

    /**
     * @return the fitness
     */
    public double getFitness() {
        return fitness;
    }
    
    /**
     * @param fitness the fitness to set
     */
    protected void setFitness(double fitness) {
        this.fitness = fitness;
    }
    
    /**
     * @return the mutated
     */
    public boolean getMutated(){
        return mutated;
    }
    
    /**
     * @param mutated 
     */
    public void setMutated(boolean mutated) {
        this.mutated = mutated; 
    }
    
    // Simple single point crossover with other cromossome. 
    // Return a new Cromossome from the crossover
    protected Cromossome<T> crossover(Cromossome<T> other, double probCrossover,
            ICromossomeFactory<T> cromossomeFactory, GenerationStatistics stat){

        return crossOver.crossover(this, other, probCrossover, cromossomeFactory, stat);
    }
    
    // Cromossome mutation
    protected Cromossome<T> mutate(double probMutate, GenerationStatistics stat) {
        return imutate.mutate(this, probMutate, stat);
    }
    
    /**
     * @return the possible states when mutating
     */
    public abstract List<T> getPossibleStates(); 
    
    public Cromossome<T> evolve(Cromossome<T> other, double probMutate, double probCrossover,
            ICromossomeFactory<T> cromossomeFactory, GenerationStatistics stat) {
        
        Cromossome<T> newCromossome = crossover(other, probCrossover, cromossomeFactory, stat);
        
        // debug print
        /*System.out.println(String.format("Crossover point: %d", crossPoint));
        System.out.print("Parent 1: ");
        this.printGenes();
        System.out.print("Parent 2: ");
        ((Cromossome1) other).printGenes();
        System.out.print("Son     : ");
        newCromossome.printGenes();
        */
        
        newCromossome = newCromossome.mutate(probMutate, stat);
        
        // debug print
        /*System.out.print("Son muta: ");
        newCromossome.printGenes();
        */
        
        return newCromossome;
    }

    // Calc cromossome fitness
    protected abstract double calcFitness();
    
    // Init cromossome random genes
    public abstract void initGenes();
    
    // return the fenotype from this cromossome (what its look like)
    protected abstract String fenotype();
    
    // print the genes
    protected abstract void printGenes();
    
    @Override
    public String toString() {
        return String.format("Fenotipo: " + fenotype() + "\n"
                + "Avaliação: %f\n", this.fitness);
    }
   
    @Override
    public int compareTo(Cromossome other) {
        double comp = this.getFitness() - other.getFitness();
        if (comp < 0)
            return -1;
        if (comp > 0)
            return 1;
        return 0;
    }
}
