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
public class Cromossome2Factory implements ICromossomeFactory{
    private Cromossome2Type type;
    private ICrossOver cross;
    
    /**
     * @return 
     */
    public Cromossome2Type getType(){
        return type;
    }

    /**
     * @param value 
     */
    public void setType(Cromossome2Type value){
        type = value;
    }
    
    public Cromossome2Factory() {
        this.type = Cromossome2Type.a;
        cross = new CrossOverSinglePoint();
    }
    
    public Cromossome2Factory(Cromossome2Type type) {
        this.type = type;
        
        switch(type) {
            case a:
                cross = new CrossOverSinglePoint();
                break;
            case b:
                cross = new CrossOverTwoPoints();
               break;
            case c:
                cross = new CrossOverUniform();
                break;
            default:
                cross = new CrossOverSinglePoint();
        }
    }
    
    @Override
    public Cromossome getNewCromossome() {
        Cromossome c;
        switch(type){
            case a:
                c = new Cromossome2(cross);
                break;
            case b:
                c = new Cromossome2b(cross);
                break;
            case c:
                c = new Cromossome2(cross);
                break;
            default:
                c = new Cromossome2(cross);
        }
        return c;
    }
    
    public static enum Cromossome2Type {
        a,
        b,
        c
    }
}
