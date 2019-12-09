/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithms.cromossomes.operators;

import geneticalgorithms.Statistics.GenerationStatistics;
import geneticalgorithms.cromossomes.Cromossome;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Fabricio Kassardjian
 */
public class CrossOverCross implements ICrossOver{
    // the size of one row
    private int rowSize;

    public int getRowSize() {
        return rowSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }
    
    
    public CrossOverCross(int rowSize) {
        System.out.println("Crossover Cross - rowSize: " + rowSize);
        this.rowSize = rowSize;
    }

    @Override
    public Cromossome crossover(Cromossome me, Cromossome other, double probCrossover, GenerationStatistics stat) {
        List newGenes = new ArrayList();
        
        Cromossome newCromossome = me.getNewCromossome();
        
        List<Integer> genesH = new ArrayList<>();
        
        if(Math.random() < probCrossover) {
            stat.incnCromCrossover();
            
            // get row crossover
            int crossPoint1 = (new Double(Math.random() * (me.getSize() / rowSize) ) ).intValue();
            int crossPoint2 = (new Double(Math.random() * rowSize)).intValue();
            
            boolean parentStart = Math.random() < 0.5;
            
            for(int i = 0; i < crossPoint1; i++) {
                int rowStart = i * rowSize;
                int rowEnd = (i+1) * rowSize; 
                if(parentStart) {
                    newGenes.addAll(me.getGenes().subList(rowStart, rowStart + crossPoint2));
                    newGenes.addAll(other.getGenes().subList(rowStart + crossPoint2, rowEnd));
                    
                    genesH.addAll(Collections.nCopies(crossPoint2, 1));
                    genesH.addAll(Collections.nCopies(rowSize - crossPoint2, 2));
                } else {
                    newGenes.addAll(other.getGenes().subList(rowStart, rowStart + crossPoint2));
                    newGenes.addAll(me.getGenes().subList(rowStart + crossPoint2, rowEnd));
                    
                    genesH.addAll(Collections.nCopies(crossPoint2, 2));
                    genesH.addAll(Collections.nCopies(rowSize - crossPoint2, 1));
                }
            }
            for(int i = crossPoint1; i < me.getSize() / rowSize; i++) {
                int rowStart = i * rowSize;
                int rowEnd = (i+1) * rowSize; 
                if(parentStart) {
                    newGenes.addAll(other.getGenes().subList(rowStart, rowStart + crossPoint2));
                    newGenes.addAll(me.getGenes().subList(rowStart + crossPoint2, rowEnd));
                    
                    genesH.addAll(Collections.nCopies(crossPoint2, 2));
                    genesH.addAll(Collections.nCopies(rowSize - crossPoint2, 1));
                } else {
                    newGenes.addAll(me.getGenes().subList(rowStart, rowStart + crossPoint2));
                    newGenes.addAll(other.getGenes().subList(rowStart + crossPoint2, rowEnd));
                    
                    genesH.addAll(Collections.nCopies(crossPoint2, 1));
                    genesH.addAll(Collections.nCopies(rowSize - crossPoint2, 2));
                }
            }
        } else {
            newGenes.addAll(me.getGenes());
            genesH.addAll(Collections.nCopies(me.getSize(), 1));
        }

        newCromossome.setGenes(newGenes, false);
        newCromossome.setGenesHeritage(genesH);
        
        return newCromossome;
    }
    
}
