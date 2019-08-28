/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.cromossomes;

import geneticalgorithms.Statistics.GenerationStatistics;
import geneticalgorithms.cromossomes.operators.ICrossOver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Fabricio
 * Cromossomo1 - genes binarios, representam inteiros x e y com 24 bits de precisao cada
 */
public class Cromossome1 extends Cromossome<Boolean> {
    private final int minVal = -100;
    private final int maxVal = 100;
    
    private final int bitsX = 15;
    private final int bitsY = 15;
    
    private final double fator = (maxVal - minVal) / Math.pow(2, bitsX);
    
    private static List states = Arrays.asList(true, false);
    
    private double x,y;
    
    public Cromossome1(ICrossOver crossover) {
        super(crossover);
    }
    
    @Override
    public void initGenes(){
        List<Boolean> genes = new ArrayList();
        for(int i = 0; i < bitsX + bitsY; i++) {
            genes.add(Math.random() < 0.5 ? Boolean.TRUE : Boolean.FALSE);
        }
        this.setGenes(genes, true);
    }

    @Override
    protected Cromossome mutate(double probMutate, GenerationStatistics stat) {
        List<Boolean> genes = this.getGenes();
        boolean oneGeneMutated = false;
        for (int i = 0; i < this.getSize(); i++) {
            if(Math.random() < probMutate) {
                genes.set(i, !genes.get(i));
                stat.incnMutations();
                oneGeneMutated = true;
            }
        }
        if(oneGeneMutated)
            stat.incnCromMutated();
        this.setGenes(genes, true);
        return this;
    }

    @Override
    protected double calcFitness() {
        x = convertGenes(this.getGenes(), 0, bitsX);
        y = convertGenes(this.getGenes(), bitsX, bitsX + bitsY);
        return (Math.abs(x * y * Math.sin((y * Math.PI)/4)));
    }

    @Override
    protected String fenotype() {
        return String.format("x = %.5f | y = %.5f", x, y);
    }
    

    // Método que converte genes binários em inteiro
    private double convertGenes(List<Boolean> genes, int start, int end) {
        int value = 0;
        int mult = 1;
        for (int i = start; i < end; i++) {
            value += (genes.get(i) ? 1 : 0) * mult;
            mult *= 2;
        }
        return(value * fator + minVal);
    }
    
    // print the genes sequence
    @Override
    protected void printGenes() {
        getGenes().forEach((gene) -> {
            System.out.print(gene ? "1" : "0");
        });
        System.out.println();
    }


}
