/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.cromossomes;

import geneticalgorithms.cromossomes.operators.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Fabricio
 */
public class Cromossome2 extends Cromossome<Integer> {
    // cromossome representa uma matriz 10x10
    private final int rows = 5, cols = 5;
    private int countp;
    private int countm;
    
    private final List<Integer> states;
    
     public Cromossome2(ICrossOver crossover, IMutate mutate){
        super(crossover, mutate);
        states = Arrays.asList(-1,1);
        setMutated(false);
    }
     
    public Cromossome2(Cromossome2 cromo) {
        this(cromo.icrossover, cromo.imutate);
    }
    
    @Override
    public Cromossome2 getCopy() {
        return new Cromossome2(this);
    }
    
    /**
     * @return the countp
     */
    public int getCountp() {
        return countp;
    }

    /**
     * @return the countm
     */
    public int getCountm() {
        return countm;
    }
    
     protected void countGenes() {
        countp = 0;
        countm = 0;
        for(int gene : getGenes()){
            if(gene > 0)
                countp++;
            else
                countm++;
        }
    }
    
    // init random genes
    @Override 
    public void initGenes(){
        List<Integer> genes = new ArrayList();
        for(int i = 0; i < rows * cols; i++) {
            genes.add(Math.random() < 0.5 ? -1 : 1);
        }
        this.setGenes(genes, true);
    }

    // Calc new fitness for the Cromossome
    @Override
    protected double calcFitness() {
        countGenes();
        
        int sum = 0;
        List<Integer> genes = this.getGenes();
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(i < rows - 1)
                    sum += genes.get(cols*i + j)*genes.get(cols*(i+1) + j);
                if(j < cols - 1)
                    sum += genes.get(cols*i + j)*genes.get(cols*i + j+1);
            }
        }
        // calc the max negative value
        int maxNegative = 2 * rows * cols - rows - cols;
        // sum the max negative to avoid negative values on fitness
        return sum + maxNegative;
    }
    
    /**
     * Calculate total energy from the grid
     * @return E from grid
     */
    public int getEnergy() {
        // return fitness value puting back negative values
        return (int) this.getFitness() - (2 * rows * cols - rows - cols);
    }

    // retrun string representing the fenotype of the cromossome
    @Override
    protected String fenotype() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        List<Integer> genes = this.getGenes();
        
        sb.append(commomStringBuilder(genes, "%2d"));
        
        sb.append(String.format("\n#1: %3d | #-1: %3d\n", countp, countm));
        
        return sb.toString();
    }
    
    private String commomStringBuilder(List genes, String format){
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                sb.append(String.format(format,genes.get(cols*i + j)));
                if(j != cols - 1)
                    sb.append(" | ");
                else
                    sb.append("\n");
            }
            if (i != rows - 1){
                for(int x = 0; x < cols ; x++)
                    sb.append("-----");
                sb.append("\n");
            }
            //sb.append("\n");
        }
        
        return sb.toString();
    }
    
    @Override
    protected String heritageMap() {
        StringBuilder sb = new StringBuilder();
        sb.append(commomStringBuilder(this.getGenesHeritage(),"%2d"));
        return sb.toString();
    }
    
    @Override
    protected String mutateMap() {
        StringBuilder sb = new StringBuilder();
        sb.append(commomStringBuilder(this.getGenesMutated(),"%B"));
        return sb.toString();
    }

    @Override
    protected void printGenes() {
        getGenes().forEach((gene) -> {
            System.out.print(gene > 0 ? "1" : "0");
        });
        System.out.println();
    }
    
    protected void initMaxGenes() {
        List<Integer> genes = new ArrayList();
        for(int i = 0; i < rows * cols; i++) {
            genes.add(-1);
        }
        this.setGenes(genes, true);
    }
    
    @Override
    public List<Integer> getPossibleStates() {
        return states;
    }
    
   

}
