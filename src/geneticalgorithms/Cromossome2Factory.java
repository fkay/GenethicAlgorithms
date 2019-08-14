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
    private Cromossome2Type type;
    
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
    }
    
    public Cromossome2Factory(Cromossome2Type type) {
        this.type = type;
    }
    
    @Override
    public Cromossome getNewCromossome() {
        Cromossome c;
        switch(type){
            case a:
                c = new Cromossome2();
                break;
            case b:
                c = new Cromossome2b();
                break;
            default:
                c = new Cromossome2();
        }
        return c;
    }
    
    public static enum Cromossome2Type {
        a,
        b
    }
}
