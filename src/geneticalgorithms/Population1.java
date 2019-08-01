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
public class Population1 extends Population {

    // metodo de selecao de roleta simples
    @Override
    protected Cromossome select() {
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

    @Override
    public void initPopulation() {
        List<Cromossome> cromossomes = new ArrayList();
        for(int i = 0; i < this.getSize(); i++) {
            Cromossome1 cromossome = new Cromossome1();
            cromossome.initGenes();
            cromossomes.add(cromossome);
        }
        this.setCromossomes(cromossomes);
    }
    
    public Population1(int size, double probMutate) {
        super(size, probMutate);
    }
    
}
