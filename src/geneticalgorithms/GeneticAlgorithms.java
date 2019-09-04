/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms;

import geneticalgorithms.SGA.SimpleGASorted;
import geneticalgorithms.SGA.SimpleGA;
import geneticalgorithms.randomsearch.RandomSearchSorted;
import geneticalgorithms.cromossomes.ICromossomeFactory;
import geneticalgorithms.cromossomes.Cromossome2Factory;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

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
        
        final int bestToSave = 3;   // how many cromossomes to save from top an bottom
        
        final int populationSize = 30;
        final int generations = 300;
        
        final double probMutate = 0.005;
        final double probCrossover = 0.80;
        final ICromossomeFactory<Integer> cromoFactory = new Cromossome2Factory(Cromossome2Factory.Cromossome2Type.c);
        String filename;

        String onedrive = System.getenv("OneDrive");
        
//        Cromossome2 cromo = new Cromossome2();
//        cromo.initMaxGenes();
//        System.out.println(cromo.toString());

        for (int i = 0; i < tests; i++) {
            SimpleGA sga = new SimpleGASorted(populationSize, probMutate, probCrossover, cromoFactory, bestToSave);
        
            sga.execute(generations, true);
        
            RandomSearchSorted randomSearchSorted = new RandomSearchSorted(populationSize * generations, cromoFactory, bestToSave);
            
            filename = String.format(onedrive + "\\IME-BMAC\\7o Sem - 01_2019\\MAP2040 - TC\\Resultados\\summary%2d.csv", i);
            sga.statisticsToFile(filename);
            filename = String.format(onedrive + "\\IME-BMAC\\7o Sem - 01_2019\\MAP2040 - TC\\Resultados\\summary%2d_rs.csv", i);
            randomSearchSorted.statisticsToFile(filename);
        }
        
        filename = onedrive + "\\IME-BMAC\\7o Sem - 01_2019\\MAP2040 - TC\\Resultados\\Parameters.csv";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("tamanho.populacao;geracoes;prob.cross;prob.mut;testes\n");
            writer.write(String.format(Locale.ROOT, "%d;%d;%f;%f;%d\n", 
                    populationSize, generations, probCrossover, probMutate, tests));
        }
        catch (IOException e) {
            System.out.println("Erro ao gravar arquivo de resultados: " + filename);
            System.out.println(e.getMessage());
        }
        
    }
    
}
