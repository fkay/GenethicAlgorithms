/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wasserstein;

import wasserstein.models.Ising;

/**
 *
 * @author Fabricio Kassardjian
 */
public class Wasserstein {
   /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ising i1 = new Ising();
        i1.randomInit();
        
        System.out.println(i1.toString());
        
        i1.calcProbs();
        
        double sum = i1.getProbs().stream().reduce(0.0, Double::sum);
        
        System.out.println(String.format("Soma probs: %.3f", sum));
        
        System.out.println("Programa encerrado");
        
    }
}
