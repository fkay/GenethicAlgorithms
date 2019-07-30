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
public class GeneticAlgorithms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int populationSize = 20;
        final int generations = 200;
        SimpleGA1 sga = new SimpleGA1(populationSize);
        
        sga.execute(generations);
    }
    
}
