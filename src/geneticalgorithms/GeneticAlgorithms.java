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
        final int tests = 5;
        
        final int populationSize = 30;
        final int generations = 300;
        
        final double probMutate = 0.005;
        final double probCrossover = 1.0;
        final ICromossomeFactory cromoFactory = new Cromossome2Factory();

//        Cromossome2 cromo = new Cromossome2();
//        cromo.initMaxGenes();
//        System.out.println(cromo.toString());

        for (int i = 0; i < tests; i++) {
            SimpleGA sga = new SimpleGASorted(populationSize, probMutate, probCrossover, cromoFactory);
        
            sga.execute(generations, true);
        
            String filename = String.format("E:\\OneDrive\\IME-BMAC\\7o Sem - 01_2019\\MAP2040 - TC\\Resultados\\summary%2d.csv", i);
            sga.statisticsToFile(filename);
        }
    }
    
}
