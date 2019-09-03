/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.cromossomes;

import geneticalgorithms.cromossomes.operators.*;

/**
 *
 * @author Fabricio
 */
public class Cromossome1Factory implements ICromossomeFactory<Boolean> {
    private static final ICrossOver<Boolean> cross = new CrossOverSinglePoint<>();
    private static final IMutate<Boolean> mute = new MutateState<>();
    
    @Override
    public Cromossome<Boolean> getNewCromossome() {
        Cromossome1 c = new Cromossome1(cross, mute); //To change body of generated methods, choose Tools | Templates.
        return(c);
    }
        
}
