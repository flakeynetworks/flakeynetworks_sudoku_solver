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
        
        boolean solved = global.solve();
        
        System.out.println("\n\nFurtherest Point: ");
       
        global.printGrid();
        
        return solved;
    } // end of solveGrid
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        solveGrid(ExampleGrids.EXAMPLE_GRID_6);
    } // end of main
} // end of Engine
