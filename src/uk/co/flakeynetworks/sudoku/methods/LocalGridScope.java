package uk.co.flakeynetworks.sudoku.methods;

import uk.co.flakeynetworks.sudoku.GlobalGrid;
import uk.co.flakeynetworks.sudoku.LocalGrid;
import uk.co.flakeynetworks.sudoku.Possibilities;

/**
 *
 * @author Richard Stokes
 */
public class LocalGridScope implements Method {

    @Override
    public boolean solve(GlobalGrid grid) {
    
        boolean progress = false;
        
        // Work through all the grids.
        for(int i = 0; i < 9; i++) {
                    
            LocalGrid local = grid.getLocal(i);
            if(checkLocalGrid(local))
                progress = true;
        } // end of for
        
        return progress;
    } // end of solve
    
    
    private boolean checkLocalGrid(LocalGrid grid) {
        
        boolean progress = false;
        
        if(grid == null) return false;
        
        grid.clearTempPossibilites();
        
        // Go through all the rows i.
        for(int i = 0; i < 3; i++) {
            
            // Go through all the columns j.
            for(int j = 0; j < 3; j++) {
        
                // Go through all the possible numbers k.
                for(int k = 1; k < 10; k++) {
                    
                    // Check if this square has been solve yet.
                    if(grid.getNumber(i, j) != 0) continue;
                    
                    Possibilities pos = grid.getTempPossibilities(i, j);
                    
                    // Check if k is a possibility.
                    if(grid.isPossible(i, j, k))
                        pos.addPossibilities(k);
                } // end of for
            } // end of for
        } // end of for
        
        
        // Go through all the squares and then check for any squares that only
        // one possibility.
        // Row i
        for(int i = 0; i < 3; i++) {
            
            // Column j
            for(int j = 0; j < 3; j++) {
                
                // Check if the square has already been solved.
                if(grid.getNumber(i, j) != 0) continue;
                
                // Check if there is only one possibility left.
                Possibilities pos = grid.getTempPossibilities(i, j);
                if(pos.getNumberOfPossibilities() == 1) {
                    
                    grid.setNumber(i, j, pos.getPossibilities().get(0));
                    progress = true;
                } // end of if
            } // end of for
        } // end of for
        
        return progress;
    } // end of checkLocalGrid
} // end of LocalGridScope
