/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.cromossomes;

import geneticalgorithms.cromossomes.operators.*;

/**
 * Crommosome2b - uses two point Crossover
 * @author Fabricio
 */
public class Cromossome2b extends Cromossome2 {
    public Cromossome2b(ICrossOver<Integer> crossover, IMutate<Integer> mutate){
        super(crossover, mutate);
    }    
}
