/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wasserstein.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Defines a Ising model with countour
 * @author Fabricio Kassardjian
 */
public class Ising {
    static public final int rows = 4, cols = 4;
    
    private int[][] spins;
    
    private int[] contourUp; 
    private int[] contourDown;
    private int[] contourLeft;
    private int[] contourRight;
    
    private double z;
    private List<Double> probs;
    
    public void randomInit() {
        if (spins == null) {
            setSpins(new int[rows][cols]);
            setContourUp(new int[cols]);
            setContourDown(new int[cols]);
            setContourLeft(new int[rows]);
            setContourRight(new int[rows]);
        }
        
        for (int r = 0; r < rows; r++) {
            contourLeft[r] = Math.random() < 0.5 ? -1 : 1;
            contourRight[r] = Math.random() < 0.5 ? -1 : 1;
            for (int c = 0; c < cols; c++) {
                spins[r][c] = Math.random() < 0.5 ? -1 : 1;
            }
        }
        for (int c = 0; c < cols; c++) {
            contourUp[c] = Math.random() < 0.5 ? -1 : 1;
            contourDown[c] = Math.random() < 0.5 ? -1 : 1;
        }
    }
    
    /**
     * interaction energy function for spins 1 and 2
     * @param i_1
     * @param j_1
     * @param i_2
     * @param j_2
     * @return energy intercation between spins
     */
    public double J(int i_1, int j_1, int i_2, int j_2) {
        int di = Math.abs(i_1 - i_2);
        int dj = Math.abs(j_1 - j_2);
        if ((di == 0 && dj == 1) || (dj == 0 && di == 1))
            return 1;
        else
            return 0;
    }
    
    /**
     * Calculate energy from the model
     * @return 
     */
    public double energy() {
        double e = 0;
        
        // contours
        for (int r = 0; r <  rows; r++) {
            e += J(r, -1, r, 0) * contourLeft[r] * spins[r][0];
            e += J(r, cols - 1, r, cols) * contourRight[r] * spins[r][cols-1];
        }
        
        for (int c = 0; c <  cols; c++) {
            e += J(-1, c, 0, c) * contourUp[c] * spins[0][c];
            e += J(rows, c, rows - 1, c) * contourDown[c] * spins[rows-1][c]; 
        }
        
        // model center
        for (int r = 0; r < rows - 1; r++) {
            for (int c =0; c < cols - 1; c++) {
                e += J(r,c, r+1, c) * spins[r][c] * spins[r+1][c];
                e += J(r,c, r, c+1) * spins[r][c] * spins[r][c+1];
            }
            
        }
        
        // last column
        for (int r = 0; r < rows - 1; r++) {
            e += J(r,cols - 1, r+1, cols - 1) * spins[r][cols - 1] * spins[r+1][cols - 1];
        }
        // last row
        for (int c = 0; c < cols - 1; c++) {
            e += J(rows - 1, c, rows - 1, c+1) * spins[rows -1][c] * spins[rows-1][c+1];
        }
        
        return e;
    }
    
    public void calcProbs() {
        probs = new ArrayList<>();

        z = 0;
        
        for (int i = 0; i < Math.pow(2, rows * cols); i++) {
            int mask = 1;
            for(int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    spins[r][c] = (mask & i) == 0 ? -1 : 1;
                }
            }
            double e = energy();
            z += e;
            getProbs().add(e);
        }

        probs = getProbs().stream().map(p -> p/getZ()).collect(Collectors.toList());
    }
    
    
    /**
     * Return the distance between two models
     * @param other: Ising model
     * @return # diferent spins
     */
    public int rho(Ising other) {
        int dif = 0;
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                dif += spins[r][c] != other.getSpin(r, c) ? 1 : 0;
            }
        }
        return dif;
    }
    
    @Override
    public String toString() {
        String space = "     ";
        String space2 = "   ";
        String separator = " | ";
        String format = "%2d";
        String lineSeparator = "-----";
        StringBuilder sb = new StringBuilder();
        
        sb.append(space2);
        for (int c = 0; c < cols; c++)
            sb.append(lineSeparator);
        sb.append("-\n");
        
        String lineSeparator2 = sb.toString();
        
        sb = new StringBuilder();
        
        // upper contour
        sb.append(space);
        for (int c = 0; c < cols; c++) {
            sb.append(String.format(format, contourUp[c]));
            sb.append(space2);
        }
        sb.append("\n");
        sb.append(lineSeparator2);
        
        for (int r = 0; r < rows; r++) {
            sb.append(String.format(format, contourLeft[r]));
            sb.append(separator);
            for (int c = 0; c < cols; c++) {
                sb.append(String.format(format, spins[r][c]));
                sb.append(separator);
            }
            sb.append(String.format(format, contourRight[r]));
            sb.append("\n");
            sb.append(lineSeparator2);
        }
        
        // down contour
        sb.append(space);
        for (int c = 0; c < cols; c++) {
            sb.append(String.format(format, contourDown[c]));
            sb.append(space2);
        }
        sb.append("\n");

        
        return sb.toString();
    }
    

    /**
     * @return the contourUp
     */
    public int[] getContourUp() {
        return contourUp.clone();
    }

    /**
     * @param contourUp the contourUp to set
     */
    public void setContourUp(int[] contourUp) {
        this.contourUp = contourUp;
    }

    /**
     * @return the contourDown
     */
    public int[] getContourDown() {
        return contourDown.clone();
    }

    /**
     * @param contourDown the contourDown to set
     */
    public void setContourDown(int[] contourDown) {
        this.contourDown = contourDown;
    }

    /**
     * @return the contourLeft
     */
    public int[] getContourLeft() {
        return contourLeft.clone();
    }

    /**
     * @param contourLeft the contourLeft to set
     */
    public void setContourLeft(int[] contourLeft) {
        this.contourLeft = contourLeft;
    }

    /**
     * @return the contourRight
     */
    public int[] getContourRight() {
        return contourRight.clone();
    }

    /**
     * @param contourRight the contourRight to set
     */
    public void setContourRight(int[] contourRight) {
        this.contourRight = contourRight;
    }

    /**
     * @return the spins
     */
    public int[][] getSpins() {
        return spins.clone();
    }

    /**
     * @param spins the spins to set
     */
    public void setSpins(int[][] spins) {
        this.spins = spins;
    }
    
    /**
     * Return the spin value on row i and column j
     * @param i - row
     * @param j - column
     * @return spin value
     */
    public int getSpin(int i, int j) {
        return spins[i][j];
    }

    /**
     * @return the z
     */
    public double getZ() {
        return z;
    }

    /**
     * @return the probs
     */
    public List<Double> getProbs() {
        return probs;
    }
  
}
