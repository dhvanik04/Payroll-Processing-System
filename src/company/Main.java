package company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main class that runs the GUI application for creating and editing a Company Database
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */

public class Main extends Application
{

    /**
     * Creates and runs the GUI application for the company database
     * @param primaryStage the stage for the GUI application
     * @throws Exception error for a problem during program execution
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        primaryStage.setTitle("Payroll Processing GUI");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }

    /**
     * Launches the GUI
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
