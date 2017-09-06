package uk.co.flakeynetworks.sudoku;

import uk.co.flakeynetworks.sudoku.examples.ExampleGrids;

/**
 *
 * @author Richard Stokes
 */
public class Engine {

    
    public static boolean solveGrid(int[] grid) {
        
        GlobalGrid global = new GlobalGrid(grid);
        System.out.println("Finished preparing grid");
        
        System.out.println("\nStarting Grid:");
        global.printGrid();
        
        long startTime = System.currentTimeMillis();
        
        boolean solved = global.solve();
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("\n\nFurtherest Point: ");
       
        global.printGrid();
        
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
        
        return solved;
    } // end of solveGrid
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        solveGrid(ExampleGrids.EXAMPLE_GRID_6);
    } // end of main
} // end of Engine
