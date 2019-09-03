/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.cromossomes;

/**
 *
 * @author Fabricio
 * @param <T> Genes type to extend
 */
public interface ICromossomeFactory<T> {
    public Cromossome<T> getNewCromossome();
}
