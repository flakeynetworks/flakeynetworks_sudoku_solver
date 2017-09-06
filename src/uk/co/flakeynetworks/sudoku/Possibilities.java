package uk.co.flakeynetworks.sudoku;

import java.util.ArrayList;

/**
 *
 * @author Richard Stokes
 */
public class Possibilities {
    
    
    private ArrayList<Integer> possibilities = new ArrayList<>();
    
    
    public Possibilities() { } // end of constructor
        
    
    public void addPossibilities(int pos) {
        
        if(pos < 1 || pos > 9) return;
        
        if(possibilities.contains(pos)) return;
        
        possibilities.add(new Integer(pos));
    } // end of addPossibilities
    
    
    public void removePossibility(int pos) {
        
        possibilities.remove(new Integer(pos));
    } // end of removePossibility
    
    
    public ArrayList<Integer> getPossibilities() {
        
        return possibilities;
    } // end of getPossibilites
    
    
    public int getNumberOfPossibilities() { return possibilities.size(); } // end of getNumberOfPossibilities
    
    
    public void clear() { possibilities.clear(); } // end of clear
    
    
    public boolean contains(int p) {
        
        return possibilities.contains(new Integer(p));
    } // end of contains
} // end of Possibilities
