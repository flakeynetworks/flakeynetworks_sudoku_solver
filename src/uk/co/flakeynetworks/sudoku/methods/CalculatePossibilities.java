package uk.co.flakeynetworks.sudoku.methods;

import uk.co.flakeynetworks.sudoku.GlobalGrid;
import uk.co.flakeynetworks.sudoku.LocalGrid;
import uk.co.flakeynetworks.sudoku.Possibilities;

/**
 *
 * @author Richard Stokes
 */
public class CalculatePossibilities {
    
    public static void calculate(GlobalGrid grid) {
        
        if(grid == null) return;
        
        // Go through all the local grid rows i
        for(int i = 0; i < 3; i++) {
            
            // Go through all the local grid columns j
            for(int j = 0; j < 3; j++) {
                
                LocalGrid local = grid.getLocal(j, j);
                local.clearPossibilities();
                
                // Go through all the squares k and calculate all the possibilities
                for(int k = 0; k < 9; k++) {
                    
                    Possibilities pos = local.getPossibilities(k);
                    
                    // Go through all the numbers z and calculate if it is possible.
                    for(int z = 1; z < 10; z++) {
                        
                        if(local.isPossible(k, z))
                            pos.addPossibilities(z);
                    } // end of for
                } // end of for
            } // end of for
        } // end of for
    } // end of GlobalGrid
} // end of CalculatePissibilities
