/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.cromossomes.operators;

import geneticalgorithms.Statistics.GenerationStatistics;
import geneticalgorithms.cromossomes.Cromossome;

/**
 *
 * @author Fabricio Kassardjian
 */
public interface IMutate {
    public Cromossome mutate(Cromossome me, double probMutate, GenerationStatistics stat);
}