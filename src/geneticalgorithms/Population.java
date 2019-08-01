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
 */
public abstract class Population {
    private List<Cromossome> cromossomes;
    private Cromossome bestCromossome;
    private double sumFitness = 0;
    
    private int generationNum;
    
    private double probMutate;
    private int size;
    
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
        this.bestFitness();
    }

    /**
     * @return the bestCromossome
     */
    public Cromossome getBestCromossome() {
        return bestCromossome;
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
    
    // seleciona individuo
    protected abstract Cromossome select();
    
    // calcula soma das avaliações dos individuos
    private double sumFitness() {
        this.sumFitness = 0;
        getCromossomes().forEach((cromossome) -> {
            sumFitness += cromossome.getFitness();
        });
        
        return (this.getSumFitness());
    }

    // metodo que inicia os cromossomos aleatórios da população
    public abstract void initPopulation();
    
    // metodo que constroi proxima geracao
    public void nextGeneration(){
        List<Cromossome> newGeneration = new ArrayList();
        for(int i=0; i <  getSize(); i++) {
            Cromossome parent1 = select();
            Cromossome parent2 = select(); 
            Cromossome son = parent1.evolve(parent2, getProbMutate());
            
            newGeneration.add(son);
        }
        setCromossomes(newGeneration);
        // calc fitness sum
        sumFitness();
        generationNum++;
    }
    
    // retorna valor médio das avaliações da população
    public double getAvgFitness() {
        return getSumFitness() / getSize();
    }
    
    protected void bestFitness() {
        Cromossome best = getCromossomes().get(0);
        
        for(Cromossome cromossome: getCromossomes()) {
            if(best.getFitness() < cromossome.getFitness())
                best = cromossome;
        }
        
        this.bestCromossome = best;
    }
    
    // construtor recebe parametros da populacao
    public Population(int size, double probMutate) {
        this.setProbMutate(probMutate);
        this.setSize(size);
        generationNum = 0;
    }
    
    @Override
    public String toString(){
        return String.format("Geração: %d\n"
                + "Média avaliação: %f\n"
                + "Melhor cromossomo: " + this.getBestCromossome().toString() + "\n", this.getGenerationNum(), this.getAvgFitness());
    }
}
