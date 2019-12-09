/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.SGA;

import geneticalgorithms.Statistics.GenerationStatisticsSorted;
import geneticalgorithms.cromossomes.Cromossome;
import geneticalgorithms.cromossomes.ICromossomeFactory;
import geneticalgorithms.populations.PopulationSorted;
import java.util.List;

import geneticalgorithms.StdDraw;

/**
 *
 * @author Fabricio
 */
public class SimpleGASorted extends SimpleGA {
    final int bestToSave;
    
    @Override
    public void execute(int generations, boolean quiet, boolean saveSummary, String distFilename, List<Integer> genToSave) {
        if(getPopulation() == null) {
            System.out.println("Nenhuma popuplacao configurada");
            return;
        }
        final int step = (generations > 15 ? generations / 15 : 1);
        
        getPopulation().init();
        
        // always saves distribution for fist generation
        if(distFilename != null)
            getPopulation().saveCromossomes(distFilename + String.format("g%d.csv", 0));
        
        if(!quiet)
            printGenerationInfo(0);
        this.setBestCromossomeAll(getPopulation().getBestCromossome());
        GenerationStatisticsSorted statistic = new GenerationStatisticsSorted(getPopulation().getSize(), 0);
        statistic.setPopulationDetails(getPopulation().getAvgSpecial(), getPopulation().getVarSpecial(), 
                getPopulation().getCromossomes().subList(0, bestToSave), 
                getPopulation().getCromossomes().subList(getPopulation().getCromossomes().size() - bestToSave, getPopulation().getCromossomes().size()));
        this.getStatistics().add(statistic);
        
        // test with linear variating pm
        double probMutate = getPopulation().getProbMutate();
        
        //StdDraw.setCanvasSize(512, (int)(10*512/10));
        //StdDraw.setXscale(0, 10);
        //StdDraw.setYscale(0, 10);
        
        this.writeImage(this.getPopulation().getBestCromossome(), 0);
        
        for (int i = 0; i < generations; i++) {
            statistic = new GenerationStatisticsSorted(getPopulation().getSize(), i + 1);
            
            // test with linear variating pm
            getPopulation().setProbMutate(probMutate + (i * 0 * probMutate)/generations);  // or uses (i * 2*probMutate) // or uses (i * 0.05)
            
            getPopulation().nextGeneration(statistic);
            
            
            if(saveSummary) saveSummarys(i);
            
            if(!quiet)
                printGenerationInfo(i);
            else
                if( i % step == 0)
                {
                    System.out.printf("Geração %d\n", i);
                    
                    if (this.bestMaps != null) this.bestMaps.add(this.getPopulation().populationBestMap());
                    
                    // generate png from map
                    this.writeImage(this.getPopulation().getBestCromossome(), i + 1);
                    
                    // saves distribution on steps if request list is null
                    if(distFilename != null && genToSave == null)
                        getPopulation().saveCromossomes(distFilename + String.format("g%d.csv", i + 1));
                        
                }
            
            // saves distribution on list requested
            if(distFilename != null && genToSave != null && genToSave.contains(i + 1))
                getPopulation().saveCromossomes(distFilename + String.format("g%d.csv", i + 1));

            statistic.setPopulationDetails(getPopulation().getAvgSpecial(), getPopulation().getVarSpecial(), 
                getPopulation().getCromossomes().subList(0, bestToSave), 
                getPopulation().getCromossomes().subList(getPopulation().getCromossomes().size() - bestToSave, getPopulation().getCromossomes().size()));
            // append the summary for this generatios
            this.getStatistics().add(statistic);
            
            if (this.getBestCromossomeAll().getFitness() < getPopulation().getBestCromossome().getFitness()) {
                this.setBestCromossomeAll(getPopulation().getBestCromossome());
            }
        }
        System.out.println("Melhor cromossomo das gerações:");
        System.out.println(this.getBestCromossomeAll());
        
        // sempre salva o ultimo
        if (this.bestMaps != null) this.bestMaps.add(this.getPopulation().populationBestMap());
        
        this.writeImage(this.getPopulation().getBestCromossome(), generations);
        
        // saves last distribution if not saved yet
        if (distFilename != null) {
            if(genToSave != null){
                if(!genToSave.contains(generations))
                    getPopulation().saveCromossomes(distFilename + String.format("g%d.csv", generations));
            }
            else {
                if((generations - 1) % step != 0)
                    getPopulation().saveCromossomes(distFilename + String.format("g%d.csv", generations));
            }
        }
    }
    
    
    public SimpleGASorted(int sizePopulation, double probMutate, double probCrossover, 
            int elite, ICromossomeFactory cromossomeFactory, int bestToSave) {
        this.setPopulation(new PopulationSorted(sizePopulation, probMutate, probCrossover, elite, cromossomeFactory));
        this.bestToSave = bestToSave;
    }

    private void writeImage(Cromossome bestCromossome, int instante) {
//        List<Integer> genes = bestCromossome.getGenes();
//        
//        for(int i = 0; i < 10; i++) {
//            for(int j = 0; j < 10; j++) {
//                int valor = genes.get(i*10 + j);
//
//                if(valor < 0) {
//                    StdDraw.setPenColor(220, 0, 0);
//                }
//                else {
//                    StdDraw.setPenColor(0, 220, 0);
//                }
//                
//                StdDraw.filledSquare(j+0.5, 10-i-0.5, 0.5);
//            }
//        }
//        
//        String t = String.valueOf(this);
//        
//        String filename = "D:\\OneDrive\\IME-BMAC\\7o Sem - 01_2019\\MAP2040 - TC\\Resultados\\MAPA_G";
//        StdDraw.save(filename + instante + ".png");
//        
//        StdDraw.show(500);
        //while(!StdDraw.mousePressed());
    }
    
}
