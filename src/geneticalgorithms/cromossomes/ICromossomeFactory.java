/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.cromossomes;

import geneticalgorithms.populations.Population;

/**
 *
 * @author Fabricio
 */
public interface ICromossomeFactory {
    public Cromossome getNewCromossome();
    
    public boolean saveCromossomes(String filename, Population population);
}
