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
public class SimpleGASorted extends SimpleGA {
    final int bestToSave = 5;
    
    @Override
    public void execute(int generations, boolean quiet) {
        if(getPopulation() == null) {
            System.out.println("Nenhuma popuplacao configurada");
            return;
        }
        final int step = generations / 15;
        
        getPopulation().init();
        if(!quiet)
            printGenerationInfo(0);
        this.setBestCromossomeAll(getPopulation().getBestCromossome());
        this.getSummary().add(new GenerationSummarySorted(getPopulation().getCromossomes().subList(0, bestToSave), 
                            getPopulation().getAvgFitness(), 0));
        for (int i = 0; i < generations; i++) {
            getPopulation().nextGeneration();
            if(!quiet)
                printGenerationInfo(i);
            else
                if( i % step == 0)
                    System.out.printf("Geração %d\n", i);
            
            // append the summary for this generatios
            this.getSummary().add(new GenerationSummarySorted(getPopulation().getCromossomes().subList(0, bestToSave), 
                            getPopulation().getAvgFitness(), getPopulation().countMutated()));
            
            if (this.getBestCromossomeAll().getFitness() < getPopulation().getBestCromossome().getFitness()) {
                this.setBestCromossomeAll(getPopulation().getBestCromossome());
            }
        }
        System.out.println("Melhor cromossomo das gerações:");
        System.out.println(this.getBestCromossomeAll());
    }
    
    
    public SimpleGASorted(int sizePopulation, double probMutate, ICromossomeFactory cromossomeFactory) {
        this.setPopulation(new PopulationSorted(sizePopulation, probMutate, cromossomeFactory));
    }
    
}
