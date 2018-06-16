package uk.co.flakeynetworks.sudoku.ui;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import uk.co.flakeynetworks.sudoku.GlobalGrid;
import uk.co.flakeynetworks.sudoku.LocalGrid;
import uk.co.flakeynetworks.sudoku.SudokuSolver;
import uk.co.flakeynetworks.sudoku.examples.Grid;

public class SudokuBoard {


    @FXML
    private ComboBox presetSelector;
    @FXML
    private GridPane boardGrid;

    private SudokuSolver mainProgram;
    protected ListProperty<Grid> listProperty = new SimpleListProperty<>();


    public SudokuBoard(SudokuSolver mainProgram) {

        this.mainProgram = mainProgram;
    } // end of Constructor


    @FXML
    private void initialize() {

        presetSelector.itemsProperty().bind(listProperty);
        listProperty.set(FXCollections.observableArrayList(mainProgram.getPresetGrids()));
    } // end of initialize


    public void setInitialGrid(Grid grid) {

        int[] gridValues = grid.getGrid();

        // i is row
        for(int i = 0; i < 9; i++) {

            // j is column
            for(int j = 0; j < 9; j++) {

                int globalLocalRow = i / 3;
                int globalLocalColumn = j / 3;

                GridPane localPane = (GridPane) boardGrid.getChildren().get((globalLocalRow * 3) + globalLocalColumn);

                int localRow = i % 3;
                int localColumn = j % 3;

                // Set the value
                TextField box = (TextField) localPane.getChildren().get(localRow * 3 + localColumn);
                int value = gridValues[i * 9 + j];

                if(value == 0) {
                    box.setText("");
                    box.setDisable(false);
                } else {
                    box.setText(Integer.toString(value));
                    box.setDisable(true);
                } // end of else
            } // end of for
        } // end of for
    } // end of setInitialGrid


    public void updateGrid(GlobalGrid grid) {

        int[] gridValues = grid.getGrid();

        // i is row
        for(int i = 0; i < 9; i++) {

            // j is column
            for(int j = 0; j < 9; j++) {

                int globalLocalRow = i / 3;
                int globalLocalColumn = j / 3;

                GridPane localPane = (GridPane) boardGrid.getChildren().get((globalLocalRow * 3) + globalLocalColumn);

                int localRow = i % 3;
                int localColumn = j % 3;

                // Set the value
                TextField box = (TextField) localPane.getChildren().get(localRow * 3 + localColumn);
                int value = gridValues[i * 9 + j];

                if(value == 0)
                    box.setText("");
                else
                    box.setText(Integer.toString(value));
            } // end of for
        } // end of for
    } // end of setInitialGrid


    @FXML
    private void loadBoard(ActionEvent event) {

        Grid currentGrid = (Grid) presetSelector.getSelectionModel().getSelectedItem();
        if(currentGrid == null) return;

        mainProgram.setCurrentGrid(currentGrid);

        setInitialGrid(currentGrid);
    } // end of loadPuzzle


    @FXML
    private void solveBoard(ActionEvent event) {

        Grid grid = mainProgram.getCurrentGrid();
        GlobalGrid global = new GlobalGrid(grid.getGrid());

        boolean solved = global.solve();

        updateGrid(global);
    } // end of solveBoard
} // end of SudokuBoard
