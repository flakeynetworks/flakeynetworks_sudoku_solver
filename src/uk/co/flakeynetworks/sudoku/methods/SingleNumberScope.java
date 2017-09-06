package uk.co.flakeynetworks.sudoku.methods;

import java.util.ArrayList;
import uk.co.flakeynetworks.sudoku.GlobalGrid;
import uk.co.flakeynetworks.sudoku.LocalGrid;

/**
 *
 * @author Richard Stokes
 */
public class SingleNumberScope implements Method {

    
    @Override
    public boolean solve(GlobalGrid grid) {
        
        boolean progress = false;
        
        // Go through all the number posibilites 1 - 9
        for(int p = 1; p < 10; p++) {
            
            // Go through all the local grids.
            for(int i = 0; i < 9; i++) {

                LocalGrid local = grid.getLocal(i);
                
                // Check of the grid already has the number.
                if(local.doesContainNumber(p)) continue;
                
                // The list of possible indexes for this number p.
                ArrayList<Integer> possibleIndexes = new ArrayList<>();
                
                // Go through all the positions in the local grid.
                for(int j = 0; j < 9; j++) {

                    // If there is already a number there continue.
                    if(local.getNumber(j) != 0) continue;

                    // Check if it is possible.
                    if(!local.isPossible(j, p)) continue;
                    
                    // Add to the list of possibilities
                    possibleIndexes.add(j);
                } // end of for
                
                // Check all the possible indexes. If there is only one the mark it in.
                if(possibleIndexes.size() == 1) {
                    
                    local.setNumber(possibleIndexes.get(0), p);
                    progress = true;
                } // end of if
            } // end of for
        } // end of for
        
        return progress;
    } // end of solve
} // end of SingleNumberScope
