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
public class Cromossome1Factory implements ICromossomeFactory {
    private static ICrossOver cross = new CrossOverSinglePoint();
    private static IMutate mute = new MutateState();
    
    @Override
    public Cromossome getNewCromossome() {
        Cromossome1 c = new Cromossome1(cross, mute); //To change body of generated methods, choose Tools | Templates.
        return(c);
    }
        
}
