package uk.co.flakeynetworks.sudoku.examples;

import uk.co.flakeynetworks.sudoku.GlobalGrid;

/**
 *
 * @author Richard Stokes
 */
public class ExampleGrid {
    
    
    private final int[] grid;
    private GlobalGrid userSolution;
    private final int[] solution;
    private final String description;
    
    
    public ExampleGrid(int[] grid, int[] solution, String description) {
        
        this.grid = grid;
        this.solution = solution;
        this.description = description;
    } // end of constructor
    
    
    public int[] getGrid() { return grid; } // end of getGrid
    
    public int[] getSolution() { return solution; } // end of getSolution
    
    public GlobalGrid getUserSolution() { return userSolution; } // end of getUserSolution
    
    public String getDescription() { return description; } // end of getDescription
    
    public void setUserSolution(GlobalGrid grid) {
        
        userSolution = grid;
    } // end of setUserSolution
    
    
    public boolean isCorrect(int[] comparision) {
        
        if(comparision == null) return false;
        if(grid == null) return false;
        if(solution == null) return false;
        
        if(comparision.length != solution.length && grid.length != solution.length) return false;
        
        for(int i = 0; i < comparision.length; i++) {
            
            if(comparision[i] == 0) continue;
            if(comparision[i] != solution[i]) return false;
        } // end of for
        
        return true;
    } // end of isCorrect
} // end of ExampleGrid
