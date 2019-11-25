/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms;

import geneticalgorithms.SGA.*;
import geneticalgorithms.randomsearch.*;
import geneticalgorithms.cromossomes.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

/**
 *
 * @author Fabricio
 */
public class GeneticAlgorithms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int tests = 1000;
        
        final int bestToSave = 3;   // how many cromossomes to save from top an bottom
        
        final int populationSize = 30;
        final int generations = 40;
        
        final boolean saveFirstTesteResumes = false;
        final boolean saveAllStatistics = true;
        
        final double probMutate = 0.02;
        final double probCrossover = 0.8;
        final ICromossomeFactory cromoFactory = new Cromossome2Factory(Cromossome2Factory.Cromossome2Type.c);
        //final ICromossomeFactory cromoFactory = new Cromossome1Factory();
        String filename;
        String baseFilename = "D:\\OneDrive\\IME-BMAC\\7o Sem - 01_2019\\MAP2040 - TC\\Resultados\\";
        String file_pre = "C2";
        
        //Locale.setDefault(new Locale("pt", "BR"));
        Locale.setDefault(Locale.ROOT);

        String resultadoCompFileName = baseFilename + String.format("Comparativo_%s.csv", file_pre);
        String distGAFilename = baseFilename + String.format("GA_%s_", file_pre);    // execução do GA completa com geracao e .csv
        String distGAFilename_temp;
        Integer[] genToSave = {10, 20, 30, 40};
        String distRandomFilename = baseFilename + String.format("Random_%s.csv", file_pre); // se usar nulos, nao salva
        
        for (int i = 0; i < tests; i++) {
            SimpleGA sga = new SimpleGASorted(populationSize, probMutate, probCrossover, cromoFactory, bestToSave);
            //SimpleGA sga = new SimpleGA(populationSize, probMutate, probCrossover, cromoFactory);
            
            boolean saveResumes = (i == 0) && saveFirstTesteResumes;
            distGAFilename_temp = distGAFilename + String.format("t%d_", i + 1);
            sga.execute(generations, true, saveResumes, distGAFilename_temp, Arrays.asList(genToSave));
        
            if(saveResumes){
                filename = baseFilename + String.format("resumo_%s_%2d.txt", file_pre, i);
                sga.summaryToFile(filename);
                filename = baseFilename + String.format("resumo2_%s_%2d.txt", file_pre, i);
                sga.summaryResumeToFile(filename);
            }
            
            //RandomSearch randomSearch = new RandomSearch(populationSize * generations, cromoFactory, distRandomFilename);
            RandomSearchSorted randomSearch = new RandomSearchSorted(populationSize * generations, cromoFactory, bestToSave, distRandomFilename);
            
            if(saveAllStatistics) {
                filename = baseFilename + String.format("summary_%s_%2d.csv", file_pre, i);
                sga.statisticsToFile(filename);
                filename = baseFilename + String.format("summary_%s_%2d_rs.csv", file_pre, i);
                randomSearch.statisticsToFile(filename);
            }
            
            // só salva a distribuição das gerações no primeiro
            //distRandomFilename = null;
            //distGAFilename = null;
            
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(resultadoCompFileName, i != 0 ))) {
                if(i == 0) writer.write("Teste; Melhor GA; Melhor RANDOM \n");
                writer.write(String.format("%d;%f;%f\n", 
                        i, sga.getBestCromossomeAll().getFitness(), randomSearch.getPopulation().getBestCromossome().getFitness()));
            }
            catch (IOException e) {
                System.out.println("Erro ao gravar arquivo de resutlados: " + resultadoCompFileName);
                System.out.println(e.getMessage());
            }
        }
        
        filename = "D:\\OneDrive\\IME-BMAC\\7o Sem - 01_2019\\MAP2040 - TC\\Resultados\\Parameters_" + file_pre + ".csv";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("tamanho.populacao;geracoes;prob.cross;prob.mut;testes\n");
            writer.write(String.format("%d;%d;%f;%f;%d\n", 
                    populationSize, generations, probCrossover, probMutate, tests));
        }
        catch (IOException e) {
            System.out.println("Erro ao gravar arquivo de resutlados: " + filename);
            System.out.println(e.getMessage());
        }
        
    }
    
}
