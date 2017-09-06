package uk.co.flakeynetworks.sudoku;

/**
 *
 * @author Richard Stokes
 */
public class LocalGrid {
    
    
    private int[] numbers = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    private boolean[] originals = {false, false, false, false, false, false, false, false, false};
    
    private int globalRow = 0;
    private int globalColumn = 0;
    
    private GlobalGrid global;
    
    
    private Possibilities[] tempPossibilities = {new Possibilities(), new Possibilities(), new Possibilities(),
                                                new Possibilities(), new Possibilities(), new Possibilities(),
                                                new Possibilities(), new Possibilities(), new Possibilities()};

    
    
    public LocalGrid(GlobalGrid global, int globalRow, int globalColumn) {
    
        if(global == null) throw new NullPointerException();
        if(globalRow < 0 || globalRow > 2) return;
        if(globalColumn < 0 || globalColumn > 2) return;
        
        this.global = global;
        this.globalRow = globalRow;
        this.globalColumn = globalColumn;
    } // end of null constructor
    
    
    public LocalGrid(GlobalGrid global, int globalRow, int globalColumn, int[] toSet) {
        
        if(toSet == null || global == null) throw new NullPointerException();
        
        this.global = global;
        this.globalRow = globalRow;
        this.globalColumn = globalColumn;
        
        for(int i = 0; i < toSet.length; i++) {
            
            if(setNumber(i, toSet[i]))
                originals[i] = true;
        } // end of for
    } // end of constructor
    
    
    public boolean setNumber(int index, int number) {
        
        // Validate the number
        if(number < 1 || number > 9) {
            number = 0;
            return false;
        } // end of if
        
        // Make sure we are settings an index within the correct range.
        if(index < 0 || index > (numbers.length - 1)) return false;
        
        numbers[index] = number;
        
        return true;
    } // end of setNumber
    
    
    public boolean setNumber(int row, int col, int number) {
        
        return setNumber((row * 3) + col, number);
    } // end of setNumber
    
    
    public int getNumber(int index) {
        
        // Make sure we are settings an index within the correct range.
        if(index < 0 || index > (numbers.length - 1)) return 0;
        
        return numbers[index];
    } // end of getNumber
    
    
    public boolean doesContainNumber(int p) {
    
        for(int i = 0; i < numbers.length; i++) {
            
            if(numbers[i] == p) return true;
        } // end of for
        
        return false;
    } // end of doesContainNumber

    
    public boolean isPossible(int j, int p) {
        
        // Check if the number is within 1 - 9
        if(p < 1 || p > 9) return false;
        
        // Check if the index is within 0 - 8
        if(j < 0 || j > 8) return false;
        
        // Check if the grid has the number in it.
        if(doesContainNumber(p)) return false;
        
        int row = (j / 3);
        
        // Check if the row in the global grid has that number.
        // Get the local grids to the right and to the left.
        for(int i = 0; i < 3; i++) {
            
            // Check if we are going to check itself.
            if(i == globalColumn) continue;
            
            LocalGrid compare = global.getLocal(globalRow, i);
            if(compare.doesRowContainNumber(row, p)) return false;
        } // end of for
        
        int column = (j % 3);
        
        // Check if the column in the global grid has that number.
        for(int i = 0; i < 3; i++) {
            
            // Check if we are going to check itself.
            if(i == globalRow) continue;
            
            LocalGrid compare = global.getLocal(i, globalColumn);
            if(compare.doesColumnContainNumber(column, p)) return false;
        } // end of for
        
        return true;
    } // end of isPossible

    
    private boolean doesRowContainNumber(int row, int p) {
        
        if(row < 0 || row > 2) return true;
        
        for(int i = 0; i < 3; i++) {
            if(numbers[(row * 3) + i] == p) return true;
        } // end of for
        
        return false;
    } // end of doesRowContainNumber
    
    
    private boolean doesColumnContainNumber(int column, int p) {
        
        if(column < 0 || column > 2) return true;
        
        for(int i = 0; i < 3; i++) {
            if(numbers[column + (i * 3)] == p) return true;
        } // end of for
        
        return false;
    } // end of doesRowContainNumber

    
    public boolean isOriginal(int i) {
        
        if(i < 0 || i == originals.length) return false;
        
        return originals[i];
    } // end of isOriginal
    
    
    public void clearTempPossibilites() {
        
        for(int i = 0; i < tempPossibilities.length; i++)
            tempPossibilities[i].clear();
    } // end of clearTempPossibilities

    
    public int getNumber(int localRow, int localColumn) {
    
        return getNumber((localRow * 3) + localColumn);
    } // end of getNumber

    
    public boolean isPossible(int localRow, int localColumn, Integer p) {
        
        return isPossible((localRow * 3) + localColumn, p);
    } // end of isPossible

    
    public Possibilities getTempPossibilities(int localRow, int localColumn) {
        
        return getTempPossibilities((localRow * 3) + localColumn);
    } // end of getTempPossibilities
    
    
    public Possibilities getTempPossibilities(int index) {
        
        if(index < 0 || index > 9) return null;
        
        return tempPossibilities[index];
    } // end of getTempPossibilities
} // end of LocalGrid
