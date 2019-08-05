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
public class Cromossome1Factory implements ICromossomeFactory {

    @Override
    public Cromossome getNewCromossome() {
        Cromossome1 c = new Cromossome1(); //To change body of generated methods, choose Tools | Templates.
        return(c);
    }
        
}
