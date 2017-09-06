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
        
        // Go through all the number posibilites 1 - 9 p.
        for(int p = 1; p < 10; p++) {
            
            // Go through all the local grids i.
            for(int i = 0; i < 9; i++) {

                LocalGrid local = grid.getLocal(i);
                
                // Check of the grid already has the number.
                // TODO change this to check a list of completed numbers for the local grid.
                if(local.doesContainNumber(p)) continue;
                
                int count = 0;
                int lastIndex = 0;
                
                // Go through all the possibilities and see if there is only one possible square j.
                for(int j = 0; j < 9; j++) {

                    // If there is already a number there continue.
                    if(local.getNumber(j) != 0) continue;

                    // Check if it is possible.
                    if(local.getPossibilities(j).contains(p)) {
                        
                        count++;
                        lastIndex = j;
                    } // end of if
                } // end of for
                
                // Check all the possible indexes. If there is only one the mark it in.
                if(count == 1) {
                    
                    local.setNumber(lastIndex, p);
                    progress = true;
                } // end of if
            } // end of for
        } // end of for
        
        return progress;
    } // end of solve
} // end of SingleNumberScope
