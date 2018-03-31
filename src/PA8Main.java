import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PA8Main extends Application {

    private static double delay = 3;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // FIXME: are we supposed to be doing everything in start?

        // Border pane set as the Scene
        BorderPane p = new BorderPane();

        // Canvas(pixels across, pixels down)
        // Note this is opposite of the Garden in PA6.
        //Canvas canvas = new Canvas(num_cols * plot_size * CELLSIZE,
        //        num_rows * plot_size * CELLSIZE);
        final int SIZE_ACROSS = 100;
        final int SIZE_DOWN = 250;
        Canvas canvas = new Canvas(SIZE_ACROSS, SIZE_DOWN);

        // Command TextArea will hold the commands from the file
        int text_pixels_down = 120;
        TextArea command = new TextArea();
        command.setPrefHeight(text_pixels_down);
        command.setEditable(false);

        // Place the canvas and command output areas in pane.
        p.setCenter(canvas);
        p.setBottom(command);

        // Store a reference to the canvas so can draw to it
        // in other methods.
        GraphicsContext gc = canvas.getGraphicsContext2D();
        primaryStage.setTitle("Garden");
        primaryStage.setScene(new Scene(p));

        // Call the draw method.
        final int RECT_SIZE = 20;
        Color c = Color.valueOf("RED");
        gc.setFill(c);
        gc.fillRect(50, 50, RECT_SIZE, RECT_SIZE);
        command.appendText("Hello World!\n");

        // gardenDraw(gc);

        // Show everything on the stage
        primaryStage.show();

        //------- Placeholder code (REMOVE once you have the above code)
        // By this point you will need to have read the delay from the
        // file.
        // This count object is taking the place of the Scanner.
        // Below you will want to pass a reference to your Scanner into
        // your "read in the next command" code. The Scanner object
        // keeps track of where it is in the file. For the placeholder
        // code we are providing here, there is no Scanner. Instead
        // there is this Counter object that the lambda function will
        // have a reference to.
        Counter countref = new Counter(10); // number of times placeholder code
                                            // updates

        //==========
        // Update GUI based on value of delay(seconds to wait)
        // The below code constructs a PauseTransition and then
        // passes a lambda function into the setOnFinished method.
        // A lambda function is an unnamed function.  Here, the
        // unnamed function takes a parameter it names "e" (FIXME: Event?)
        // and then parses the next command out of the file,
        // applies the command to the Garden, and then tells the
        // wait thread to pause for the delay again.
        PauseTransition wait = new PauseTransition(Duration.seconds(delay));
        wait.setOnFinished((e) -> {

            //==== Code that should be executed after each delay goes in here.
            // read in the next command
            // if there was a command left in the file:
            // * apply that command to the garden
            // * draw to the canvas by calling your gardenDraw(gc)
            // * append the command to the text field, command.appendText(...);
            // * call wait.playFromStart();
            // else:
            // * call wait.stop();

            //---- Placeholder code (REMOVE once you have the above code)
            // This place holder code will draw a rectangle that
            // moves down the screen count times.
            if (countref.count > 0) {
                // final int RECT_SIZE = 20;
                // c = Color.valueOf("BLUE");
                gc.setFill(c);
                int x = SIZE_ACROSS / 2;
                int y = countref.count * RECT_SIZE;
                gc.fillRect(x, y, RECT_SIZE, RECT_SIZE);
                command.appendText(
                        "count=" + countref.count + ", x=" + x + ", y = " + y
                                + "\n");
                countref.count--;
                wait.playFromStart();
            } else {
                wait.stop();
            }

        });

        // Now that the PauseTransition thread is setup, get it going.
        wait.play();
    }

}
