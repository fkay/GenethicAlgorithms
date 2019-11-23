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
public class Cromossome1Factory implements ICromossomeFactory {
    private static ICrossOver cross = new CrossOverSinglePoint();
    private static IMutate mute = new MutateState();
    
    @Override
    public Cromossome getNewCromossome() {
        Cromossome1 c = new Cromossome1(cross, mute); //To change body of generated methods, choose Tools | Templates.
        return(c);
    }
    
    @Override
    public boolean saveCromossomes(String filename, Population population) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Cromossomo; x; y; fit\n");
            for(int j = 0; j < population.getSize(); j++) {
                Cromossome1 c = (Cromossome1) population.getCromossomes().get(j);
                writer.write(String.format("%d;%f;%f;%f\n", 
                        j + 1, c.getX(), c.getY(), c.getFitness()));
            }
        }
        catch (IOException e) {
            System.out.println("Erro ao gravar arquivo de resultados: " + filename);
            System.out.println(e.getMessage());
            return false;
        }
        return true; 
    }
        
}
