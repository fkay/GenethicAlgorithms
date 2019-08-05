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
public class Cromossome2 extends Cromossome<Integer> {
    // cromossome representa uma matriz 10x10
    private final int rows = 5, cols = 5;
    
    // init random genes
    @Override 
    public void initGenes(){
        List<Integer> genes = new ArrayList();
        for(int i = 0; i < rows * cols; i++) {
            genes.add(Math.random() < 0.5 ? -1 : 1);
        }
        this.setGenes(genes, true);
    }

    // change each value at mutation
    @Override
    public Cromossome mutate(double probMutate) {
        List<Integer> genes = this.getGenes();
        for (int i = 0; i < this.getSize(); i++) {
            if(Math.random() < probMutate) {
                genes.set(i, -1 * genes.get(i));
            }
        }
        // by setting the genes we force recalculate fitness
        this.setGenes(genes, true);
        
        // debug print
        /*System.out.print("Son muta: ");
        this.printGenes();
        */
        return this;
    }

    // Calc new fitness for the Cromossome
    @Override
    protected double calcFitness() {
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

    // retrun string representing the fenotype of the cromossome
    @Override
    protected String fenotype() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        List<Integer> genes = this.getGenes();
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                sb.append(String.format("%2d",genes.get(cols*i + j)));
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
    
}
