/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.cromossomes.operators;

import geneticalgorithms.Statistics.GenerationStatistics;
import geneticalgorithms.cromossomes.Cromossome;
import geneticalgorithms.cromossomes.ICromossomeFactory;

/**
 * Interface for CrossOver
 * @author fkay1
 */
public interface ICrossOver {
    public Cromossome crossover(Cromossome me, Cromossome other, double probCrossover,
            GenerationStatistics stat);
}
