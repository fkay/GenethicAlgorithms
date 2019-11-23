/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.cromossomes;

import geneticalgorithms.cromossomes.operators.*;
import geneticalgorithms.populations.Population;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Fabricio
 */
public class Cromossome2Factory implements ICromossomeFactory{
    private Cromossome2Type type;
    private ICrossOver cross;
    private IMutate mute;
    
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
        mute = new MutateState();
    }
    
    public Cromossome2Factory(Cromossome2Type type) {
        this.type = type;
        mute = new MutateState();
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
                c = new Cromossome2(cross, mute);
                break;
            case b:
                c = new Cromossome2b(cross, mute);
                break;
            case c:
                c = new Cromossome2(cross, mute);
                break;
            default:
                c = new Cromossome2(cross, mute);
        }
        return c;
    }
    
    @Override
    public boolean saveCromossomes(String filename, Population population) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Cromossomo; fit; E\n");
            for(int j = 0; j < population.getSize(); j++) {
                Cromossome2 c = (Cromossome2) population.getCromossomes().get(j);
                writer.write(String.format("%d;%f;%d\n", 
                        j + 1, c.getFitness(), c.getEnergy()));
            }
        }
        catch (IOException e) {
            System.out.println("Erro ao gravar arquivo de resultados: " + filename);
            System.out.println(e.getMessage());
            return false;
        }
        return true; 
    }
    
    public static enum Cromossome2Type {
        a,
        b,
        c
    }
}
