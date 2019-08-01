/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms;

/**
 *
 * @author Fabricio
 */
public class SimpleGA1 extends SimpleGA{
    final double probMutate = 0.01;
    
    public SimpleGA1(int sizePopulation) {
        Population population = new Population1(sizePopulation, probMutate);
        
        this.setPopulation(population);
    }
}
