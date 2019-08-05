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
public class Cromossome2Factory implements ICromossomeFactory{
    @Override
    public Cromossome getNewCromossome() {
        Cromossome2 c = new Cromossome2();
        return c;
    }
}
