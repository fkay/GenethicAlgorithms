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
public class GeneticAlgorithms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int tests = 10;
        
        final int populationSize = 30;
        final int generations = 300;
        
        final double probMutate = 0.01;
        final ICromossomeFactory cromoFactory = new Cromossome2Factory();
        
        SimpleGA sga = new SimpleGA(populationSize, probMutate, cromoFactory);
        
//        Cromossome2 cromo = new Cromossome2();
//        cromo.initMaxGenes();
//        System.out.println(cromo.toString());
        
        sga.execute(generations);
    }
    
}
