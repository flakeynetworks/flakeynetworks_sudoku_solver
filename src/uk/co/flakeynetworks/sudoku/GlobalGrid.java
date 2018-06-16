package uk.co.flakeynetworks.sudoku;

import uk.co.flakeynetworks.sudoku.methods.CalculatePossibilities;
import uk.co.flakeynetworks.sudoku.methods.LocalGridScope;
import uk.co.flakeynetworks.sudoku.methods.Method;
import uk.co.flakeynetworks.sudoku.methods.SingleNumberScope;
import uk.co.flakeynetworks.sudoku.methods.SolveLine;

/**
 *
 * @author Richard Stokes
 */
public class GlobalGrid {
    
    
    private LocalGrid[] grids = {new LocalGrid(this, 0, 0), new LocalGrid(this, 0, 1), new LocalGrid(this, 0, 2), 
                                new LocalGrid(this, 1, 0), new LocalGrid(this, 1, 1), new LocalGrid(this, 1, 2), 
                                new LocalGrid(this, 2, 0), new LocalGrid(this, 2, 1), new LocalGrid(this, 2, 2) };
    
    
    private static final Method[] methods = { new SingleNumberScope(), new SolveLine(), new LocalGridScope() } ;
    
    
    public GlobalGrid(int[] grid) {
        
        loadGrid(grid);
    } // end of null constructor
    
    
    private void loadGrid(int[] grid) {
        
        // Validate the grid
        if(grid == null) return;
        if(grid.length < 81) return;
        
        for(int i = 0; i < grid.length; i++) {
            
            // Invalid number. Number must be 1-9 or 0 for empty
            if(grid[i] < 0 || grid[i] > 9) return;
        } // end of for
        
        
        for(int i = 0; i < grids.length; i++) {
        
            LocalGrid localGrid = grids[i];
            
            // Go for row
            for(int j = 0; j < 3; j++) {
                
                // Go for column
                for(int k = 0; k < 3; k++) {
                    
                    // Calculate the index within the local grid.
                    int localIndex = (j * 3) + k;
                    
                    // Calculate the index within the global grid.
                    int globalLocalRowOffset = (int) (i / 3);
                    int localRowOffset = (int) (localIndex /3);
                    
                    int globalLocalColumnOffset = i % 3;
                    int localColumnOffset = localIndex % 3;
                            
                    int globalRow = (globalLocalRowOffset * 3) + localRowOffset;
                    int globalColumn = (globalLocalColumnOffset * 3) + localColumnOffset;
                    
                    int globalIndex = globalRow * 9 + globalColumn;
                    
                    //System.out.println("Setting grid: " + i + " grid row: " + globalLocalRowOffset + " grid column: " + globalLocalColumnOffset + " local row: " + localRowOffset + " local column: " + localColumnOffset + " globalRow: " + globalRow + " globalColumn: " + globalColumn + " globalIndex: " + globalIndex + " value: " + grid[globalIndex]);
                    localGrid.setNumber(localIndex, grid[globalIndex]);
                } // end of for
            } // end of for
        } // end of for
    } // end of loadGrid

    
    public void printGrid() {

        // i is row
        for(int i = 0; i < 9; i++) {
            
            // Print a diverse at the top of each local grid
            if(i == 0)
                System.out.println("  -------------------------------------");
            else if(i % 3 == 0)
                System.out.println("  |-----------+-----------+-----------|");
            else
                System.out.println("  |           |           |           | ");

            // j is column
            for(int j = 0; j < 9; j++) {
                
                int globalLocalRow = (int) (i / 3);
                int globalLocalColumn = (int) (j / 3);
                
                LocalGrid local = grids[(globalLocalRow * 3) + globalLocalColumn];
                
                int localRow = i % 3;
                int localColumn = j % 3;
                
                if(localColumn % 3 == 0)
                    System.out.print("  |");
                
                int value = local.getNumber((localRow * 3) + localColumn);
                if(value == 0) {
                    System.out.print("   ");
                } else {
                    if(local.isOriginal((localRow * 3) + localColumn))
                        System.out.print("  " + value);
                    else 
                        System.out.print("  " + value);
                } // end of else
            } // end of for
            
            System.out.println("  |");
        } // end of for
        
        System.out.println("  -------------------------------------");
    } // end of printGrid
    
    
    public LocalGrid getLocal(int index) {
        
        if(index < 0 || index >= grids.length) return null;
        
        return grids[index];
    } // end of getLocal
    
    
    public LocalGrid getLocal(int row, int column) {
        
        return getLocal((row * 3) + column);
    } // end of getLocal

    
    public boolean solve() {
        
        boolean progress = true;
        
        // First calculate all the possibilities.
        CalculatePossibilities.calculate(this);
        
        // Keep going until no more progress can be made.
        while(progress) {
            
            progress = false;

            // Go through all the methods to try and solve.
            for(int i = 0; i < methods.length; i++) {

                if(methods[i].solve(this))
                    progress = true;
            } // end of for
        } // end of while
        
        // Check to see if the grid has been solved.
        return false;
    } // end of solve

    
    public boolean isSolved() {
        
        // Go through all local grids
        for(int i = 0; i < 9; i++) {
            
            for(int j = 0; j < 9; j++) {
                if(grids[i].getNumber(i) == 0) return false;
            } // end of for
        } // end of for
        
        return true;
    } // end of isSolved
    
    
    public int[] getGrid() {
        
        int[] grid = new int[81];
        
        for(int i = 0; i < 3; i++) {
            
            for(int z = 0; z < 3; z++) {
                
                for(int j = 0; j < 9; j++) {
                    
                    grid[(i*3 + z) * 9 + j] = grids[(i * 3) + (j / 3)].getNumber(z, j % 3);
                } // end of for
            } // end of for
        } // end of for
        
        return grid;
    } // end of getGrid
    
    
    public void removePossibility(int globalRow, int localRow, int globalColumn, int localColumn, int number) {
    
        // Validate the indexes
        if(globalRow < 0 || globalRow > 2) return;
        if(globalColumn < 0 || globalColumn > 2) return;
        if(localRow < 0 || localRow > 2) return;
        if(localColumn < 0 || localColumn > 2) return;
        
        // Go through all the indexes in the row and column and remove the possibilities.
        for(int i = 0; i < 3; i++) {
            
            getLocal(globalRow, i).removePossibilityFromRow(localRow, number);
            getLocal(i, globalColumn).removePossibilityFromColumn(localColumn, number);
        } // end of for
    } // end of removePossibility
} // end of GlobalGrid
