/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.cromossomes;

/**
 *
 * @author Fabricio
 */
public class Cromossome2b extends Cromossome2 {
    // represent the fenotype with count of -1 and 1
    @Override
    protected String fenotype() {
        int countp, countm;
        countp = 0;
        countm = 0;
        for(int gene : getGenes()){
            if(gene > 0)
                countp++;
            else
                countm++;
        }
        return String.format("#1: %3d | #-1: %3d",countp, countm);
    }    
}
