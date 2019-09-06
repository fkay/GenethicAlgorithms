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
 * Cromossomo1 - genes binarios, representam inteiros x e y com 24 bits de precisao cada
 */
public class Cromossome1 extends Cromossome<Boolean> {
    private final int minVal = -100;
    private final int maxVal = 100;
    
    private final int bitsX = 15;
    private final int bitsY = 15;
    
    private final double fator = (maxVal - minVal) / Math.pow(2, bitsX);
    
    private double x,y;
    
    private final List<Boolean> states;
    
    public Cromossome1(ICrossOver crossover, IMutate mutate) {
        super(crossover, mutate);
        states = Arrays.asList(true,false);
    }
    
    public Cromossome1(Cromossome1 cromo) {
        this(cromo.icrossover, cromo.imutate);
    }
    
    @Override
    public Cromossome1 getCopy() {
        return new Cromossome1(this);
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
    protected double calcFitness() {
        x = convertGenes(this.getGenes(), 0, bitsX);
        y = convertGenes(this.getGenes(), bitsX, bitsX + bitsY);
        return (Math.abs(x * y * Math.sin((y * Math.PI)/4)));
    }

    @Override
    protected String fenotype() {
        return String.format("x = %.5f | y = %.5f", x, y);
    }
    
    @Override
    public List<Boolean> getPossibleStates() {
        return states;
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
    
    @Override
    protected String heritageMap() {
        StringBuilder sb = new StringBuilder();
        this.getGenesHeritage().forEach((gH) -> {
            sb.append(gH);
        });
        return sb.toString();
    }
    
    @Override
    protected String mutateMap() {
        StringBuilder sb = new StringBuilder();
        this.getGenesMutated().forEach((gH) -> {
            sb.append(gH);
        });
        return sb.toString();
    }

}
