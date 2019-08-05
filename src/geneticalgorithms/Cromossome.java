/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabricio
 * @param <T>
 */
public abstract class Cromossome<T> {
    private List<T> genes;
    private double fitness;

    /**
     * @return the genes
     */
    public List<T> getGenes() {
        return genes;
    }

    /**
     * @param genes the genes to set
     */
    protected void setGenes(List<T> genes, boolean calcFit) {
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
    
    // Simple single point crossover with other cromossome. 
    // Return a new Cromossome from the crossover
    protected Cromossome crossover(Cromossome other, ICromossomeFactory cromossomeFactory){
        // get crossover point
        int crossPoint = (new Double(Math.random() * this.getSize())).intValue();
        Cromossome newCromossome = cromossomeFactory.getNewCromossome();
        List<T> newGenes = new ArrayList();
        if(Math.random() < 0.5) {
            newGenes.addAll(this.getGenes().subList(0, crossPoint));
            newGenes.addAll(other.getGenes().subList(crossPoint,this.getSize()));
        }
        else {
            newGenes.addAll(other.getGenes().subList(0, crossPoint));
            newGenes.addAll(this.getGenes().subList(crossPoint,this.getSize()));
        }
        newCromossome.setGenes(newGenes, false);
        return newCromossome;
    }
    
    // Cromossome mutation
    protected abstract Cromossome mutate(double probMutate);
    
    public Cromossome evolve(Cromossome other, double probMutate, ICromossomeFactory cromossomeFactory) {
        Cromossome newCromossome = crossover(other, cromossomeFactory);
        
        // debug print
        /*System.out.println(String.format("Crossover point: %d", crossPoint));
        System.out.print("Parent 1: ");
        this.printGenes();
        System.out.print("Parent 2: ");
        ((Cromossome1) other).printGenes();
        System.out.print("Son     : ");
        newCromossome.printGenes();
        */
        
        newCromossome = newCromossome.mutate(probMutate);
        
        // debug print
        /*System.out.print("Son muta: ");
        newCromossome.printGenes();
        */
        
        return newCromossome;
    }

    // Calc cromossome fitness
    protected abstract double calcFitness();
    
    // Init cromossome random genes
    protected abstract void initGenes();
    
    // return the fenotype from this cromossome (what its look like)
    protected abstract String fenotype();
    
    // print the genes
    protected abstract void printGenes();
    
    @Override
    public String toString() {
        return String.format("Fenotipo: " + fenotype() + "\n"
                + "Avaliação: %f\n", this.fitness);
    }
}
