package Lesson_04;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.*;

public class JavaFxChat extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setX(900);
        primaryStage.setY(50);
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JavaFxChat.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("JavaFX-реализация");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void run(){ launch(); }
}
