package uk.co.flakeynetworks.sudoku;

import uk.co.flakeynetworks.sudoku.examples.Grid;
import uk.co.flakeynetworks.sudoku.examples.ExampleGrids;

/**
 *
 * @author Richard Stokes
 */
public class Engine {

    
    public static boolean solveGrid(Grid grid) {
        
        GlobalGrid global = new GlobalGrid(grid.getGrid());
        
        System.out.println("\n\nSolving Grid: " + grid.getDescription());
        
        long startTime = System.currentTimeMillis();
        
        boolean solved = global.solve();
        
        long endTime = System.currentTimeMillis();
        

        // Check if solved.
        if(grid.isCorrect(global.getGrid())) {
            if(global.isSolved()) {
                System.out.println("Grid has been solved: ");
            } else {
                System.out.println("Furtherest Point: ");
                global.printGrid();
            } // end of else
        } else {
            System.out.println("Error! Solution does not match solution: ");
            global.printGrid();
        } // end of else
        
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
        
        return solved;
    } // end of solveGrid
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        solveGrid(ExampleGrids.EXAMPLE_GRID_0);
        solveGrid(ExampleGrids.EXAMPLE_GRID_1);
        solveGrid(ExampleGrids.EXAMPLE_GRID_2);
        solveGrid(ExampleGrids.EXAMPLE_GRID_3);
        solveGrid(ExampleGrids.EXAMPLE_GRID_4);
        solveGrid(ExampleGrids.EXAMPLE_GRID_5);
    } // end of main
} // end of Engine
