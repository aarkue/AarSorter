/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author aarim
 */
public class Sorter extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane rootPane = new AnchorPane();
        
FXMLDocumentController controller = new FXMLDocumentController();
FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
fxmlLoader.setController(controller);

        Scene scene = new Scene(fxmlLoader.load(),420,480);
        
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
