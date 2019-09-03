/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.populations;

import geneticalgorithms.Statistics.GenerationStatistics;
import geneticalgorithms.cromossomes.ICromossomeFactory;
import geneticalgorithms.cromossomes.Cromossome;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabricio
 * @param <T> type of genes
 */
public class Population<T> {
    private List<Cromossome<T>> cromossomes;
    private Cromossome<T> bestCromossome;
    private Cromossome<T> worstCromossome;
    private double sumFitness = 0;
    
    private int generationNum;
    
    private double probCrossover;
    private double probMutate;
    private int size;
    
    private final ICromossomeFactory<T> cromossomeFactory;
    
    /**
     * @return the cromossomes
     */
    public List<Cromossome<T>> getCromossomes() {
        return cromossomes;
    }

    /**
     * @param cromossomes the cromossomes to set
     */
    public final void setCromossomes(List<Cromossome<T>> cromossomes) {
        this.cromossomes = cromossomes;
        // Calc fitness sum
        this.sumFitness();
        // Procura o melhor cromossomo
        this.classifyFitness();
    }

    /**
     * @return the bestCromossome
     */
    public Cromossome<T> getBestCromossome() {
        return bestCromossome;
    }
    
    /**
     * @return the worstCromossome
     */
    public Cromossome<T> getWorstCromossome() {
        return worstCromossome;
    }

    /**
     * @return the sumFitness
     */
    public double getSumFitness() {
        return sumFitness;
    }

    /**
     * @return the generationNum
     */
    public int getGenerationNum() {
        return generationNum;
    }
    
    /**
     * @return the probCrossover
     */
    public double getProbCrossover() {
        return probCrossover;
    }
    
    /**
     * @param probCrossover the probCrossover to set 
     */
    public final void setProbCrossover(double probCrossover) {
        this.probCrossover = probCrossover;
    }

    /**
     * @return the probMutate
     */
    public double getProbMutate() {
        return probMutate;
    }

    /**
     * @param probMutate the probMutate to set
     */
    public final void setProbMutate(double probMutate) {
        this.probMutate = probMutate;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public final void setSize(int size) {
        this.size = size;
    }
    
    // calculate fitness sum
    private double sumFitness() {
        this.sumFitness = 0;
        getCromossomes().forEach((cromossome) -> {
            sumFitness += cromossome.getFitness();
        });
        
        return (this.getSumFitness());
    }

    // init Cromossomes population with random values
    public void init() {
        List<Cromossome<T>> cromos = new ArrayList<>();
        for(int i = 0; i < this.getSize(); i++) {
            Cromossome<T> cromossome = cromossomeFactory.getNewCromossome();
            cromossome.initGenes();
            cromos.add(cromossome);
        }
        this.setCromossomes(cromos);
    }
    
    // select cromossome using simple roullete
    protected Cromossome<T> selectRoullete() {
        Cromossome<T> cromossome = null;
        double soma = 0;
        double roullete = Math.random() * this.getSumFitness();
        for(int i = 0; i < this.getSize(); i++) {
            cromossome = this.getCromossomes().get(i);
            soma += cromossome.getFitness();
            if(soma >= roullete)
                break;
        }
        return cromossome;
    }
    
    // method for cromossome selection
    // default selects using simple Roullete
    protected Cromossome<T> select(){
        return this.selectRoullete();
    }
    
    // generate the next generation usim SGA

    /**
     *
     * @param stat statiscs object
     */
    public void nextGeneration(GenerationStatistics<T> stat){
        List<Cromossome<T>> newGeneration = new ArrayList<>();
        for(int i=0; i <  getSize(); i++) {
            Cromossome<T> parent1 = select();
            Cromossome<T> parent2 = select(); 
            Cromossome<T> son = parent1.evolve(parent2, getProbMutate(), 
                    getProbCrossover(), cromossomeFactory, stat);
            
            newGeneration.add(son);
        }
        setCromossomes(newGeneration);
        // calc fitness sum
        sumFitness();
        generationNum++;
    }
    
    // return the average fitness value
    public double getAvgFitness() {
        return getSumFitness() / getSize();
    }
    
    // return the count of mutated cromossomes
    public int countMutated() {
        int mut = 0;
        for(Cromossome c :getCromossomes()) {
            if(c.getMutated())
                mut++;
        }
        return mut;
    }
    
    // set the best cromossome form the population
    protected void classifyFitness() {
        Cromossome<T> best = getCromossomes().get(0);
        Cromossome<T> worst = best;
        
        for(Cromossome<T> cromossome: getCromossomes()) {
            if(best.getFitness() < cromossome.getFitness())
                best = cromossome;
            if(worst.getFitness() > cromossome.getFitness())
                worst = cromossome;
        }
        
        this.bestCromossome = best;
        this.worstCromossome = worst;
    }
    
    // Instance object with popupaltion size and probability to mutate
    public Population(int size, double probMutate, double probCrossover, ICromossomeFactory<T> cromossomeFactory) {
        this.setProbMutate(probMutate);
        this.setProbCrossover(probCrossover);
        this.setSize(size);
        this.cromossomeFactory = cromossomeFactory;
        generationNum = 0;
    }
    
    // return instance string
    @Override
    public String toString(){
        return String.format("Geração: %d\n"
                + "Média avaliação: %f\n"
                + "Melhor cromossomo: " + this.getBestCromossome().toString() + "\n", this.getGenerationNum(), this.getAvgFitness());
    }
}
