/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.cromossomes;

import geneticalgorithms.cromossomes.operators.ICrossOver;
import geneticalgorithms.cromossomes.operators.IMutate;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Fabricio Kassardjian
 */
public class Cromossome3 extends Cromossome<Double> {
    static public final int rows = 2^(4 * 4), cols = 2^(4 * 4);
    
    public Cromossome3(ICrossOver crossover, IMutate mutate){
        super(crossover, mutate);
        setMutated(false);
    }
     
    public Cromossome3(Cromossome3 cromo) {
        this(cromo.icrossover, cromo.imutate);
    }
    
    
    @Override
    public Cromossome getCopy() {
        return new Cromossome3(this);
    }

    @Override
    public List<Double> getPossibleStates() {
        throw new UnsupportedOperationException("This cromossome doesn't have states"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected double calcFitness() {
        // must calc the distance between the two ising models
        return 0;        
    }

    @Override
    public void initGenes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String fenotype() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String heritageMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String mutateMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void printGenes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
