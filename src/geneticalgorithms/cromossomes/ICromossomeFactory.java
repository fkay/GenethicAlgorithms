/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.cromossomes;

/**
 *
 * @author Fabricio
 */
public interface ICromossomeFactory<T extends Cromossome> {

    /**
     *
     * @return A new Cromossome object
     */
    public T getNewCromossome();
}
