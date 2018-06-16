package uk.co.flakeynetworks.sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.co.flakeynetworks.sudoku.examples.ExampleGrids;
import uk.co.flakeynetworks.sudoku.examples.Grid;
import uk.co.flakeynetworks.sudoku.ui.SudokuBoard;

import java.io.IOException;
import java.util.ArrayList;

public class SudokuSolver extends Application {


    private ArrayList<Grid> presetGrids = new ArrayList<>();

    private Grid currentGrid;


    public static void main(String[] args) {
        launch(args);
    }


    public ArrayList<Grid> getPresetGrids() { return presetGrids; } // end of getPresetGrids


    @Override
    public void start(Stage primaryStage) throws IOException {

        // Load up the list of preset grids
        presetGrids.add(ExampleGrids.EXAMPLE_GRID_0);
        presetGrids.add(ExampleGrids.EXAMPLE_GRID_1);
        presetGrids.add(ExampleGrids.EXAMPLE_GRID_2);
        presetGrids.add(ExampleGrids.EXAMPLE_GRID_3);
        presetGrids.add(ExampleGrids.EXAMPLE_GRID_4);
        presetGrids.add(ExampleGrids.EXAMPLE_GRID_5);


        // Load up the UI

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/SudokuBoard.fxml"));

        // Controller
        SudokuBoard controller = new SudokuBoard(this);
        loader.setController(controller);

        Parent root = loader.load();

        Scene scene = new Scene(root, 327.0, 421.0);
        primaryStage.setScene(scene);
        primaryStage.show();
    } // end of start


    public void setCurrentGrid(Grid grid) { currentGrid = grid; } // end of setCurrentGrid


    public Grid getCurrentGrid() { return currentGrid; } // end of getCurrentGrid
} // end of SudokoSovler
