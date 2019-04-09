package zad1;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class WebViewApp extends Application implements Initializable {
    private static String country = "London";
    @FXML WebView webViewArea;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebEngine webEngine = webViewArea.getEngine();
        webEngine.load("https://en.wikipedia.org/wiki/"+country);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("view.fxml"));
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    static void run(String s){
        country = s;
        launch();
    }
}
