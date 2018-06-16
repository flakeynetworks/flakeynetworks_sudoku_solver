package uk.co.flakeynetworks.sudoku.methods;

import uk.co.flakeynetworks.sudoku.GlobalGrid;

/**
 *
 * @author Richard Stokes
 */
public class EliminateInLinePossibilities implements Method {

    @Override
    public boolean solve(GlobalGrid grid) {
    
        boolean progress = false;
        
        // Go through all the local grids
        for(int i = 0; i < 9; i++) {
        
            // Go through each row in the grid
            for(int r = 0; r < 3; r++) {
                
                // Check if the number has 
            } // end of for
        } // end of for
        
        return progress;
    } // end of solve
} // end of EliminateInLinePossibilities
