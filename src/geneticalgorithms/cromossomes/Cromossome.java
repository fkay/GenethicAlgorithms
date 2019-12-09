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
    private int id;
    private List<T> genes;
    private double fitness;
    private boolean mutated;
    
    private Cromossome parent1;
    private Cromossome parent2;
    
    private List<Integer> genesHeritage;
    private List<Boolean> genesMutated;
    
    private int selected = 0;
    
    protected final ICrossOver icrossover;
    protected final IMutate imutate;
    
    public abstract Cromossome getCopy();
    
    public Cromossome(ICrossOver crossover, IMutate mutate) {
        this.icrossover = crossover;
        this.imutate = mutate;
    }
    
    public Cromossome(Cromossome other) {
        this(other.icrossover, other.imutate);
    }

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @return the genes
     */
    public List<T> getGenes() {
        return genes;
    }

    /**
     * @param genes the genes to set
     * @param calcFit true if already calc the fitness
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
     * @return some special variable if any
     */
    public double getSpecial() {
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
    protected Cromossome crossover(Cromossome other, double probCrossover,
            GenerationStatistics stat){

        return icrossover.crossover(this, other, probCrossover, stat);
    }
    
    // Cromossome mutation
    protected Cromossome mutate(double probMutate, GenerationStatistics stat) {
        return imutate.mutate(this, probMutate, stat);
    }
    
    /**
     *
     * @return list of possibleStates to mutate
     */
    public abstract List<T> getPossibleStates(); 
    
    public Cromossome evolve(Cromossome other, double probMutate, double probCrossover,
            GenerationStatistics stat) {
        
        Cromossome newCromossome = crossover(other, probCrossover, stat);
        
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
        
        newCromossome.setParent1(this);
        newCromossome.setParent2(other);
        
        // debug print
        /*System.out.print("Son muta: ");
        newCromossome.printGenes();
        */
        
        return newCromossome;
    }
    
    public Cromossome getNewCromossome() {
        return this.getCopy();
    }

    // Calc cromossome fitness
    protected abstract double calcFitness();
    
    // Init cromossome random genes
    public abstract void initGenes();
    
    // return the fenotype from this cromossome (what its look like)
    protected abstract String fenotype();
    
    // return the heritage map
    protected abstract String heritageMap();
    
    // return the mutated map
    protected abstract String mutateMap();
    
    // print the genes
    protected abstract void printGenes();
    
    public void setParent1(Cromossome parent1) {
        this.parent1 = parent1;
    }
    
    public Cromossome getParent1() {
        return this.parent1;
    }
    
    public void setParent2(Cromossome parent2) {
        this.parent2 = parent2;
    }
    
    public Cromossome getParent2() {
        return this.parent2;
    }
    
    public void setGenesHeritage(List<Integer> genesH) {
        this.genesHeritage = genesH;
    }
    
    public List<Integer> getGenesHeritage() {
        return this.genesHeritage;
    }
    
    public void setGenesMutated(List<Boolean> genesM) {
        this.genesMutated = genesM;
    }
    
    public List<Boolean> getGenesMutated() {
        return this.genesMutated;
    }
    
    public void incSelected() {
        this.selected++;
    }
    
    public int getSelected() {
        return this.selected;
    }
    
    @Override
    public String toString() {
        return String.format("ID: %d\nFenotipo: " + fenotype() + "\n"
                + "Avaliação: %f\n", this.id, this.fitness);
    }
    
    public String printMap(){
        return "Not implemented";
    }
    
    public String cromossomeSummary(int popSize) {
        StringBuilder sb = new StringBuilder();
        sb.append("#### CHILD #####\n");
        sb.append(this.toString());

        if(this.parent1 == null) {
            sb.append("### CROMOSSOMO GERADO NA POP INICIAL ####\n\n");
        }
        else {
            sb.append("#### PARENT 1 #####\n");
            sb.append(this.parent1.toString());
            sb.append(String.format("Selecionado %d vezes, %.2f\n\n", this.parent1.selected, (1.0 * this.parent1.selected) / popSize));

            sb.append("#### PARENT 2 #####\n");

            sb.append(this.parent2.toString());
            sb.append(String.format("Selecionado %d vezes, %.2f\n\n", this.parent2.selected, (1.0 * this.parent2.selected) / popSize));

            sb.append("### HERITAGE MAP ###\n");
            sb.append(this.heritageMap());
            sb.append("\n");

            sb.append("### MUTATE MAP\n");
            sb.append(this.mutateMap());
            sb.append("\n");
        }
        return sb.toString();
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
