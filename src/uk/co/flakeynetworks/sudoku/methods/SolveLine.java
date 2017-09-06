package uk.co.flakeynetworks.sudoku.methods;

import java.util.ArrayList;
import uk.co.flakeynetworks.sudoku.GlobalGrid;
import uk.co.flakeynetworks.sudoku.LocalGrid;
import uk.co.flakeynetworks.sudoku.Possibilities;

/**
 *
 * @author Richard Stokes
 */
public class SolveLine implements Method {

    @Override
    public boolean solve(GlobalGrid grid) {
        
        boolean progress = false;
        
        // Check horizontally
        for(int i = 0; i < 9; i++) {
            
            // Check row
            if(checkRow(i, grid)) progress = true;
        } // end of for
        
        // Check Vertically
        for(int i = 0; i < 9; i++) {
            
            // Check row
            if(checkColumn(i, grid)) progress = true;
        } // end of for
        
        return progress;
    } // end of solve
    
    
    public boolean checkRow(int i, GlobalGrid grid) {
        
        boolean progress = false;
        
        int globalLocalRow = (int) i / 3;
        int localRow = i % 3;
        
        // Get all the boxes we are working with.
        ArrayList<LocalGrid> grids = new ArrayList<>();
        grids.add(grid.getLocal(globalLocalRow, 0));
        grids.add(grid.getLocal(globalLocalRow, 1));
        grids.add(grid.getLocal(globalLocalRow, 2));
        
        // Clear all the previous possibilities
        for(int j = 0; j < grids.size(); j++)
            grids.get(j).clearTempPossibilites();

        
        // Get a list of all the currently solved numbers
        ArrayList<Integer> solved = new ArrayList<>();
        
        for(int j = 0; j < grids.size(); j++) {
            
            LocalGrid localGrid = grids.get(j);
            
            for(int k = 0; k < 3; k++) {

                int number = localGrid.getNumber(localRow, k);
                
                if(number != 0)
                    solved.add(number);
            } // end of for
        } // end of for
        
        ArrayList<Integer> remaining = new ArrayList<>();
        // Calculate the remaining numbers
        for(int j = 1; j < 10; j++) {
            
            if(solved.contains(j)) continue;
            remaining.add(j);
        } // end of for
        
        
        // Go through the row and see what square can have the possibilities
        for(int j = 0; j < grids.size(); j++) {
            
            LocalGrid localGrid = grids.get(j);
            
            for(int k = 0; k < 3; k++) {

                for(int l = 0; l < remaining.size(); l++) {

                    // Check to see if the square has been solved.
                    if(localGrid.getNumber(localRow, k) != 0) continue;
                    
                    if(localGrid.isPossible(localRow, k, remaining.get(l))) {
                        Possibilities pos = localGrid.getTempPossibilities(localRow, k);
                        pos.addPossibilities(remaining.get(l));
                    } // end of if
                } // end of for
            } // end of for
        } // end of for
        
        
        // Go through and see if there are any squares with only one possibility.
        for(int j = 0; j < grids.size(); j++) {
            
            LocalGrid localGrid = grids.get(j);
            
            for(int k = 0; k < 3; k++) {

                // Check to see if the square has been solved.
                if(localGrid.getNumber(localRow, k) != 0) continue;
                    
                Possibilities pos = localGrid.getTempPossibilities(localRow, k);
                if(pos.getNumberOfPossibilities() == 1) {
                 
                    int possibility = pos.getPossibilities().get(0);
                    localGrid.setNumber(localRow, k, possibility);
                    remaining.remove(new Integer(possibility));
                    progress = true; 
                } // end of if
            } // end of for
        } // end of for
        
        
        // Go through and check for a number that only has one possible place.
        for(int j = 0; j < remaining.size(); j++) {
            
            int checkingNumber = remaining.get(j);
            int count = 0; 
            int lastGrid = 0;
            int lastColumn = 0;
            
            for(int g = 0; g < grids.size(); g++) {
                
                LocalGrid localGrid = grids.get(g);
                
                for(int c = 0; c < 3; c++) {
                    
                    Possibilities pos = localGrid.getTempPossibilities(localRow, c);
                    if(pos.contains(checkingNumber)) {
                        count++;
                        lastGrid = g;
                        lastColumn = c;
                    } // end of if
                } // end of for
            } // end of for
            
            if(count == 1) {
                grids.get(lastGrid).setNumber(localRow, lastColumn, checkingNumber);
                progress = true;
            } // end of if
        } // end of for
        
        return progress;
    } // end of checkRow
    
    
    public boolean checkColumn(int i, GlobalGrid grid) {
        
        boolean progress = false;
        
        int globalLocalColumn = (int) i / 3;
        int localColumn = i % 3;
        
        // Get all the boxes we are working with.
        ArrayList<LocalGrid> grids = new ArrayList<>();
        grids.add(grid.getLocal(0, globalLocalColumn));
        grids.add(grid.getLocal(1, globalLocalColumn));
        grids.add(grid.getLocal(2, globalLocalColumn));
        
        // Clear all the previous possibilities
        for(int j = 0; j < grids.size(); j++)
            grids.get(j).clearTempPossibilites();

        
        // Get a list of all the currently solved numbers
        ArrayList<Integer> solved = new ArrayList<>();
        
        for(int j = 0; j < grids.size(); j++) {
            
            LocalGrid localGrid = grids.get(j);
            
            for(int k = 0; k < 3; k++) {

                int number = localGrid.getNumber(k, localColumn);
                
                if(number != 0)
                    solved.add(number);
            } // end of for
        } // end of for
        
        ArrayList<Integer> remaining = new ArrayList<>();
        // Calculate the remaining numbers
        for(int j = 1; j < 10; j++) {
            
            if(solved.contains(j)) continue;
            remaining.add(j);
        } // end of for
        
        
        // Go through the row and see what square can have the possibilities
        for(int j = 0; j < grids.size(); j++) {
            
            LocalGrid localGrid = grids.get(j);
            
            for(int k = 0; k < 3; k++) {

                for(int l = 0; l < remaining.size(); l++) {

                    // Check to see if the square has been solved.
                    if(localGrid.getNumber(k, localColumn) != 0) continue;
                    
                    if(localGrid.isPossible(k, localColumn, remaining.get(l))) {
                        Possibilities pos = localGrid.getTempPossibilities(k, localColumn);
                        pos.addPossibilities(remaining.get(l));
                    } // end of if
                } // end of for
            } // end of for
        } // end of for
        
        
        // Go through and see if there are any squares with only one possibility.
        for(int j = 0; j < grids.size(); j++) {
            
            LocalGrid localGrid = grids.get(j);
            
            for(int k = 0; k < 3; k++) {

                // Check to see if the square has been solved.
                if(localGrid.getNumber(k, localColumn) != 0) continue;
                    
                Possibilities pos = localGrid.getTempPossibilities(k, localColumn);
                if(pos.getNumberOfPossibilities() == 1) {
                 
                    int possibility = pos.getPossibilities().get(0);
                    localGrid.setNumber(k, localColumn, possibility);
                    remaining.remove(new Integer(possibility));
                    progress = true; 
                } // end of if
            } // end of for
        } // end of for
        
        
        // Go through and check for a number that only has one possible place.
        for(int j = 0; j < remaining.size(); j++) {
            
            int checkingNumber = remaining.get(j);
            int count = 0; 
            int lastGrid = 0;
            int lastRow = 0;
            
            for(int g = 0; g < grids.size(); g++) {
                
                LocalGrid localGrid = grids.get(g);
                
                for(int r = 0; r < 3; r++) {
                    
                    Possibilities pos = localGrid.getTempPossibilities(r, localColumn);
                    if(pos.contains(checkingNumber)) {
                        count++;
                        lastGrid = g;
                        lastRow = r;
                    } // end of if
                } // end of for
            } // end of for
            
            if(count == 1) {
                grids.get(lastGrid).setNumber(lastRow, localColumn, checkingNumber);
                progress = true;
            } // end of if
        } // end of for
        
        return progress;
    } // end of checkColumn
} // end of SolveLine
