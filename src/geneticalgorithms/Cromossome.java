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
     * @return the alleles
     */
    public List<T> getGenes() {
        return genes;
    }

    /**
     * @param alleles the alleles to set
     */
    protected void setGenes(List<T> genes) {
        this.genes = genes;
    }
    
    public int getSize() {
        return this.genes.size();
    }

    /**
     * @return the fitness
     */
    public double getFitness() {
        return fitness;
    }
    
    // Crossover com outro cromossomo
    // Retorna cromossomo resultado
    public abstract Cromossome crossover(Cromossome other, double probCross);
    
    // Mutação do cromossomo
    // Retorna cromossomo mutado
    public abstract Cromossome mutate(double probMutate);

    // Calcula avaliação do cromossomo
    // retorno valor da avaliação
    protected abstract double calcFitness();
    
    // Inicial genes do cromossome
    protected abstract void initGenes();
    
    // metodo que devolve o fenotipo gerado pelos genes
    protected abstract String fenotype();
    
    @Override
    public String toString() {
        return String.format("Cromossomo: " + fenotype() + "\n"
                + "Avaliação: %f\n", this.fitness);
    }
}
