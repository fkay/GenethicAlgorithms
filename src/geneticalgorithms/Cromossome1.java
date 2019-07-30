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
 * Cromossomo1 - genes binarios, representam inteiros x e y com 24 bits de precisao cada
 */
public class Cromossome1 extends Cromossome<Boolean> {
    private final int minVal = -100;
    private final int maxVal = 100;
    
    private final int bitsX = 15;
    private final int bitsY = 15;
    
    private final double fator = (maxVal - minVal) / Math.pow(2, bitsX);
    
    private double x,y;
    
    @Override
    public void setGenes(List<Boolean> genes) {
        super.setGenes(genes);
        x = convertGenes(genes, 0, bitsX);
        y = convertGenes(genes, bitsX, bitsX + bitsY);
        this.calcFitness();
    }
    
    @Override
    public void initGenes(){
        List<Boolean> genes = new ArrayList();
        for(int i = 0; i < bitsX + bitsY; i++) {
            genes.add(Math.random() < 0.5 ? Boolean.TRUE : Boolean.FALSE);
        }
        this.setGenes(genes);
    }
    
    @Override
    public Cromossome crossover(Cromossome other, double probCross) {
        // get crossover point
        int crossPoint = (new Double(Math.random() * this.getSize())).intValue();
        Cromossome1 newCromossome = new Cromossome1();
        List<Boolean> newGenes = new ArrayList();
        if(Math.random() < 0.5) {
            newGenes.addAll(this.getGenes().subList(0, crossPoint));
            newGenes.addAll(other.getGenes().subList(crossPoint,this.getSize()));
        }
        else {
            newGenes.addAll(other.getGenes().subList(0, crossPoint));
            newGenes.addAll(this.getGenes().subList(crossPoint,this.getSize()));
        }
        newCromossome.setGenes(newGenes);
        
        // debug print
        /*System.out.println(String.format("Crossover point: %d", crossPoint));
        System.out.print("Parent 1: ");
        this.printGenes();
        System.out.print("Parent 2: ");
        ((Cromossome1) other).printGenes();
        System.out.print("Son     : ");
        newCromossome.printGenes();
       */ 
        return newCromossome;
    }

    @Override
    public Cromossome mutate(double probMutate) {
        List<Boolean> genes = this.getGenes();
        for (int i = 0; i < this.getSize(); i++) {
            if(Math.random() < probMutate) {
                genes.set(i, !genes.get(i));
            }
        }
        this.setGenes(genes);
        
        // debug print
        /*System.out.print("Son muta: ");
        this.printGenes();
        */
        return this;
    }

    @Override
    protected double calcFitness() {
        this.setFitness(Math.abs(x * y * Math.sin((y * Math.PI)/4)));
        return this.getFitness();
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
    private void printGenes() {
        getGenes().forEach((gene) -> {
            System.out.print(gene ? "1" : "0");
        });
        System.out.println();
    }


}
