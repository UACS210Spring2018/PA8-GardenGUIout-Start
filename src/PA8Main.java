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

    private GraphicsContext gc;
    private static double delay = 0;

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
        int canvas_pixels_across = 45;
        int canvas_pixels_down = 22;
        Canvas canvas = new Canvas(pixels_across, pixels_down);

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
        gc = canvas.getGraphicsContext2D();
        primaryStage.setTitle("Garden");
        primaryStage.setScene(new Scene(p));

        // Call the draw method.
        draw(gc);  // can we pass in gc?

        // Show everything on the stage
        primaryStage.show();

        //------- Placeholder code (REMOVE once you have the above code)
        // By this point you will need to have read the delay from the
        // file.  Here delay is just set to a constant.
        double delay = 3;
        Integer count = 10;  // number of times placeholder code updates

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
            //     apply that command to the garden
            //     draw to the canvas
            //     append the command to the text field, command.appendText(...);
            //     call wait.playFromStart(); 
            // else:
            //     call wait.stop();

            //---- Placeholder code (REMOVE once you have the above code)
            // This place holder code will draw a rectangle that
            // moves down the screen count times.
            if (count>0) {
                int RECT_SIZE = 10;
                Color c = Color.valueOf("BLUE");
                gc.setFill(c);
                gc.fillRect(canvas_pixels_across/2, count*RECT_SIZE, RECT_SIZE);
                command.appendText("Hello there, count="+count);
                wait.playFromStart();
            } else {
                wait.stop();
            }

        });

        // Now that the PauseTransition thread is setup, get it going.
        wait.play();
    }

}
