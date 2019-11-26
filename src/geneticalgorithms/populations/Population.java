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
import java.util.Locale;

/**
 *
 * @author Fabricio
 */
public class Population {
    private int cromoCount;
    private List<Cromossome> cromossomes;
    private Cromossome bestCromossome;
    private Cromossome worstCromossome;
    private double sumFitness = 0;
    
    private int generationNum;
    
    private double probCrossover;
    private double probMutate;
    private int size;
    
    private String previousGenerationSummary;
    
    private final ICromossomeFactory cromossomeFactory;
    
    /**
     * @return the cromossomes
     */
    public List<Cromossome> getCromossomes() {
        return cromossomes;
    }

    /**
     * @param cromossomes the cromossomes to set
     */
    public final void setCromossomes(List<Cromossome> cromossomes) {
        this.cromossomes = cromossomes;
        // Calc fitness sum
        this.sumFitness();
        // Procura o melhor cromossomo
        this.classifyFitness();
    }

    /**
     * @return the bestCromossome
     */
    public Cromossome getBestCromossome() {
        return bestCromossome;
    }
    
    /**
     * @return the worstCromossome
     */
    public Cromossome getWorstCromossome() {
        return worstCromossome;
    }

    /**
     * @return the sumFitness
     */
    public double getSumFitness() {
        return sumFitness;
    }
    
    private void setSumFitness(double newSum) {
        this.sumFitness = newSum;
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
    
    /**
     * @return the string resuming the generation that generate the current one
     */
    public final String getPreviousGenerationSummary() {
        return this.previousGenerationSummary;
    }
    
    /**
     * @param genSumm previous generation summary
     */
    protected final void setPreviousGenerationSummary(String genSumm) {
        this.previousGenerationSummary = genSumm;
    }
    
    // calculate fitness sum
    protected double sumFitness() {
        double sum = 0;
        for(Cromossome c : getCromossomes()) {
            sum += c.getFitness();
        }
        this.setSumFitness(sum);
        return (this.getSumFitness());
    }

    // init Cromossomes population with random values
    public void init() {
        List<Cromossome> cromos = new ArrayList();
        for(int i = 0; i < this.getSize(); i++) {
            Cromossome cromossome = cromossomeFactory.getNewCromossome();
            cromossome.initGenes();
            cromossome.setId(++cromoCount);
            cromos.add(cromossome);
        }
        this.setCromossomes(cromos);
    }
    
    // select cromossome using simple roullete
    protected Cromossome selectRoullete() {
        Cromossome cromossome = null;
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
    protected Cromossome select(){
        Cromossome selectedCrom = this.selectRoullete();
        selectedCrom.incSelected();
        return selectedCrom;
    }
    
    // generate the next generation usim SGA
    // return the old generatoion
    public void nextGeneration(GenerationStatistics stat){
        List<Cromossome> newGeneration = new ArrayList();
        for(int i=0; i <  getSize(); i++) {
            Cromossome parent1 = select();
            Cromossome parent2 = select();
            Cromossome son = parent1.evolve(parent2, getProbMutate(), 
                    getProbCrossover(), stat);
            son.setId(++cromoCount);
            newGeneration.add(son);
        }
        
        setPreviousGenerationSummary(populationSummaryResume());
        
        setCromossomes(newGeneration);
        // calc fitness sum
        //sumFitness();
        generationNum++;
    }
    
    // return the average fitness value
    public double getAvgFitness() {
        return getSumFitness() / getSize();
    }
    
    // return the variance for fitness
    public double getVarFitness() {
        double sum = 0;
        double avg = getAvgFitness();
        for(Cromossome c: getCromossomes()){
            sum += Math.pow(c.getFitness() - avg, 2);
        }
        return sum/(this.getSize() - 1);
    }
    
    // return the average from some special parameter from cromossomoes
    public double getAvgSpecial() {
        double sum = 0;
        for(Cromossome c: getCromossomes()){
            sum += c.getSpecial();
        }
        return sum/this.getSize();
    }
    
    public double getVarSpecial() {
        double sum = 0;
        double avg = getAvgSpecial();
        for(Cromossome c: getCromossomes()){
            sum += Math.pow(c.getSpecial() - avg, 2);
        }
        return sum/(this.getSize() - 1);
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
        Cromossome best = getCromossomes().get(0);
        Cromossome worst = best;
        
        for(Cromossome cromossome: getCromossomes()) {
            if(best.getFitness() < cromossome.getFitness())
                best = cromossome;
            if(worst.getFitness() > cromossome.getFitness())
                worst = cromossome;
        }
        
        this.bestCromossome = best;
        this.worstCromossome = worst;
    }
    
    // Instance object with popupaltion size and probability to mutate
    public Population(int size, double probMutate, double probCrossover, ICromossomeFactory cromossomeFactory) {
        this.setProbMutate(probMutate);
        this.setProbCrossover(probCrossover);
        this.setSize(size);
        this.cromossomeFactory = cromossomeFactory;
        generationNum = 0;
        this.cromoCount = 0;
    }
    
    public String popuplationSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Geração: %d | Média Avaliação: %f\n", this.getGenerationNum(), this.getAvgFitness()));
        
        for(Cromossome cromo : this.cromossomes) {
            String s = cromo.cromossomeSummary(size);
            sb.append(s);
        }
        
        return sb.toString();
    }
    
    public String populationSummaryResume() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(String.format("Geração: %d | Média Avaliação: %f\n", this.getGenerationNum(), this.getAvgFitness()));
        
        for(Cromossome cromo : this.cromossomes) {
            double estRate = cromo.getFitness() / this.sumFitness;
            sb.append(String.format(Locale.ROOT, "ID: %d; "
                    + "Aval: %.2f; SelRate: %.3f; EstSelRate: %.3f\n", 
                    cromo.getId(), cromo.getFitness(), (0.5 * cromo.getSelected() / size),
                    estRate));
        }
        
        sb.append("\n\n");
        
        return sb.toString();
    }
    
    // return instance string
    @Override
    public String toString(){
        return String.format("Geração: %d\n"
                + "Média avaliação: %f\n"
                + "Melhor cromossomo: " + this.getBestCromossome().toString() + "\n", this.getGenerationNum(), this.getAvgFitness());
    }
    
    public boolean saveCromossomes(String filename){
        return this.cromossomeFactory.saveCromossomes(filename, this);
    }
}
